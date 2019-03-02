var packageItems = [];

function getNow() {
    var time = new Date();
    var month = ("0" + (time.getMonth() + 1)).slice(-2);
    var day = ("0" + time.getDate()).slice(-2);
    var hour = ("0" + time.getHours()).slice(-2);
    var min = ("0" + time.getMinutes()).slice(-2);
    return time.getFullYear() + "-" + month + "-" + day + "T" + hour + ":" + min;
}

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
    var commodityBeginDate = document.getElementById("commodity-beginDate").value;
    var commodityEndDate = document.getElementById("commodity-endDate").value;
    var tmp1 = new Date(Date.parse(commodityBeginDate));
    var tmp2 = new Date(Date.parse(commodityEndDate));
    var tmp3 = new Date(Date.parse(getNow()));
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
        function() {
            if(tmp1.getTime() > tmp2.getTime() || tmp1.getTime() < tmp3.getTime()){
                swal("发布失败","结束时间必须大于等于开始时间，开始时间必须大于等于本地时间","error");
                return;
            }
            var store = showCommodity(restaurantId);
            for(var i = 0; i < store.length; i++) {
                if(store[i].commodityName === commodityName){
                    swal("发布失败","商品名称与现有商品名称重复","error");
                    return;
                }
            }
            if(commodityName === ""){
                swal("发布失败","商品名称不可为空","error");
                return;
            }else if(!isNonNegativeDouble(commodityPrice) ||
                     !isPositiveInteger(commodityAmount)){
                swal("发布失败","商品价格或者数量填写格式有误","error");
                return;
            }else if(parseInt(commodityAmount) > 10000){
                swal("发布失败","一次性发布商品数量不得超过10000份","error");
                return;
            }
            var commodityItem = new CommodityItem(restaurantId, commodityName, commodityPrice);
            var items = [];
            items.push(commodityItem);
            var commodityInfo = new CommodityInfo(restaurantId, commodityName, "单品", commodityPrice,
                                            commodityAmount, commodityAmount, items,
                                            commodityBeginDate, commodityEndDate);
            if(addCommodityInfo(commodityInfo)){
                swal({
                    title: "发布成功",
                    text: "商品信息已更新",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }else
                swal("发布失败", "商品信息未更新", "error");

        });
}

function releasePackage() {
    var commodityName = document.getElementById("commodity-name").value;
    var commodityPrice = document.getElementById("commodity-price").value;
    var commodityAmount = document.getElementById("commodity-amount").value;
    var commodityBeginDate = document.getElementById("commodity-beginDate").value;
    var commodityEndDate = document.getElementById("commodity-endDate").value;
    var restaurantId = localStorage.getItem("restaurantId");
    var tmp1 = new Date(Date.parse(commodityBeginDate));
    var tmp2 = new Date(Date.parse(commodityEndDate));
    var tmp3 = new Date(Date.parse(getNow()));
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
            if(tmp1.getTime() > tmp2.getTime() || tmp1.getTime() < tmp3.getTime()){
                swal("发布失败","结束时间必须大于等于开始时间，开始时间必须大于等于本地时间","error");
                return;
            }
            if(commodityName === ""){
                swal("发布失败","商品名称不可为空","error");
                return;
            }else if(!isNonNegativeDouble(commodityPrice) ||
                !isPositiveInteger(commodityAmount)){
                swal("发布失败","商品价格或者数量填写格式有误","error");
                return;
            }else if(packageItems.length < 1){
                swal("发布失败","套餐至少需要一件单品","error");
                return;
            }else if(parseInt(commodityAmount) > 10000){
                swal("发布失败","一次性发布商品数量不得超过10000份","error");
                return;
            }
            var store = showCommodity(restaurantId);
            for(var i = 0; i < store.length; i++) {
                if(store[i].commodityName === commodityName){
                    swal("发布失败","商品名称与现有商品名称重复","error");
                    return;
                }
            }
            var commodityInfo = new CommodityInfo(restaurantId, commodityName, "套餐", commodityPrice,
                commodityAmount, commodityAmount, packageItems, commodityBeginDate, commodityEndDate);
            if(addCommodityInfo(commodityInfo)){
                swal({
                    title: "发布成功",
                    text: "商品信息已更新",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }else
                swal("发布失败", "商品信息未更新", "error");

        });
}

function releaseDiscount() {
    var commodityName = document.getElementById("commodity-select").value;
    var commodityDiscount = document.getElementById("commodity-discount").value;
    var commodityAmount = document.getElementById("commodity-amount").value;
    var commodityBeginDate = document.getElementById("commodity-beginDate").value;
    var commodityEndDate = document.getElementById("commodity-endDate").value;
    var restaurantId = localStorage.getItem("restaurantId");
    var tmp1 = new Date(Date.parse(commodityBeginDate));
    var tmp2 = new Date(Date.parse(commodityEndDate));
    var tmp3 = new Date(Date.parse(getNow()));
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
            if(tmp1.getTime() > tmp2.getTime() || tmp1.getTime() < tmp3.getTime()){
                swal("发布失败","结束时间必须大于等于开始时间，开始时间必须大于等于本地时间","error");
                return;
            }
            if(commodityName === "--please select--"){
                swal("发布失败","商品名称不可为空","error");
                return;
            }else if(!isNonNegativeDouble(commodityDiscount) ||
                !isPositiveInteger(commodityAmount) || commodityDiscount <= 0 ||
                commodityDiscount >= 10){
                swal("发布失败","商品折扣或者数量填写格式有误","error");
                return;
            }
            var store = showCommodity(restaurantId);
            for(var j = 0; j < store.length; j++) {
                if(store[j].commodityName === commodityName){
                    if(parseInt(store[j].amount) < parseInt(commodityAmount)) {
                        swal("发布失败","打折数量已超过该商品现有的数量","error");
                        return;
                    }
                }
            }
            if(commodityDiscount.indexOf(".") > 0){
                if(commodityDiscount.substring(commodityDiscount.indexOf(".") + 1).length > 1){
                    commodityDiscount = parseFloat(commodityDiscount).toFixed(1);
                }
            }
            var discountInfo = new DiscountInfo(restaurantId, commodityName, commodityDiscount,
                commodityAmount, commodityAmount, commodityBeginDate, commodityEndDate);
            if(addDiscountInfo(discountInfo)){
                swal({
                    title: "发布成功",
                    text: "优惠信息已发布",
                    type: "success"
                },function () {
                    window.location.reload();
                });
            }else
                swal("发布失败", "商品信息未更新", "error");

        });
}

function setCommodity() {
    var store = showCommodity(localStorage.getItem("restaurantId"));
    var select = document.getElementById("commodity-select");
    for(var i = 0; i < store.length; i++) {
        addCommodityRow(store[i].commodityName, store[i].type, store[i].commodityPrice,
                        store[i].amount, 0);
        if(!hasDiscount(store[i].commodityName))
            select.options.add(new Option(store[i].commodityName));
    }
}

function hasDiscount(commodityName) {
    var store = showDiscountInfo(localStorage.getItem("restaurantId"));
    for(var i = 0; i < store.length; i++){
        if(store[i].commodityName === commodityName)
            return true;
    }
    return false;
}

function addCommodityRow(name, type, price, amount, num) {
    var table = document.getElementById("commodityTable");
    var row = document.createElement("tr");

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

    var opCell = document.createElement('td');
    if(type === "单品"){
        var add = document.createElement('a');
        add.innerHTML = '添加';
        add.className = 'opBtn';
        add.href = 'javascript:void(0);';
        add.onclick = function() {
            addItem(this);
        };
        var del = document.createElement('a');
        del.innerHTML = '删除';
        del.className = 'opBtn';
        del.href = 'javascript:void(0);';
        del.onclick = function() {
            deleteItem(this);
        };
        opCell.appendChild(add);
        opCell.appendChild(del);
    }else{
        opCell.style.textAlign='center';
        opCell.innerHTML = "无法添加";
    }
    opCell.style.display='none';
    row.appendChild(opCell);

    var numCell = document.createElement('td');
    numCell.style.display='none';
    numCell.style.textAlign='center';
    numCell.innerHTML = num;
    row.appendChild(numCell);

    table.appendChild(row);
}
function addItem(a) {
    var tr = a.parentNode.parentNode;
    var restaurantId = localStorage.getItem("restaurantId");
    var itemName = tr.cells[0].innerText;
    var itemPrice = tr.cells[2].innerText;
    var item = new CommodityItem(restaurantId, itemName, itemPrice);
    packageItems.push(item);
    tr.cells[5].innerHTML = parseInt(tr.cells[5].innerText) + 1;
}

function deleteItem(a) {
    var tr = a.parentNode.parentNode;
    var itemName = tr.cells[0].innerText;
    var itemPrice = tr.cells[2].innerText;
    for(var i = 0; i < packageItems.length; i++){
        if(packageItems[i].itemName === itemName && packageItems[i].itemPrice === itemPrice){
            packageItems.splice(i, 1);
            break;
        }
    }
    if(parseInt(tr.cells[5].innerText) > 0)
        tr.cells[5].innerHTML = parseInt(tr.cells[5].innerText) - 1;
}