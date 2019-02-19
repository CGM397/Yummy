function showRestaurantModification() {
    var store = findRestaurantModification(localStorage.getItem("restaurantId"));
    for(var i = 0; i < store.length; i++){
        var condition = "已同意";
        if(!store[i].checked)
            condition = "审核中";
        else if(!store[i].approve)
            condition = "已拒绝";
        addModificationRow(store[i].modificationDate, store[i].modifiedName, store[i].modifiedType,
                           store[i].modifiedAddress, condition);
    }
}

function addModificationRow(date, name, type, address, condition) {
    var table = document.getElementById("modificationTable");
    var row = document.createElement("tr");

    var dateCell = document.createElement('td');
    dateCell.style.textAlign='center';
    dateCell.innerHTML = date;
    row.appendChild(dateCell);

    var modificationCell = document.createElement('td');
    var nameCell = document.createElement('p');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = "餐厅名称: " + name;
    modificationCell.appendChild(nameCell);

    var typeCell = document.createElement('p');
    typeCell.style.textAlign='center';
    typeCell.innerHTML = "餐厅类型: " + type;
    modificationCell.appendChild(typeCell);

    var addressCell = document.createElement('p');
    addressCell.style.textAlign='center';
    addressCell.innerHTML = "地址: " + address;
    modificationCell.appendChild(addressCell);
    row.appendChild(modificationCell);

    var conditionCell = document.createElement('td');
    conditionCell.style.textAlign='center';
    conditionCell.innerHTML = condition;
    row.appendChild(conditionCell);
    table.appendChild(row);
}