var customerAddresses = [];

function saveDeliveryAddress(address) {
    var res = false;
    $.ajax({
        type: 'POST',
        async: false,                         //将ajax改为同步模式
        url:"/customerInfo/saveDeliveryAddress",
        contentType: "application/json",
        data: JSON.stringify(address),
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}

function addAddress() {
    swal({
            title: "新增地址",
            text: "请输入地址",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "address"
        },
        function(newAddress){
            if(newAddress === false)
                return;
            if(newAddress === "")
                swal.showInputError("请输入地址");
            else {
                for(var i = 0; i < customerAddresses.length; i++){
                    if(newAddress === customerAddresses[i].address){
                        swal("新增地址失败", "地址重复", "error");
                        return;
                    }
                }
                var oneAddress = new Address(localStorage.getItem("customerId"), newAddress, 0, 0);
                var res = saveDeliveryAddress(oneAddress);
                if(res){
                    swal({
                        title: "新增地址成功",
                        text: "地址信息已更新",
                        type: "success"
                    },function () {
                        window.location.reload();
                    });
                }else{
                    swal("新增地址失败","地址信息未更新","error");
                }
            }
        });
}

function addRow(address) {
    var table = document.getElementById("addressTable");
    var row = document.createElement("tr");

    var addressCell = document.createElement('td');
    addressCell.style.textAlign='center';
    addressCell.innerHTML = address;
    row.appendChild(addressCell);

    var opCell = document.createElement('td');
    var edit = document.createElement('a');
    edit.innerHTML = '编辑';
    edit.className = 'opBtn';
    edit.href = 'javascript:void(0);';
    edit.onclick = function() {
        editAddress(this);
    };
    var del = document.createElement('a');
    del.innerHTML = '删除';
    del.className = 'opBtn';
    del.href = 'javascript:void(0);';
    del.onclick = function() {
        delAddress(this);
    };
    opCell.appendChild(edit);
    opCell.appendChild(del);
    row.appendChild(opCell);

    table.appendChild(row);
}

function editAddress(a) {
    var tr = a.parentNode.parentNode;
    var oldAddress = tr.cells[0].innerText;
    swal({
            title: "修改地址",
            text: "请输入新地址",
            type: "input",
            showCancelButton: true,
            closeOnConfirm: false,
            animation: "slide-from-top",
            inputPlaceholder: "new address"
        },
        function (newAddress) {
            if(newAddress === false)
                return;
            if(newAddress === "")
                swal.showInputError("请输入地址");
            else{
                for(var i = 0; i < customerAddresses.length; i++){
                    if(newAddress === customerAddresses[i].address){
                        swal("新增地址失败", "地址重复", "error");
                        return;
                    }
                }
                $.ajax({
                    type: 'POST',
                    url:"/customerInfo/updateDeliveryAddress",
                    data: {
                        customerId : localStorage.getItem("customerId"),
                        oldAddress : oldAddress,
                        newAddress : newAddress
                    },
                    success:function(result){
                        if(result){
                            swal({
                                title: "修改地址成功",
                                text: "地址信息已更新",
                                type: "success"
                            },function () {
                                window.location.reload();
                            });
                        }else
                            swal("新增地址失败","地址信息未更新","error");

                    },
                    error:function(){
                        alert("error");
                    }
                });
            }
        });
}

function delAddress(a) {
    swal({
            title: "确定删除该地址吗?",
            text: "点击确认进行删除操作",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            var tr = a.parentNode.parentNode;
            var address = tr.cells[0].innerText;
            $.ajax({
                type: 'POST',
                url:"/customerInfo/deleteDeliveryAddress",
                data: {
                    customerId : localStorage.getItem("customerId"),
                    address : address
                },
                success:function(result){
                    if(result){
                        swal({
                            title: "删除地址成功",
                            text: "地址信息已更新",
                            type: "success"
                        },function () {
                            window.location.reload();
                        });
                    }else
                        swal("删除地址失败","地址信息未更新","error");
                },
                error:function(){
                    alert("error");
                }
            });
        });
}

function showDeliveryAddress() {
    $.ajax({
        type: 'POST',
        url:"/customerInfo/showDeliveryAddress",
        data: {
            customerId : localStorage.getItem("customerId")
        },
        success:function(result){
            customerAddresses = result;
            for(var i = 0; i < result.length; i++)
                addRow(result[i].address);
        },
        error:function(){
            alert("error");
        }
    });
}

function findCustomerAddressById(customerId) {
    var res = [];
    $.ajax({
        type: 'POST',
        url:"/customerInfo/showDeliveryAddress",
        async: false,                         //将ajax改为同步模式
        data: {
            customerId : customerId
        },
        success:function(result){
            res = result;
        },
        error:function(){
            alert("error");
        }
    });
    return res;
}