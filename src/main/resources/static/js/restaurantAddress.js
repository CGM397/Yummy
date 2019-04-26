function showRestaurantAddress(restaurantId) {
    var res = new Address();
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/showRestaurantAddress",
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

function saveRestaurantAddress(address) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/saveRestaurantAddress",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(address),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function updateRestaurantAddress(restaurantId, oldAddress, newAddress) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/updateRestaurantAddress",
        async: false,                         //将ajax改为同步模式
        data: {
            restaurantId : restaurantId,
            oldAddress : oldAddress,
            newAddress : newAddress
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