function setTotalIncome() {
    var restaurantId = localStorage.getItem("restaurantId");
    var totalPay = showUserAccount(restaurantId)[0].balance;
    totalPay = Math.round(totalPay * 100) / 100.0;
    document.getElementById("totalIncome").value = "总收入：" + totalPay + " 元";
}

function setCustomerOrderReturnList() {
    clearCustomerOrderList();
    var restaurantId = localStorage.getItem("restaurantId");
    var store = showRestaurantOrders(restaurantId);
    var mapStore = {};
    for(var i = 0; i < store.length; i++) {
        if(store[i].orderCondition === "已取消") {
            if(mapStore.hasOwnProperty(store[i].customerId))
                mapStore[store[i].customerId] = mapStore[store[i].customerId] + 1;
            else
                mapStore[store[i].customerId] = 1;
        }
    }
    for(var key in mapStore){
        if(mapStore.hasOwnProperty(key)){
            var customerName = findCustomerInfoById(key).customerName;
            addCustomerOrderListRow(key, customerName, mapStore[key]);
        }
    }
}

function setCustomerOrderList() {
    clearCustomerOrderList();
    var restaurantId = localStorage.getItem("restaurantId");
    var store = showRestaurantOrders(restaurantId);
    var mapStore = {};
    for(var i = 0; i < store.length; i++) {
        if(store[i].orderCondition === "已完成" || store[i].orderCondition === "送货中") {
            if(mapStore.hasOwnProperty(store[i].customerId))
                mapStore[store[i].customerId] = mapStore[store[i].customerId] + 1;
            else
                mapStore[store[i].customerId] = 1;
        }
    }
    for(var key in mapStore) {
        if(mapStore.hasOwnProperty(key)){
            var customerName = findCustomerInfoById(key).customerName;
            addCustomerOrderListRow(key, customerName, mapStore[key]);
        }
    }
}

function setOrderListSortByTime() {
    clearOrderList();
    var store = showRestaurantOrders(localStorage.getItem("restaurantId"));
    for(var i = store.length - 1; i >= 0; i--) {
        if(store[i].orderCondition === "已完成" || store[i].orderCondition === "送货中") {
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
            addOrderListRow(orderId, orderTime, content, total, condition);
        }
    }
}

function setOrderReturnListSortByTime() {
    clearOrderList();
    var store = showRestaurantOrders(localStorage.getItem("restaurantId"));
    for(var i = store.length - 1; i >= 0; i--) {
        if(store[i].orderCondition === "已取消") {
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
            addOrderListRow(orderId, orderTime, content, total, condition);
        }
    }
}

function setOrderListSortByMoney() {
    clearOrderList();
    var store = showRestaurantOrders(localStorage.getItem("restaurantId"));
    for(var j = 0; j < store.length - 1; j++){
        for(var k = j + 1; k < store.length; k++){
            if(store[j].totalPrice > store[k].totalPrice){
                var tmp = store[j];
                store[j] = store[k];
                store[k] = tmp;
            }
        }
    }
    for(var i = store.length - 1; i >= 0; i--) {
        if(store[i].orderCondition === "已完成" || store[i].orderCondition === "送货中") {
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
            addOrderListRow(orderId, orderTime, content, total, condition);
        }
    }
}

function setOrderReturnListSortByMoney() {
    clearOrderList();
    var store = showRestaurantOrders(localStorage.getItem("restaurantId"));
    for(var j = 0; j < store.length - 1; j++){
        for(var k = j + 1; k < store.length; k++){
            if(store[j].totalPrice > store[k].totalPrice){
                var tmp = store[j];
                store[j] = store[k];
                store[k] = tmp;
            }
        }
    }
    for(var i = store.length - 1; i >= 0; i--) {
        if(store[i].orderCondition === "已取消") {
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
            addOrderListRow(orderId, orderTime, content, total, condition);
        }
    }
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

function addCustomerOrderListRow(id, name, num) {
    var table = document.getElementById("customerOrderList");
    var row = document.createElement("tr");

    var idCell = document.createElement('td');
    idCell.style.textAlign='center';
    idCell.style.display='none';
    idCell.innerHTML = id;
    row.appendChild(idCell);

    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = name;
    row.appendChild(nameCell);

    var numCell = document.createElement('td');
    numCell.style.textAlign='center';
    numCell.innerHTML = num;
    row.appendChild(numCell);

    var opCell = document.createElement('td');
    var check = document.createElement('a');
    check.innerHTML = '查看详情';
    check.className = 'opBtn';
    check.href = 'javascript:void(0);';
    check.onclick = function() {
        checkRestaurantOrder(this);
    };
    opCell.appendChild(check);
    row.appendChild(opCell);

    table.appendChild(row);
}

function clearCustomerOrderList() {
    var table = document.getElementById("customerOrderList");
    var trs = table.rows;
    for(var i = trs.length - 1; i > 0; i--)
        table.deleteRow(i);
}

function clearOrderList() {
    var table = document.getElementById("orderList");
    var trs = table.rows;
    for(var i = trs.length - 1; i > 0; i--)
        table.deleteRow(i);
}

function checkOrder(a) {
    var tr = a.parentNode.parentNode;
    var orderId = tr.cells[0].innerText;
    localStorage.setItem("restaurantStatisticsOrderId", orderId);
    window.location.href="restaurant-statisticsDetail";
}

function checkRestaurantOrder(a) {
    var tr = a.parentNode.parentNode;
    var restaurantId = tr.cells[0].innerText;
    localStorage.setItem("restaurantStaResId", restaurantId);
    var conditionOne = document.getElementById("filterCondition-one").value;
    localStorage.setItem("resStaConditionOne",conditionOne);
    window.location.href="restaurant-staResDetail";
}