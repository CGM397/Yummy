function sendMail(customerMail) {
    $.ajax({
        type: "POST",
        url: "/registerLogin/sendMail",
        data: {
            customerMail : customerMail
        },
        success: function (result) {
            if(result){
                alert("send success");
            }
            else
                alert("发送失败！");
        },
        error: function () {
            alert("sendMail : error!");
        }
    });
}

function register(customerMail, customerPassword, customerName, phoneNumber) {
    $.ajax({
        type: "POST",
        url: "/registerLogin/register",
        data: {
            customerMail : customerMail,
            customerPassword : customerPassword,
            customerName : customerName,
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

function login(customerMail, customerPassword) {
    $.ajax({
        type: "POST",
        url: "/registerLogin/login",
        data: {
            customerMail : customerMail,
            customerPassword : customerPassword
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