function getInputForm() {
	var frm = document.inputform;
	if(frm == undefined) frm = document.mainform;
	return frm;
}
function paging(url, pageId) {
	var frm = getInputForm();
	frm.page.value = pageId;
	frm.clickSearch.value = 1;
	frm.submit();
}
