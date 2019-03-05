function setPageInfo() {
    document.getElementById("restaurant-num").value = getRestaurantNum();
    document.getElementById("customer-num").value = getCustomerNum();
    document.getElementById("sys-income").value =
                                        showUserAccount("admin")[0].balance;
}

function getCustomerNum() {
    var res = 0;
    $.ajax({
        type: 'POST',
        url:"/adminManagement/getCustomerNum",
        async: false,                         //将ajax改为同步模式
        data: {},
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function getRestaurantNum() {
    var res = 0;
    $.ajax({
        type: 'POST',
        url:"/adminManagement/getRestaurantNum",
        async: false,                         //将ajax改为同步模式
        data: {},
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}