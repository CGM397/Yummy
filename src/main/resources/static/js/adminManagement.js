var storeUnchecked = [];
function showUncheckedModification() {
    var store = showModification();
    for(var i = 0; i < store.length; i++){
        if(!store[i].checked) {
            storeUnchecked.push(store[i]);
            addUncheckedModificationRow(store[i].restaurantId, store[i].modifiedName,
                                        store[i].modifiedType, store[i].modifiedAddress);
        }
    }
}

function addUncheckedModificationRow(date, name, type, address) {
    var table = document.getElementById("modificationTable");
    var row = document.createElement("tr");

    var idCell = document.createElement('td');
    idCell.style.textAlign='center';
    idCell.innerHTML = date;
    row.appendChild(idCell);

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

    var opCell = document.createElement('td');
    var approve = document.createElement('a');
    approve.innerHTML = '同意';
    approve.className = 'opBtn';
    approve.href = 'javascript:void(0);';
    approve.onclick = function() {
        adminApprove(this);
    };
    var refuse = document.createElement('a');
    refuse.innerHTML = '拒绝';
    refuse.className = 'opBtn';
    refuse.href = 'javascript:void(0);';
    refuse.onclick = function() {
        adminRefuse(this);
    };
    opCell.appendChild(approve);
    opCell.appendChild(refuse);
    row.appendChild(opCell);
    
    table.appendChild(row);
}

function adminApprove(a) {
    swal({
            title: "确定同意此修改申请吗?",
            text: "点击确认将会更新餐厅信息",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            var tr = a.parentNode.parentNode;
            var restaurantId = tr.cells[0].innerText;
            var currentMod = new Modification();
            for(var i = 0; i < storeUnchecked.length; i++){
                if(storeUnchecked[i].restaurantId === restaurantId){
                    currentMod = storeUnchecked[i];
                    currentMod.checked = true;
                    currentMod.approve = true;
                    break;
                }
            }
            var currentRes = findRestaurantInfoById(restaurantId);
            var currentAdd = showRestaurantAddress(restaurantId);
            var modifiedRes = currentRes;
            if(currentMod.modifiedType !== "无修改")
                modifiedRes.restaurantType = currentMod.modifiedType;
            if(currentMod.modifiedName !== "无修改")
                modifiedRes.restaurantName = currentMod.modifiedName;
            if(updateModification(currentMod) && updateRestaurantInfo(modifiedRes)){
                var res = false;
                if(currentMod.modifiedAddress !== "无修改"){
                    if(currentAdd.address === null)
                        res = saveRestaurantAddress(new Address(restaurantId,
                                                                currentMod.modifiedAddress, 0, 0));
                    else
                        res = updateRestaurantAddress(restaurantId, currentAdd.address,
                                                      currentMod.modifiedAddress);
                }else
                    res = true;
                if(!res){
                    swal("修改餐厅信息失败","餐厅信息未更新","error");
                    return;
                }
                swal({
                    title: "修改餐厅信息成功",
                    text: "餐厅信息已更新",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }else
                swal("修改餐厅信息失败","餐厅信息未更新","error");
        });
}

function adminRefuse(a) {
    swal({
            title: "确定拒绝此修改申请吗?",
            text: "点击确认将会拒绝此修改申请",
            type: "warning",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            var tr = a.parentNode.parentNode;
            var restaurantId = tr.cells[0].innerText;
            var currentMod = new Modification();
            for(var i = 0; i < storeUnchecked.length; i++){
                if(storeUnchecked[i].restaurantId === restaurantId){
                    currentMod = storeUnchecked[i];
                    currentMod.checked = true;
                    currentMod.approve = false;
                    break;
                }
            }
            if(updateModification(currentMod)){
                swal({
                    title: "拒绝修改信息成功",
                    text: "餐厅信息未改变",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }else
                swal("拒绝修改信息失败","餐厅信息未改变","error");
        });
}