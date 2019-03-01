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
    var store = showUserAccount(localStorage.getItem("customerId"));
    if(store.length === 0){
        swal("支付失败","您还未绑定银行卡，请到个人信息页面进行绑定","error");
        return;
    }
    var account = new Account();
    for(var i = 0; i < store.length; i++){
        if(store[i].inUse){
            account = store[i];
            break;
        }
    }
    swal({
            title: "支付密码",
            text: "请输入支付密码",
            type: "input",
            inputType: "password",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "password"
        },
        function (pwd) {
            if (account.paymentPassword !== pwd) {
                swal.showInputError("支付密码错误");
            }
            else {
                var orderId = localStorage.getItem("selectedOrderId");
                $.ajax({
                    type: 'POST',
                    url:"/customerOrder/payOrder",
                    data: {
                        orderId : orderId
                    },
                    success:function(result){
                        if(result){
                            swal({
                                title: "支付成功",
                                text: "支付成功",
                                type: "success"
                            },function () {
                                window.location.reload();
                            });
                        }else
                            swal("支付失败", "商品数量不足或者银行账户余额不足", "error");
                    },
                    error:function(){
                        alert("error");
                    }
                });
            }
        });
}

function cancelOrder() {
    swal({
            title: "确定取消此订单吗",
            text: "点击确认取消订单",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function () {
            var orderId = localStorage.getItem("selectedOrderId");
            $.ajax({
                type: 'POST',
                url:"/customerOrder/cancelOrder",
                data: {
                    orderId : orderId
                },
                success:function(result) {
                    if(result === -1){
                        swal({
                            title: "退订成功",
                            text: "退订成功",
                            type: "success"
                        },function () {
                            window.location.reload();
                        });
                    }else if(result === -2){
                        swal("退订失败", "退订失败", "error");
                    }else{
                        swal({
                            title: "退订成功",
                            text: "退订成功，退还金额: " + result + " 元。",
                            type: "success"
                        },function () {
                            window.location.reload();
                        });
                    }
                },
                error:function(){
                    alert("error");
                }
            });
        });
}