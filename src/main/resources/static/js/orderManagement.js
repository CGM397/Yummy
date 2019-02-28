function showCustomerOrders(customerId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/customerOrder/showCustomerOrders",
        async: false,                         //将ajax改为同步模式
        data: {
            customerId : customerId
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

function showRestaurantOrders(restaurantId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/restaurantOrder/showRestaurantOrders",
        async: false,                         //将ajax改为同步模式
        data: {
            restaurantId : restaurantId
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

function findOneOrderById(orderId) {
    var res = new OrderInfo();
    $.ajax({
        type: 'POST',
        url:"/customerOrder/findOneOrderById",
        async: false,                         //将ajax改为同步模式
        data: {
            orderId : orderId
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