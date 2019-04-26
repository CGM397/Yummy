function showUserAccount(userId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/userAccount/showUserAccount",
        async: false,                         //将ajax改为同步模式
        data: {
            userId : userId
        },
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function saveUserAccount(account) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/userAccount/saveUserAccount",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(account),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function updateUserAccount(account) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/userAccount/updateUserAccount",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(account),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}