function release() {
    var infoType = document.getElementById("info-type").value;
    if(infoType === "单品")
        releaseSingleProduct();
    else if(infoType === "套餐")
        releasePackage();
    else if(infoType === "优惠")
        releaseDiscount();
}

function releaseSingleProduct() {
    var commodityName = document.getElementById("commodity-name").value;
    var commodityPrice = document.getElementById("commodity-price").value;
    var commodityAmount = document.getElementById("commodity-amount").value;
    var commodityDuration = document.getElementById("commodity-duration").value;
    var restaurantId = localStorage.getItem("restaurantId");
    swal({
            title: "确定发布该信息吗",
            text: "点击确认进行发布",
            type: "warning",
            cancelButtonText:"取消",
            confirmButtonText:"确认",
            showCancelButton: true,
            closeOnConfirm: false
        },
        function(){
            if(commodityName === ""){
                swal("发布失败","商品名称不可为空","error");
                return;
            }else if(!isNonNegativeDouble(commodityPrice) ||
                     !isPositiveInteger(commodityAmount) ||
                     !isPositiveInteger(commodityDuration)){
                swal("发布失败","商品价格、数量或者持续时间填写格式有误","error");
                return;
            }
            var commodityItem = new CommodityItem(restaurantId, commodityName, commodityPrice);
            var commodityInfo = new CommodityInfo(restaurantId, commodityName, "单品", commodityPrice,
                                            commodityAmount, commodityItem, "date", commodityDuration);
            if(addCommodityInfo(commodityInfo)){
                swal("发布成功", "商品信息已更新", "success");
            }else
                swal("发布失败", "商品信息未更新", "error");

        });
}

function releasePackage() {
    
}

function releaseDiscount() {

}

function setCommodity() {

}