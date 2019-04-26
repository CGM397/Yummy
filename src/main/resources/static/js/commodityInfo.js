function showCommodity(restaurantId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/restaurantRelease/showCommodity",
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

function showCommodityToCome(restaurantId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/restaurantRelease/showCommodityToCome",
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

function showDiscountInfo(restaurantId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/customerShopping/showDiscountInfo",
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

function addCommodityInfo(info) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantRelease/releaseProduct",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(info),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function addDiscountInfo(info) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantRelease/releaseDiscount",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(info),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}