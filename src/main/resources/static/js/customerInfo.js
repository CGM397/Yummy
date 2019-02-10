function findCustomerInfoByMail(customerMail) {
    var currentCustomer = new Customer();
    $.ajax({
        type: "POST",
        url: "/customerInfo/findCustomerInfoByMail",
        async: false,                         //将ajax改为同步模式
        data: {
            customerMail : customerMail
        },
        success: function (result) {
            currentCustomer = new Customer(result.customerId,
                customerMail,
                result.customerPassword,
                result.customerName,
                result.phoneNumber,
                result.vipLevel,
                result.active);
        },
        error: function () {
            alert("register : error!");
        }
    });
    return currentCustomer;
}

function modifyPassword() {
    
}

function modifyInfo() {
    var info = {

    };
    swal({
            title: "确定修改您的个人信息吗",
            text: "点击确认进行修改",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false,
            showLoaderOnConfirm: true
        },
        function(){
            $.ajax({
                type: 'POST',
                url:"/customerInfo/updateCustomerInfo",
                contentType: "application/json",
                data: JSON.stringify(info),
                success:function(result){
                },
                error:function(){
                    alert("error");
                }
            });
            setTimeout(function(){
                swal("修改成功","个人信息已更新","success");
            }, 1500);
        });
}

function delAccount() {
    
}