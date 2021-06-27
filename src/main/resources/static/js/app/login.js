var URL_ = window.location.protocol + "//" + window.location.host + "/";

function login(){
    var name = document.getElementById("name");
    var password = document.getElementById("password");
    sendLogin(name.value, password.value);
}
function signUp(){
    var name = document.getElementById("name");
    var password = document.getElementById("password");
    if(name.value.length>=5 && password.value.length>=5){
        sendSign(name.value, password.value);
    }else{
        alert("name and password must be least 5 words");
    }
}
function sendSign(name, password){
    fetch(URL_+"modify", {
    method: "POST",
    headers: {
        "Content-Type": "application/json",
    },
    body: JSON.stringify({
        name: name,
        money: 0,
        password: password,
        debt: 0
    }),
    })
    .then((response) => response.json())
    .then((data) => console.log(data));
    setTimeout(() => window.location.replace("/"), 1000);
}
function sendLogin(name_, password_){
    var url = new URL(URL_+"api/login");

    var params = {name: name_, password: password_};

    url.search = new URLSearchParams(params).toString();

    fetch(url)
        .then((response) => response.json())
        .then((data) => isTrue(data));
}
function isTrue(bool){
    if(bool){
        setTimeout(() => window.location.replace("/"), 1000);
    }else{
        alert("No User!");
    }
}