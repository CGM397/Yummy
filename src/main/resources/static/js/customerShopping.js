function showRestaurantCommodity(restaurantId) {
    var commodityStore = showCommodity(restaurantId);
    var discountInfoStore = showDiscountInfo(restaurantId);
    for(var i = 0; i < commodityStore.length; i++){
        var name = commodityStore[i].commodityName;
        var price = commodityStore[i].commodityPrice;
        var amount = commodityStore[i].amount;
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
                describe += key + " " + mapStore[key] + "份；";
            }
        }
        for(var j = 0; j < discountInfoStore.length; j++){
            if(discountInfoStore[j].commodityName === name){
                discount = discountInfoStore[j].discount + "折";
                discountAmount = discountInfoStore[j].discountAmount;
                break;
            }
        }
        addCommodityRow(name, price, amount, describe, discount, discountAmount);
    }
}

function addCommodityRow(name, price, amount, describe, discount, discountAmount) {
    var table = document.getElementById("commodityTable");
    var row = document.createElement("tr");

    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = name;
    row.appendChild(nameCell);

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

    var opCell = document.createElement('td');
    var addOne = document.createElement('a');
    addOne.innerHTML = '购买';
    addOne.className = 'opBtn';
    addOne.href = 'javascript:void(0);';
    addOne.onclick = function() {
        addOneCommodity(this);
    };
    opCell.appendChild(addOne);
    row.appendChild(opCell);

    table.appendChild(row);
}

function addOneCommodity(a) {
    var tr=a.parentNode.parentNode;
    var commodityName = tr.cells[0].innerText;
    var commodityAmount = parseInt(tr.cells[2].innerText);
    var discountAmount = tr.cells[5].innerText;
    var buyDiscountAmount = 0;
    if(commodityAmount < 1){
        swal("购买失败","商品数量不足","error");
        return;
    }
    tr.cells[2].innerHTML = commodityAmount - 1;
    if(discountAmount !== "无" && parseInt(discountAmount) > 0){
        tr.cells[5].innerHTML = parseInt(discountAmount) - 1;
        buyDiscountAmount++;
    }
    addShoppingCartRow(commodityName, buyDiscountAmount);
}

function addShoppingCartRow(name, buyDiscountAmount) {
    var table = document.getElementById("shoppingCartTable");
    var trs = table.rows;
    for(var i = 0; i < trs.length; i++){
        if(trs[i].cells[0].innerText === name){
            trs[i].cells[1].innerHTML = parseInt(trs[i].cells[1].innerText) + 1;
            trs[i].cells[2].innerHTML = parseInt(trs[i].cells[2].innerText) + buyDiscountAmount;
            return;
        }
    }

    var row = document.createElement("tr");
    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = name;
    row.appendChild(nameCell);

    var amountCell = document.createElement('td');
    amountCell.style.textAlign='center';
    amountCell.innerHTML = "1";
    row.appendChild(amountCell);

    var discountAmountCell = document.createElement('td');
    discountAmountCell.style.textAlign='center';
    discountAmountCell.style.display='none';
    discountAmountCell.innerHTML = buyDiscountAmount;
    row.appendChild(discountAmountCell);

    var opCell = document.createElement('td');
    var deleteOne = document.createElement('a');
    deleteOne.innerHTML = '删除';
    deleteOne.className = 'opBtn';
    deleteOne.href = 'javascript:void(0);';
    deleteOne.onclick = function() {
        deleteOneCommodity(this);
    };
    opCell.appendChild(deleteOne);
    row.appendChild(opCell);

    table.appendChild(row);
}

function deleteOneCommodity(a) {
    var tr=a.parentNode.parentNode;
    var commodityName = tr.cells[0].innerText;
    var commodityAmount = parseInt(tr.cells[1].innerText);
    var discountAmount = parseInt(tr.cells[2].innerText);
    var isDiscount = (commodityAmount === discountAmount);
    if(commodityAmount > 1){
        if(isDiscount)
            tr.cells[2].innerHTML = discountAmount - 1;
        tr.cells[1].innerHTML = commodityAmount - 1;
    }
    else{
        var shoppingCartTable = document.getElementById("shoppingCartTable");
        shoppingCartTable.deleteRow(tr.rowIndex);
    }

    var table = document.getElementById("commodityTable");
    var trs = table.rows;
    for(var i = 0; i < trs.length; i++){
        if(trs[i].cells[0].innerText === commodityName) {
            trs[i].cells[2].innerHTML = parseInt(trs[i].cells[2].innerText) + 1;
            if(isDiscount)
                trs[i].cells[5].innerHTML = parseInt(trs[i].cells[5].innerText) + 1;
            break;
        }
    }
}