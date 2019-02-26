var customerAddresses = [];
function initPageInfo() {
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
                for(var i = 0; i < result.length; i++){
                    select.options.add(new Option(result[i].address));
                    customerAddresses.push(result[i]);
                }
                showAllRestaurants();
            }
        },
        error:function(){
            alert("error");
        }
    });
}

function showAllRestaurants() {
    $.ajax({
        type: 'POST',
        url:"/customerShopping/showAllRestaurants",
        data: {},
        success:function(result){
            for(var i = 0; i < result.length; i++){
                var restaurantAddress = showRestaurantAddress(result[i].restaurantId);
                if(isNear(restaurantAddress))
                    addRestaurantRow(result[i].restaurantId,
                                     result[i].restaurantName,
                                     result[i].restaurantType,
                                     restaurantAddress.address);

            }
        },
        error:function(){
            alert("error");
        }
    });
}

function isNear(restaurantAddress) {
    var x1 = restaurantAddress.coordinateX;
    var y1 = restaurantAddress.coordinateY;
    var customerAddress = document.getElementById("customer-address").value;
    var x2 = 0,y2 = 0;
    for(var i = 0; i < customerAddresses.length; i++){
        if(customerAddresses[i].address === customerAddress){
            x2 = customerAddresses[i].coordinateX;
            y2 = customerAddresses[i].coordinateY;
            break;
        }
    }
    return (Math.sqrt(Math.pow(x1-x2, 2) + Math.pow(y1-y2, 2)) < 300);
}

function addRestaurantRow(restaurantId, restaurantName, restaurantType, restaurantAddress) {
    var table = document.getElementById("restaurantTable");
    var row = document.createElement("tr");

    var idCell = document.createElement('td');
    idCell.style.textAlign='center';
    idCell.style.display='none';
    idCell.innerHTML = restaurantId;
    row.appendChild(idCell);

    var nameCell = document.createElement('td');
    nameCell.style.textAlign='center';
    nameCell.innerHTML = restaurantName;
    row.appendChild(nameCell);

    var typeCell = document.createElement('td');
    typeCell.style.textAlign='center';
    if(restaurantType === "type")
        typeCell.innerHTML = "暂无";
    else
        typeCell.innerHTML = restaurantType;
    row.appendChild(typeCell);

    var addressCell = document.createElement('td');
    addressCell.style.textAlign='center';
    if(restaurantAddress === "" || restaurantAddress === null)
        addressCell.innerHTML = "暂无";
    else
        addressCell.innerHTML = restaurantAddress;
    row.appendChild(addressCell);

    var opCell = document.createElement('td');
    var enter = document.createElement('a');
    enter.innerHTML = '进入餐厅';
    enter.className = 'opBtn';
    enter.href = 'javascript:void(0);';
    enter.onclick = function() {
        turnToShopping(this);
    };
    opCell.appendChild(enter);
    row.appendChild(opCell);

    table.appendChild(row);
}

function turnToShopping(a) {
    var tr = a.parentNode.parentNode;
    var restaurantId = tr.cells[0].innerText;
    localStorage.setItem("selectRestaurant", restaurantId);
    localStorage.setItem("currentCustomerAddress",
                         document.getElementById("customer-address").value);
    window.location.href="/customer-shopping";
}

function updateRestaurantList() {
    var table = document.getElementById("restaurantTable");
    var trs = document.getElementsByTagName("tr");
    for(var i = trs.length - 1; i > 0; i--)
        table.deleteRow(i);
    showAllRestaurants();
}