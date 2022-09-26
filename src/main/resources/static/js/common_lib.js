


function getUrlParam(key) {

	if(key == null) {
		return null;
	}	
	
	var urlStr = window.location.href;
	const url = new URL(urlStr);
	const urlParams = url.searchParams;	
	return urlParams.get(key);
}