package com.shellofmagic.web.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shellofmagic.web.controller.MgrUiController;
import com.shellofmagic.web.dao.AnswerCategoryDto;
import com.shellofmagic.web.dao.AnswerCategoryRepository;
import com.shellofmagic.web.dao.AnswerDto;
import com.shellofmagic.web.dao.AnswerLogDto;
import com.shellofmagic.web.dao.AnswerLogRepository;
import com.shellofmagic.web.dao.AnswerRepository;
import com.shellofmagic.web.dao.CategoryDto;
import com.shellofmagic.web.dao.CategoryRepository;
import com.shellofmagic.web.dao.PlayLogDto;
import com.shellofmagic.web.dao.PlayLogRepository;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class WebService {
	
	@Autowired
	private AnswerRepository answerRepoitory;
	
	@Autowired
	private CategoryRepository categoryRepository;
	
	@Autowired
	private PlayLogRepository playLogRepository;
	
	@Autowired
	private AnswerCategoryRepository answerCategoryRepository;
	
	@Autowired
	private AnswerLogRepository answerLogRepository;
	
	
	public Page<AnswerDto> getAnswerList(Pageable pageable) {
		Page<AnswerDto> resData = answerRepoitory.findAll(pageable);
		return resData;
	}
	
	public AnswerDto getAnswer(Integer id) {
		Optional<AnswerDto> resData = answerRepoitory.findById(id);
		if(resData.isPresent()) {
			return resData.get();
		} else {
			return null;
		}
	}
	
	public AnswerDto getRandomAnswer(HttpServletRequest request, Integer categId, String question) {
		
		log.info("[getRandomAnswer]:" + categId);
		
		long total = 0;
		
		if(categId == null) {
			total = answerCategoryRepository.count();
		} else {
			total = answerCategoryRepository.getCountByCateg(categId);
		}
		
		int idx = (int)(Math.random() * total);
		
		List<AnswerCategoryDto> acList = answerCategoryRepository.getAllByCateg(categId, PageRequest.of(idx, 1));
		
		if(acList != null && acList.size() > 0) {
			AnswerDto answer = answerRepoitory.findById(acList.get(0).getAnswerId()).orElse(null);	
		
			String agent = request.getHeader("USER-AGENT");    
		    String os = getClientOS(agent);
		    String browser = getClientBrowser(agent);
		    String client = os + "/" + browser;
			String ip = request.getHeader("X-FORWARDED-FOR");
	        if(ip == null){
	            ip = request.getRemoteAddr();
	        }
	        
	        if(question == null) {
	        	question = "";
	        }
	        
			answerLogRepository.save(AnswerLogDto.builder()
					.ip(ip)
					.client(client)
					.category(categId)
					.question(question)
					.answerId(answer.getId())
					.build());
			
			return answer;
			
		} else {
			return new AnswerDto();
		}
		
	}
	
	public String getClientOS(String userAgent) {            
	      String os = "";            
	    userAgent = userAgent.toLowerCase();
	    if (userAgent.indexOf("windows nt 10.0") > -1) {
	        os = "Windows10";
	    }else if (userAgent.indexOf("windows nt 6.1") > -1) {
	        os = "Windows7";
	    }else if (userAgent.indexOf("windows nt 6.2") > -1 || userAgent.indexOf("windows nt 6.3") > -1 ) {
	        os = "Windows8";
	    }else if (userAgent.indexOf("windows nt 6.0") > -1) {
	        os = "WindowsVista";
	    }else if (userAgent.indexOf("windows nt 5.1") > -1) {
	        os = "WindowsXP";
	    }else if (userAgent.indexOf("windows nt 5.0") > -1) {
	        os = "Windows2000";
	    }else if (userAgent.indexOf("windows nt 4.0") > -1) {
	        os = "WindowsNT";
	    }else if (userAgent.indexOf("windows 98") > -1) {
	        os = "Windows98";
	    }else if (userAgent.indexOf("windows 95") > -1) {
	        os = "Windows95";
	    }else if (userAgent.indexOf("iphone") > -1) {
	        os = "iPhone";
	    }else if (userAgent.indexOf("ipad") > -1) {
	        os = "iPad";
	    }else if (userAgent.indexOf("android") > -1) {
	        os = "android";
	    }else if (userAgent.indexOf("mac") > -1) {
	        os = "mac";
	    }else if (userAgent.indexOf("linux") > -1) {
	        os = "Linux";
	    }else{
	        os = "Unknown";
	    }            
	      return os;
	}
	
	public String getClientBrowser(String userAgent) {
	      String browser = "";
	      
	      if (userAgent.indexOf("Trident/7.0") > -1) {
	        browser = "ie11";
	    }
	      else if (userAgent.indexOf("MSIE 10") > -1) {
	        browser = "ie10";
	    }
	    else if (userAgent.indexOf("MSIE 9") > -1) {
	        browser = "ie9";
	    }
	    else if (userAgent.indexOf("MSIE 8") > -1) {
	        browser = "ie8";
	    }
	    else if (userAgent.indexOf("Chrome/") > -1) {
	        browser = "Chrome";
	    }
	    else if (userAgent.indexOf("Chrome/") == -1 && userAgent.indexOf("Safari/") >= -1) {
	        browser = "Safari";
	    }
	    else if (userAgent.indexOf("Firefox/") >= -1) {
	        browser = "Firefox";
	    }
	    else {
	        browser ="Other";
	    }
	      return browser;
	}

	
	public CategoryDto getCategory(Integer categId) {
		Optional<CategoryDto> categOp = categoryRepository.findById(categId);
		if(categOp.isPresent()) {
			return categOp.get();
		}
		return null;
	}
	
	public boolean saveLog(PlayLogDto log) {
		playLogRepository.save(log);
		return true;
	}
		
}
