define(['jquery'],function($){
    //变量定义区
    var moduleName = "hello module";
    var moduleVersion = "1.0";

    var singleUploadForm = document.querySelector('#singleUploadForm');
    var singleFileUploadInput = document.querySelector('#singleFileUploadInput');
    var singleFileUploadError = document.querySelector('#singleFileUploadError');
    var singleFileUploadSuccess = document.querySelector('#singleFileUploadSuccess');

    var multipleUploadForm = document.querySelector('#multipleUploadForm');
    var multipleFileUploadInput = document.querySelector('#multipleFileUploadInput');
    var multipleFileUploadError = document.querySelector('#multipleFileUploadError');
    var multipleFileUploadSuccess = document.querySelector('#multipleFileUploadSuccess');

    var uploadSingleFile = function uploadSingleFile(file) {
        var formData = new FormData();
        formData.append("file", file);

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/uploadFile");

        xhr.onload = function() {
            console.log(xhr.responseText);
            var response = JSON.parse(xhr.responseText);
            if(xhr.status == 200) {
                singleFileUploadError.style.display = "none";
                singleFileUploadSuccess.innerHTML = "<p>File Uploaded Successfully.</p><p>DownloadUrl : <a href='" + response.fileDownloadUri + "' target='_blank'>" + response.fileDownloadUri + "</a></p>";
                singleFileUploadSuccess.style.display = "block";
            } else {
                singleFileUploadSuccess.style.display = "none";
                singleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
            }
        }

        xhr.send(formData);
    }

    var uploadMultipleFiles = function uploadMultipleFiles(files) {
        var formData = new FormData();
        for(var index = 0; index < files.length; index++) {
            formData.append("files", files[index]);
        }

        var xhr = new XMLHttpRequest();
        xhr.open("POST", "/uploadMultipleFiles");

        xhr.onload = function() {
            console.log(xhr.responseText);
            var response = JSON.parse(xhr.responseText);
            if(xhr.status == 200) {
                multipleFileUploadError.style.display = "none";
                var content = "<p>All Files Uploaded Successfully</p>";
                for(var i = 0; i < response.length; i++) {
                    content += "<p>DownloadUrl : <a href='" + response[i].fileDownloadUri + "' target='_blank'>" + response[i].fileDownloadUri + "</a></p>";
                }
                multipleFileUploadSuccess.innerHTML = content;
                multipleFileUploadSuccess.style.display = "block";
            } else {
                multipleFileUploadSuccess.style.display = "none";
                multipleFileUploadError.innerHTML = (response && response.message) || "Some Error Occurred";
            }
        }

        xhr.send(formData);
    }

    return {
        //code
        singleUploadForm:singleUploadForm,
        singleFileUploadInput: singleFileUploadInput,
        singleFileUploadError:singleFileUploadError,
        singleFileUploadSuccess:singleFileUploadSuccess,

        multipleUploadForm:  multipleUploadForm,
        multipleFileUploadInput: multipleFileUploadInput,
        multipleFileUploadError: multipleFileUploadError,
        multipleFileUploadSuccess: multipleFileUploadSuccess,


        uploadSingleFile: uploadSingleFile,
        uploadMultipleFiles: uploadMultipleFiles,
    }
})