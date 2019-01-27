function register(userMail, userPassword, userName, phoneNumber) {
    $.ajax({
        type: "POST",
        url: "/registerLogin/register",
        data: {
            userMail : userMail,
            userPassword : userPassword,
            userName : userName,
            phoneNumber : phoneNumber
        },
        success: function (result) {
            if(result){
                alert("register success");
            }
            else
                alert("注册失败！");
        },
        error: function () {
            alert("register : error!");
        }
    });
}

function login(userMail, userPassword) {
    $.ajax({
        type: "POST",
        url: "/registerLogin/login",
        data: {
            userMail : userMail,
            userPassword : userPassword
        },
        success: function (result) {
            if(result){
                alert("login success");
            }
            else
                alert("登录失败，账号或者密码错误！");
        },
        error: function () {
            alert("login : error!");
        }
    });
}