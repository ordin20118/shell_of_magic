


function getUrlParam(key) {

	if(key == null) {
		return null;
	}	
	
	var urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParams = url.searchParams;	
	return urlParams.get(key);
}


function copyClip() {
	var url = '';
	var textarea = document.createElement("textarea");
	document.body.appendChild(textarea);
	url = window.document.location.href;
	textarea.value = url;
	textarea.select();
	document.execCommand("copy");
	document.body.removeChild(textarea);
	alert('URL이 복사되었습니다.');
}