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