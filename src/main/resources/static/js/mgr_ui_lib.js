
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