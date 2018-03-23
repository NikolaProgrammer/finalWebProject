function uploadFileForm(taskId) {
	document.uploadFile.hiddenTaskId.value = taskId;
	document.uploadFile.submit();
}

function downloadFileForm(taskId) {
	document.downloadFile.hiddenTaskId.value = taskId;
	document.downloadFile.submit();
}

function deleteFileForm(taskId) {
	document.deleteFile.hiddenTaskId.value = taskId;
	document.deleteFile.submit();
}