function checkPasswordMatch() {
	var password1 = document.getElementsByName("password")[0].value;
    var password2 = document.getElementsByName("passwordConfirm")[0].value;

    if (password1 !== password2) {
        document.getElementById("passwordMatchMessage").innerHTML = "パスワードが一致しません";
        document.getElementById("submitBtn").disabled = true;
    } else {
        document.getElementById("passwordMatchMessage").innerHTML = "";
        document.getElementById("submitBtn").disabled = false;
    }
}

function resetForm() {
	var form = document.getElementById("userForm");

	// 入力フィールドの値を空にする
	var inputs = form.getElementsByTagName("input");
	for (var i = 0; i < inputs.length; i++) {
		if (inputs[i].type !== "submit" && inputs[i].type !== "reset") {
			inputs[i].value = "";
		}
	}
}