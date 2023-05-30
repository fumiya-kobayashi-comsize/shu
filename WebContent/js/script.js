function checkPasswordMatch() {
    var password1 = document.getElementsByName("password")[0].value;
    var password2 = document.getElementsByName("passwordConfirm")[0].value;

    if (password1 !== password2) {
        document.getElementById("passwordMatchMessage").innerHTML = "パスワードが一致しません";
    } else {
        document.getElementById("passwordMatchMessage").innerHTML = "";
    }
}
