function setOrderList() {
    var store = showRestaurantOrders(localStorage.getItem("restaurantId"));
    for(var i = store.length - 1; i >= 0; i--) {
        var orderId = store[i].orderId;
        var orderTime = store[i].orderTime;
        orderTime = orderTime.substring(0, orderTime.indexOf("T")) + " " +
            orderTime.substring(orderTime.indexOf("T") + 1, orderTime.indexOf("."));
        var content = "";
        var total = store[i].totalPrice;
        var condition = store[i].orderCondition;
        var restaurantName = localStorage.getItem("restaurantName");
        var itemName = store[i].items[0].itemName;
        var itemsAmount = store[i].items.length;
        content = restaurantName + ": " + itemName + "等" + itemsAmount + "种商品";
        addOrderList(orderId, orderTime, content, total, condition);
    }
}

function addOrderList(orderId, time, content, total, condition) {
    var table = document.getElementById("orderList");
    var row = document.createElement("tr");

    var idCell = document.createElement('td');
    idCell.style.textAlign='center';
    idCell.style.display='none';
    idCell.innerHTML = orderId;
    row.appendChild(idCell);

    var timeCell = document.createElement('td');
    timeCell.style.textAlign='center';
    timeCell.innerHTML = time;
    row.appendChild(timeCell);

    var contentCell = document.createElement('td');
    contentCell.style.textAlign='left';
    contentCell.innerHTML = content;
    row.appendChild(contentCell);

    var totalCell = document.createElement('td');
    totalCell.style.textAlign='center';
    totalCell.innerHTML = total;
    row.appendChild(totalCell);

    var conditionCell = document.createElement('td');
    conditionCell.style.textAlign='center';
    conditionCell.innerHTML = condition;
    row.appendChild(conditionCell);

    var opCell = document.createElement('td');
    var check = document.createElement('a');
    check.innerHTML = '查看详情';
    check.className = 'opBtn';
    check.href = 'javascript:void(0);';
    check.onclick = function() {
        checkOrder(this);
    };
    opCell.appendChild(check);
    row.appendChild(opCell);

    table.appendChild(row);
}

function checkOrder(a) {
    var tr=a.parentNode.parentNode;
    var orderId = tr.cells[0].innerText;
    localStorage.setItem("restaurantSelectedOrderId", orderId);
    window.location.href="restaurant-orderDetail";
}