var packageItems = [];

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
                     !isPositiveInteger(commodityAmount)){
                swal("发布失败","商品价格、数量或者持续时间填写格式有误","error");
                return;
            }
            var commodityItem = new CommodityItem(restaurantId, commodityName, commodityPrice);
            var items = [];
            items.push(commodityItem);
            var commodityInfo = new CommodityInfo(restaurantId, commodityName, "单品", commodityPrice,
                                            commodityAmount, items, commodityBeginDate,
                                            commodityEndDate);
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
                !isPositiveInteger(commodityAmount)){
                swal("发布失败","商品价格、数量或者持续时间填写格式有误","error");
                return;
            }else if(packageItems.length < 1){
                swal("发布失败","套餐至少需要一件单品","error");
                return;
            }
            var commodityInfo = new CommodityInfo(restaurantId, commodityName, "套餐", commodityPrice,
                commodityAmount, packageItems, commodityBeginDate,
                commodityEndDate);
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
            if(commodityName === "--please select--"){
                swal("发布失败","商品名称不可为空","error");
                return;
            }else if(!isNonNegativeDouble(commodityDiscount) ||
                !isPositiveInteger(commodityAmount) || commodityDiscount <= 0 ||
                commodityDiscount >= 10){
                swal("发布失败","商品折扣或者数量填写格式有误","error");
                return;
            }
            var discountInfo = new DiscountInfo(restaurantId, commodityName, commodityDiscount,
                commodityAmount, commodityBeginDate, commodityEndDate);
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
    for(var i = 0; i < store.length; i++){
        addCommodityRow(store[i].commodityName, store[i].type, store[i].commodityPrice, 0);
        select.options.add(new Option(store[i].commodityName));
    }
}

function addCommodityRow(name, type, price, num) {
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
    tr.cells[4].innerHTML = parseInt(tr.cells[4].innerText) + 1;
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
    if(parseInt(tr.cells[4].innerText) > 0)
        tr.cells[4].innerHTML = parseInt(tr.cells[4].innerText) - 1;
}