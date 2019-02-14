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
            inputPlaceholder: "address",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showLoaderOnConfirm: true
        },
        function(newAddress){
            var oneAddress = new Address(localStorage.getItem("customerId"), newAddress, 0, 0);
            var res = saveDeliveryAddress(oneAddress);
            if(res){
                addRow(newAddress);
                setTimeout(function(){
                    swal("新增地址成功","地址信息已更新","success");
                }, 1200);
            }else{
                setTimeout(function(){
                    swal("新增地址失败","地址信息未更新","error");
                }, 1200);
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

    var checkCell = document.createElement('td');
    var check = document.createElement('a');
    check.innerHTML = '编辑';
    check.className = 'checkBtn';
    check.href = '#';
    check.onclick = function() {
        addressEdit(this);
    };
    checkCell.appendChild(check);
    row.appendChild(checkCell);

    table.appendChild(row);
}

function addressEdit(a) {
    var tr = a.parentNode.parentNode;
    var address = tr.cells[1].innerText;
    localStorage.setItem("selectAddress", address);
    window.location.href = 'customer-addressDetail';
    window.event.returnValue = false;
}

function showDeliveryAddress() {
    $.ajax({
        type: 'POST',
        url:"/customerInfo/showDeliveryAddress",
        data: {
            customerId : localStorage.getItem("customerId")
        },
        success:function(result){
            for(var i = 0; i < result.length; i++)
                addRow(result[i].address);
        },
        error:function(){
            alert("error");
        }
    });
}