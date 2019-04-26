function setCommodity(restaurantId) {
    var commodityStore = showCommodity(restaurantId);
    var discountInfoStore = showDiscountInfo(restaurantId);
    for(var i = 0; i < commodityStore.length; i++){
        var beginDate = commodityStore[i].beginDate;
        beginDate = beginDate.substring(0, beginDate.indexOf("T")) + " " +
            beginDate.substring(beginDate.indexOf("T") + 1, beginDate.indexOf("."));
        var endDate = commodityStore[i].endDate;
        endDate = endDate.substring(0, endDate.indexOf("T")) + " " +
            endDate.substring(endDate.indexOf("T") + 1, endDate.indexOf("."));
        var name = commodityStore[i].commodityName;
        var type = commodityStore[i].type;
        var price = commodityStore[i].commodityPrice;
        var amount = commodityStore[i].leftAmount;
        var describe = "";
        var mapStore = {};
        var discount = "无";
        var discountAmount = "无";
        for(var k = 0; k < commodityStore[i].items.length; k++){
            var oneItem = commodityStore[i].items[k];
            if(mapStore.hasOwnProperty(oneItem.itemName))
                mapStore[oneItem.itemName] = mapStore[oneItem.itemName] + 1;
            else
                mapStore[oneItem.itemName] = 1;
        }
        for(var key in mapStore){
            if(mapStore.hasOwnProperty(key)){
                describe += key + "；";
            }
        }
        for(var j = 0; j < discountInfoStore.length; j++){
            if(discountInfoStore[j].commodityName === name){
                discount = discountInfoStore[j].discount + "折";
                discountAmount = discountInfoStore[j].leftAmount;
                break;
            }
        }
        addCommodityRow(beginDate, endDate, name, type, price, amount,
                        describe, discount, discountAmount);
    }
}

function addCommodityRow(begin, end, name, type, price, amount, describe, discount, discountAmount) {
    var table = document.getElementById("commodityTable");
    var row = document.createElement("tr");

    var beginCell = document.createElement('td');
    beginCell.style.textAlign='center';
    beginCell.innerHTML = begin;
    row.appendChild(beginCell);

    var endCell = document.createElement('td');
    endCell.style.textAlign='center';
    endCell.innerHTML = end;
    row.appendChild(endCell);

    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = name;
    row.appendChild(nameCell);

    var typeCell = document.createElement('td');
    typeCell.style.textAlign='center';
    typeCell.innerHTML = type;
    row.appendChild(typeCell);

    var priceCell = document.createElement('td');
    priceCell.style.textAlign='center';
    priceCell.innerHTML = price;
    row.appendChild(priceCell);

    var amountCell = document.createElement('td');
    amountCell.style.textAlign='center';
    amountCell.innerHTML = amount;
    row.appendChild(amountCell);

    var describeCell = document.createElement('td');
    describeCell.style.textAlign='left';
    describeCell.innerHTML = describe;
    row.appendChild(describeCell);

    var discountCell = document.createElement('td');
    discountCell.style.textAlign='center';
    discountCell.innerHTML = discount;
    row.appendChild(discountCell);

    var discountAmountCell = document.createElement('td');
    discountAmountCell.style.textAlign='center';
    discountAmountCell.innerHTML = discountAmount;
    row.appendChild(discountAmountCell);

    table.appendChild(row);
}