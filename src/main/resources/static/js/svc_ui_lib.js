
function moveToMgrHome() {
	location.href = "/mgr/home";
}

function moveToInputAnswer() {
	location.href = "/mgr/inputAnswer";
}

function moveToModify(answerId) {
	location.href = "/mgr/modifyAnswer/" + answerId;
}

function moveToBack() {
	history.back();
}

function shareFacebook() {
    var sendUrl = "shellofmagic.com/"; // 전달할 URL
    window.open("http://www.facebook.com/sharer/sharer.php?u=" + sendUrl);
}

function shareTwitter() {
    var sendText = "고민을 말해보세요!"; // 전달할 텍스트
    var sendUrl = "shellofmagic.com/"; // 전달할 URL
    window.open("https://twitter.com/intent/tweet?text=" + sendText + "&url=" + sendUrl);
}