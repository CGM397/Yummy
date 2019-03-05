function setPageInfo() {
    var customerId = localStorage.getItem("restaurantStaCusId");
    var conditionOne = localStorage.getItem("resStaConditionOne");
    document.getElementById("staCusName").value =
                                        findCustomerInfoById(customerId).customerName;
    var restaurantId = localStorage.getItem("restaurantId");
    var restaurantName = localStorage.getItem("restaurantName");
    var store = showRestaurantOrders(restaurantId);
    for(var i = store.length - 1; i >= 0; i--){
        if(store[i].customerId === customerId &&
            isSelectedCondition(store[i].orderCondition, conditionOne)) {
            var orderId = store[i].orderId;
            var orderTime = store[i].orderTime;
            orderTime = orderTime.substring(0, orderTime.indexOf("T")) + " " +
                orderTime.substring(orderTime.indexOf("T") + 1, orderTime.indexOf("."));
            var content = "";
            var total = store[i].totalPrice;
            var condition = store[i].orderCondition;
            var itemName = store[i].items[0].itemName;
            var itemsAmount = store[i].items.length;
            content = restaurantName + ": " + itemName + "等" + itemsAmount + "种商品";
            addOrderListRow(orderId, orderTime, content, total, condition);
        }
    }
}

function isSelectedCondition(orderCondition, conditionOne) {
    if(conditionOne === "购买记录") {
        if(orderCondition === "已完成" || orderCondition === "送货中")
            return true;
    }
    else if(conditionOne === "退订记录") {
        if(orderCondition === "已取消")
            return true;
    }
    return false;
}

function addOrderListRow(orderId, time, content, total, condition) {
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
    var tr = a.parentNode.parentNode;
    var orderId = tr.cells[0].innerText;
    localStorage.setItem("restaurantStatisticsOrderId", orderId);
    window.location.href="restaurant-staCusItemDetail";
}