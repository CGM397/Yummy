function setCustomerAddress() {
    document.getElementById("customer-address").options.length = 0;
    $.ajax({
        type: 'POST',
        url:"/customerInfo/showDeliveryAddress",
        data: {
            customerId : localStorage.getItem("customerId")
        },
        success:function(result){
            var select = document.getElementById("customer-address");
            if(result === null || result.length === 0){
                swal("暂无地址", "请尽快去收货地址页面添加地址", "warning");
                select.options.add(new Option("暂无地址"));
            }else{
                for(var i = 0; i < result.length; i++)
                    select.options.add(new Option(result[i].address));
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function updateRestaurantList() {
    var currentAddress = document.getElementById("customer-address").value;
    alert(currentAddress);
}