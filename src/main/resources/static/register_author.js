let authorAvatar = document.getElementById("author-avatar");
let authorPix = document.getElementById("author-pix");
let alertSuccess = document.getElementById("alert-success");
let alertFailure = document.getElementById("alert-failure");
let closeSuccess = document.getElementById("close-success");
let closeFailure = document.getElementById("close-failure");
let failureText = document.getElementById("failure-text");
let successText = document.getElementById("success-text");
let photoBlob;
let baseName;

authorAvatar.src === "" ? authorAvatar.style.display = "none" : authorAvatar.style.display = "";

closeSuccess.addEventListener('click', () => {
    alertSuccess.style.display = "none";
});
closeFailure.addEventListener('click', () => {
    alertFailure.style.display = "none";
});
authorPix.addEventListener('change', (e) => {
    let tmp_path = URL.createObjectURL(e.target.files[0]);
    authorAvatar.style.display = "";
    authorAvatar.src = tmp_path;
    console.log(authorAvatar);
    baseName = authorPix.value.split('\\').pop().split('/').pop();
    photoBlob = e.target.files[0];
});

let authorName = document.getElementById("author-name");
let authorDisplayName = document.getElementById("display-name");
let authorEmail = document.getElementById("author-email");
let authorAccountStatus = false;
let userName = document.getElementById("username");
let password = document.getElementById("password");
let retPassword = document.getElementById("ret-password");
let regBtn = document.getElementById("reg-btn");

regBtn.addEventListener('click', async (e) => {
    e.preventDefault();
    if(retPassword.value != password.value) {
        alertFailure.style.display = "block";
        failureText.innerHTML = "Passwords do not match!";
    } else {

        let formData = new FormData();
        formData.append("authorPix", photoBlob, baseName);
        formData.append("authorName", authorName.value);
        formData.append("authorDisplayName", authorDisplayName.value);
        formData.append("authorEmail", authorEmail.value);
        formData.append("authorAccountStatus", authorAccountStatus);
        formData.append("userName", userName.value);
        formData.append("password", password.value);
    
        let saveOperation = await fetch('http://localhost:8080/save_author', {
            method: 'POST',
            body: formData
        });

        let serverResponse = await saveOperation.json();
        console.log(serverResponse);
        if(serverResponse.status == "success") {
            alertSuccess.style.display = "block";
            successText.innerHTML = serverResponse.message;
        } else if( serverResponse.status == "error") {
            alertFailure.style.display = "block";
            failureText.innerHTML = serverResponse.message;
        } else {
            alertFailure.style.display = "block";
            failureText.innerHTML = "Some error occured";
        }

    }

});
