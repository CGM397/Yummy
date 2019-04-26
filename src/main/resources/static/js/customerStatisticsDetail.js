function setPageInfo() {
    var orderId = localStorage.getItem("customerStatisticsOrderId");
    var orderInfo = findOneOrderById(orderId);

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