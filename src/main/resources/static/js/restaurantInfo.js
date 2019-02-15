function findRestaurantInfoById(restaurantId) {
    var currentRestaurant = new Restaurant();
    $.ajax({
        type: "POST",
        url: "/restaurantInfo/findRestaurantInfoById",
        async: false,                         //将ajax改为同步模式
        data: {
            restaurantId : restaurantId
        },
        success: function (result) {
            currentRestaurant = new Restaurant(result.restaurantId,result.restaurantPassword,
                                            result.restaurantName, result.restaurantType);
        },
        error: function () {
            alert("register : error!");
        }
    });
    return currentRestaurant;
}