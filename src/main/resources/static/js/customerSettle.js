var fastest = new Date();

function setPageInfo() {
    var currentOrder = JSON.parse(localStorage.getItem("currentOrderInfo"));
    var currentCustomer = findCustomerInfoByMail(localStorage.getItem("customerMail"));
    var totalPrice = currentOrder.totalPrice;
    totalPrice = totalPrice * (1 - (currentCustomer.vipLevel / 100.0));
    totalPrice = Math.round(totalPrice * 100) /100.0;
    var items = currentOrder.items;
    document.getElementById("delivery-address").value = currentOrder.deliveryAddress;
    document.getElementById("total-price").value = totalPrice +
                                        "(会员等级优惠，立减: " + currentCustomer.vipLevel +"%)";
    for(var i = 0; i < items.length; i++){
        var name = items[i].itemName;
        var amount = items[i].amount;
        var subTotal = items[i].subTotal;
        addShoppingCartRow(name, amount, subTotal);
    }
    setFastestDeliveryTime(currentOrder.restaurantId, currentOrder.customerId,
                           currentOrder.deliveryAddress);
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

function setFastestDeliveryTime(restaurantId, customerId, customerAddress) {
    var x1 = showRestaurantAddress(restaurantId).coordinateX;
    var y1 = showRestaurantAddress(restaurantId).coordinateY;
    var cusAddStore = findCustomerAddressById(customerId);
    var x2 = 0, y2 = 0;
    for(var i = 0; i < cusAddStore.length; i++){
        if(cusAddStore[i].address === customerAddress){
            x2 = cusAddStore[i].coordinateX;
            y2 = cusAddStore[i].coordinateY;
            break;
        }
    }
    var fastestDeliveryTime = Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
    fastestDeliveryTime = Math.round(fastestDeliveryTime / 10.0);
    var time = new Date();
    time.setMinutes(time.getMinutes() + fastestDeliveryTime);
    var month = ("0" + (time.getMonth() + 1)).slice(-2);
    var day = ("0" + time.getDate()).slice(-2);
    var hour = ("0" + time.getHours()).slice(-2);
    var min = ("0" + time.getMinutes()).slice(-2);
    var now = time.getFullYear() + "-" + month + "-" + day + "T" + hour + ":" + min;
    fastest = now;
    document.getElementById("delivery-time").value = now;
    document.getElementById("delivery-time").min = now;
}
function confirmOrder() {
    var currentOrder = JSON.parse(localStorage.getItem("currentOrderInfo"));
    var time = new Date();
    var month = ("0" + (time.getMonth() + 1)).slice(-2);
    var day = ("0" + time.getDate()).slice(-2);
    var hour = ("0" + time.getHours()).slice(-2);
    var min = ("0" + time.getMinutes()).slice(-2);
    var sec = ("0" + time.getSeconds()).slice(-2);
    document.getElementById("order-time").value =
        time.getFullYear() + "-" + month + "-" + day + "T" + hour + ":" + min + ":" + sec;
    currentOrder.orderTime = document.getElementById("order-time").value;
    currentOrder.deliveryTime = document.getElementById("delivery-time").value;
    var tmp1 = new Date(Date.parse(currentOrder.deliveryTime));
    var tmp2 = new Date(Date.parse(fastest));
    var tmp3 = new Date(Date.parse(fastest));
    tmp3.setMinutes(tmp3.getMinutes() + 60);
    if(tmp1.getTime() < tmp2.getTime() || tmp1.getTime() > tmp3.getTime()) {
        swal("下单失败","请将送达时间设置在给定最快时间之后的一个小时内","error");
        return;
    }
    var totalPrice = document.getElementById("total-price").value;
    totalPrice = totalPrice.substring(0, totalPrice.indexOf("("));
    currentOrder.totalPrice = totalPrice;
    $.ajax({
        type: 'POST',
        url:"/customerShopping/confirmOrder",
        contentType: "application/json",
        data: JSON.stringify(currentOrder),
        success:function(result){
            if(result){
                swal({
                    title: "下单成功",
                    text: "请于2分钟内到订单页面付款",
                    type: "success"
                },function () {
                    window.location.href='customer-order';
                });
            }else
                swal("下单失败","下单失败","error");
        },
        error:function(){
            alert("error");
        }
    });
}