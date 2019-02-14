function findCustomerInfoByMail(customerMail) {
    var currentCustomer = new Customer();
    $.ajax({
        type: "POST",
        url: "/customerInfo/findCustomerInfoByMail",
        async: false,                         //将ajax改为同步模式
        data: {
            customerMail : customerMail
        },
        success: function (result) {
            currentCustomer = new Customer(result.customerId,
                customerMail,
                result.customerPassword,
                result.customerName,
                result.phoneNumber,
                result.vipLevel,
                result.active);
        },
        error: function () {
            alert("register : error!");
        }
    });
    return currentCustomer;
}

function modifyPassword() {
    var customerPassword = document.getElementById("customer-password").value;
    swal({
            title: "修改密码",
            text: "请输入原密码",
            type: "input",
            inputType: "password",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "password"
        },
        function (oldPwd) {
            if (oldPwd !== customerPassword) {
                swal.showInputError("原密码错误");
            }
            else {
                swal({
                        title: "修改密码",
                        text: "请输入新密码",
                        type: "input",
                        inputType: "password",
                        showCancelButton: true,
                        closeOnConfirm: false,
                        animation: "slide-from-top",
                        inputPlaceholder: "password"
                    },
                    function (newPwd) {
                        if(newPwd === ""){
                            swal.showInputError("密码长度至少为1");
                            return;
                        }
                        swal({
                                title: "修改密码",
                                text: "请确认新密码",
                                type: "input",
                                inputType: "password",
                                showCancelButton: true,
                                closeOnConfirm: false,
                                animation: "slide-from-top",
                                inputPlaceholder: "password"
                            },
                            function (newPwd2) {
                                if (newPwd !== newPwd2) {
                                    swal.showInputError("确认密码错误");
                                }
                                else {
                                    var customerId = document.getElementById("customer-id").value;
                                    var customerName = document.getElementById("customer-name").value;
                                    var customerPassword = newPwd2;
                                    var customerMail = document.getElementById("customer-mail").value;
                                    var phoneNumber = document.getElementById("phone-number").value;
                                    var vipLevel = document.getElementById("vip-level").value;
                                    var active = true;
                                    var info = new Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipLevel, active);
                                    updateCustomerInfo(info);
                                    infoListInit();
                                    swal("修改成功", "密码已更新", "success");
                                }
                            });
                    });
            }
        });
}

function modifyInfo() {
    var customerId = document.getElementById("customer-id").value;
    var customerName = document.getElementById("customer-name").value;
    var customerPassword = document.getElementById("customer-password").value;
    var customerMail = document.getElementById("customer-mail").value;
    var phoneNumber = document.getElementById("phone-number").value;
    var vipLevel = document.getElementById("vip-level").value;
    var active = true;
    var info = new Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipLevel, active);
    swal({
            title: "确定修改您的个人信息吗",
            text: "点击确认进行修改",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            if(customerName === "" || phoneNumber === ""){
                swal("修改失败","用户名和联系电话不可为空","error");
                return;
            }
            updateCustomerInfo(info);
            infoListInit();
            swal("修改成功","个人信息已更新","success");
        });
}

function delAccount() {
    swal({
            title: "确定注销该账号吗?",
            text: "点击确认进行注销",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function(){
            var customerId = document.getElementById("customer-id").value;
            var customerName = document.getElementById("customer-name").value;
            var customerPassword = document.getElementById("customer-password").value;
            var customerMail = document.getElementById("customer-mail").value;
            var phoneNumber = document.getElementById("phone-number").value;
            var vipLevel = document.getElementById("vip-level").value;
            var active = false;
            var info = new Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipLevel, active);
            updateCustomerInfo(info);
            swal("注销成功","本账号已无法登陆此系统！","success");
            setTimeout(function(){
                window.location.href="login";
                window.event.returnValue=false;
            }, 1500);
        });
}

function updateCustomerInfo(info) {
    $.ajax({
        type: 'POST',
        url:"/customerInfo/updateCustomerInfo",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(info),
        success:function(){
        },
        error:function(){
            alert("error");
        }
    });
}