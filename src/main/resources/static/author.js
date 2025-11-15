let alertSuccess = document.getElementById("alert-success");
let alertFailure = document.getElementById("alert-failure");
let closeSuccess = document.getElementById("close-success");
let closeFailure = document.getElementById("close-failure");
let failureText = document.getElementById("failure-text");
let successText = document.getElementById("success-text");


closeSuccess.addEventListener('click', () => {
    alertSuccess.style.display = "none";
});
closeFailure.addEventListener('click', () => {
    alertFailure.style.display = "none";
});

const csrftoken = document.cookie.replace(/(?:(?:^|.*;\s*)XSRF-TOKEN\s*\=\s*([^;]*).*$)|^.*$/, '$1');

let userName = document.getElementById("user-name");
let password = document.getElementById("password");
let loginBtn = document.getElementById("login-btn");

async function hashPassword (pWord) {
    let pWordArr = await pWord.split("");
    let reversedPword = pWordArr.reverse();

    return reversedPword.join("*").toString();
}

loginBtn.addEventListener('click', async (e) => {
    e.preventDefault();
    let author = {
        "userName": userName.value,
        "password": password.value
    }

    let loginOperation = await fetch("http://localhost:8080/login_author", {
        method: "POST",
        headers: {"Content-Type": "application/json"},
        body: JSON.stringify(author)
    });

    let result = await loginOperation.json();

    if (result.status == "success") {
        alertSuccess.style.display = "block";
        successText.innerHTML = result.message;
        console.log(result);
        console.log(result.author);
        let modPassword = await hashPassword(password.value);
        console.log(modPassword);
        localStorage.setItem("authorDetails", JSON.stringify(result.author));
        setTimeout(() => {window.location = `http://localhost:8080/logged_author/${userName.value}/${modPassword}`}, 3000);
        // setTimeout(() => {gotoAuthorDashboard(userName.value, modPassword)}, 3000);
    } else {
        alertFailure.style.display = "block";
        failureText.innerHTML = result.message;
    }
});

function gotoAuthorDashboard(username, password) {

    // let reqBody = {
    //     "userName": username,
    //     "password": password
    // }

    fetch(`http://localhost:8080/logged_author/${username}/${password}`, {
        method: "GET",
        headers: {
            "Content-Type": "application/json",
            "X-XSRF-TOKEN": csrftoken
                },
        // body: JSON.stringify(reqBody)
    }).then(res => console.log(res)).
    catch(err => console.log(err.message));
}
