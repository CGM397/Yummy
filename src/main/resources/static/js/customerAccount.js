function setAccountInfo() {
    var accountStore = showUserAccount(localStorage.getItem("customerId"));
    if(accountStore.length > 0){
        document.getElementById("newAccount").style.display = "none";
        document.getElementById("accountInUse").style.display = "block";
        var accountInUse = new Account();
        for(var i = 0; i < accountStore.length; i++){
            if(accountStore[i].inUse){
                accountInUse = accountStore[i];
                break;
            }
        }
        document.getElementById("account-id").value = accountInUse.accountId;
        document.getElementById("account-password").value = accountInUse.paymentPassword;
    }
}

function saveAccount() {
    var accountId = document.getElementById("new-accountId").value;
    var password = document.getElementById("new-accountPassword").value;
    var passwordConfirm = document.getElementById("new-passwordConfirm").value;
    if(accountId === "" || password === "" || passwordConfirm === ""){
        swal("绑定失败","请将信息填写完整","error");
        return;
    }
    else if(password !== passwordConfirm){
        swal("绑定失败","两次密码输入不一致","error");
        return;
    }
    swal({
            title: "确定绑定此银行卡吗",
            text: "点击确认进行绑定",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            var account = new Account(localStorage.getItem("customerId"),
                                    accountId, password, 100000, true);
            if(saveUserAccount(account)){
                swal({
                    title: "绑定成功",
                    text: "绑定银行卡成功！",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }
            else
                swal("绑定失败", "绑定银行卡失败", "error");
        });
}

function modify() {
    document.getElementById("account-id").disabled = false;
    swal("编辑权限已开启", "请在‘银行账号’一栏输入新的银行卡号", "success");
}

function modifyPassword() {
    var accountPassword = document.getElementById("account-password").value;
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
            if (oldPwd !== accountPassword) {
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
                                    var store =
                                        showUserAccount(localStorage.getItem("customerId"));
                                    var res = true;
                                    for(var i = 0; i < store.length; i++){
                                        var one = store[i];
                                        one.paymentPassword = newPwd2;
                                        if(!updateUserAccount(one))
                                            res = false;
                                    }
                                    if(res){
                                        swal({
                                            title: "修改成功",
                                            text: "支付密码已更新",
                                            type: "success"
                                        },function () {
                                            window.location.reload();
                                        });
                                    }
                                    else
                                        swal("修改失败", "支付密码未更新", "error");
                                }
                            });
                    });
            }
        });
}

function modifyAccount() {
    var accountId = document.getElementById("account-id").value;
    var password = document.getElementById("account-password").value;

    swal({
            title: "确定保存此银行卡信息吗",
            text: "点击确认进行保存",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            if(accountId === ""){
                swal("保存失败","银行卡号长度至少要大于等于1位","error");
                return;
            }
            var store = showUserAccount(localStorage.getItem("customerId"));
            var account = new Account();
            var isOldAccount = false;
            var flag = true;
            for(var i = 0; i < store.length; i++){
                account = store[i];
                if(account.accountId === accountId){
                    isOldAccount = true;
                    if(!account.inUse){
                        account.inUse = true;
                        if(!updateUserAccount(account))
                            flag = false;
                    }
                }else{
                    if(account.inUse){
                        account.inUse = false;
                        if(!updateUserAccount(account))
                            flag = false;
                    }
                }
            }
            if(!flag){
                swal("保存失败","支付设置未更新","error");
                return;
            }
            if(!isOldAccount){
                account = new Account(localStorage.getItem("customerId"),
                    accountId, password, 100000, true);
                if(saveUserAccount(account)){
                    swal({
                        title: "保存成功",
                        text: "支付设置已更新",
                        type: "success"
                    },function () {
                        window.location.reload();
                    });
                }
                else
                    swal("保存失败","支付设置未更新","error");
            }else{
                swal({
                    title: "保存成功",
                    text: "支付设置已更新",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }
        });
}