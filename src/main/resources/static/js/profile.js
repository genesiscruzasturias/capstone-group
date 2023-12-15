import {client} from "./keys.js";

let imgUploadBtn = document.querySelector("#profilePicUpload");
imgUploadBtn.addEventListener('click', function (e) {
    e.preventDefault();
    const options = {
        onUploadDone: async (file, options) => {
            let fileURL = "";
            console.log(file.filesUploaded[0].url);
            document.getElementById('profilePicURL').value = file.filesUploaded[0].url;
        }
    }
    client.picker(options).open();
})