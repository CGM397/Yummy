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

function modifyPassword() {
    var restaurantPassword = document.getElementById("restaurant-password").value;
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
            if (oldPwd !== restaurantPassword) {
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
                                    var currentInfo =
                                        findRestaurantInfoById(localStorage.getItem("restaurantId"));
                                    var info = new Restaurant(currentInfo.restaurantId,
                                                              newPwd2,
                                                              currentInfo.restaurantName,
                                                              currentInfo.restaurantType);
                                    if(updateRestaurantInfo(info)){
                                        swal({
                                            title: "修改成功",
                                            text: "登录密码已更新",
                                            type: "success"
                                        },function () {
                                            window.location.reload();
                                        });
                                    }
                                    else
                                        swal("修改失败", "登录密码未更新", "error");
                                }
                            });
                    });
            }
        });
}

function modifyInfo() {
    if(!modificationAllChecked()){
        swal("修改失败","您还有在审核中的修改申请","error");
        return;
    }
    var modificationDate = getDate();
    var restaurantId = document.getElementById("restaurant-id").value;
    var restaurantName = document.getElementById("restaurant-name").value;
    var restaurantAddress = document.getElementById("restaurant-address").value;
    var restaurantType = document.getElementById("restaurant-type").value;
    swal({
            title: "确定修改您的餐厅信息吗",
            text: "点击确认进行修改",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            if(restaurantName === "" || restaurantAddress === "" ||
                restaurantType === "-- please select --"){
                swal("修改失败","餐厅名称、地址和类型不可为空","error");
                return;
            }
            var currentRes = findRestaurantInfoById(restaurantId);
            var currentAdd = showRestaurantAddress(restaurantId);
            if(restaurantName === currentRes.restaurantName)
                restaurantName = "无修改";
            if(restaurantType === currentRes.restaurantType)
                restaurantType = "无修改";
            if(restaurantAddress === currentAdd.address)
                restaurantAddress = "无修改";
            var modification = new Modification(modificationDate, restaurantId, restaurantName,
                                                restaurantType, restaurantAddress, false, false);
            if(addModification(modification))
                swal("修改申请已提交", "待Yummy总经理审批", "success");
            else
                swal("修改申请未提交", "修改失败", "error");
        });
}

function updateRestaurantInfo(info) {
    var res = false;
    $.ajax({
        type: 'POST',
        url:"/restaurantInfo/updateRestaurantInfo",
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