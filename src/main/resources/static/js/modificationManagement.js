function addModification(modification) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/addModification",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(modification),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function updateModification(modification) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/adminManagement/updateModification",
        async: false,                         //将ajax改为同步模式
        contentType: "application/json",
        data: JSON.stringify(modification),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function showModification() {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/adminManagement/showModification",
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

function findRestaurantModification(restaurantId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/findRestaurantModification",
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

function modificationAllChecked() {
    var res = true;
    var store = findRestaurantModification(localStorage.getItem("restaurantId"));
    if(store !== null && store.length > 0){
        for(var i = 0; i < store.length; i++){
            if(store[i].checked === false){
                res = false;
                break;
            }
        }
    }
    return res;
}