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

function deleteModification() {

}

function updateModification() {

}

function showModification() {

}

function findModificationById(restaurantId) {

}