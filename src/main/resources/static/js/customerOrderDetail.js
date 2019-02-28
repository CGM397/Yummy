function setPageInfo() {
    var orderId = localStorage.getItem("selectedOrderId");
    var orderInfo = findOneOrderById(orderId);
    if(orderInfo.orderCondition === "未付款"){
        document.getElementById("pay-order").style.display = "inline-block";
        document.getElementById("cancel-order").style.display = "inline-block";
    }else if(orderInfo.orderCondition === "送货中"){
        document.getElementById("pay-order").style.display = "none";
        document.getElementById("cancel-order").style.display = "inline-block";
    }else if(orderInfo.orderCondition === "已取消" || orderInfo.orderCondition === "已完成"){
        document.getElementById("pay-order").style.display = "none";
        document.getElementById("cancel-order").style.display = "none";
    }

    document.getElementById("restaurantName").value =
        findRestaurantInfoById(orderInfo.restaurantId).restaurantName;
    document.getElementById("delivery-address").value = orderInfo.deliveryAddress;
    document.getElementById("total-price").value = orderInfo.totalPrice;
    var deliveryTime = orderInfo.deliveryTime;
    deliveryTime = deliveryTime.substring(0, deliveryTime.indexOf("."));
    document.getElementById("delivery-time").value = deliveryTime;

    for(var i = 0; i < orderInfo.items.length; i++){
        var name = orderInfo.items[i].itemName;
        var amount = orderInfo.items[i].amount;
        var subTotal = orderInfo.items[i].subTotal;
        addShoppingCartRow(name, amount, subTotal);
    }
}

function addShoppingCartRow(name, amount, subTotal) {
    var table = document.getElementById("shoppingCartTable");
    var row = document.createElement("tr");

    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = name;
    row.appendChild(nameCell);

    var amountCell = document.createElement('td');
    amountCell.style.textAlign='center';
    amountCell.innerHTML = amount;
    row.appendChild(amountCell);

    var subTotalCell = document.createElement('td');
    subTotalCell.style.textAlign='center';
    subTotalCell.innerHTML = subTotal;
    row.appendChild(subTotalCell);

    table.appendChild(row);
}

function payOrder() {
    swal({
            title: "输入支付密码",
            text: "请输入支付密码",
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
                    findCustomerInfoByMail(localStorage.getItem("customerMail"));
                var info = new Customer(currentInfo.customerId,
                    currentInfo.customerMail,
                    newPwd2,
                    currentInfo.customerName,
                    currentInfo.phoneNumber,
                    currentInfo.vipPoints,
                    currentInfo.vipLevel,
                    currentInfo.active);
                if(updateCustomerInfo(info)){
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
}

function cancelOrder() {

}