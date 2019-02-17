var customerId = "";
var customerMail = "";
var customerPassword = "";
var customerName = "";
var phoneNumber = "";
var vipLevel = 0;
var active = false;
function Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipLevel, active) {
    this.customerId = customerId;
    this.customerMail = customerMail;
    this.customerPassword = customerPassword;
    this.customerName= customerName;
    this.phoneNumber = phoneNumber;
    this.vipLevel = vipLevel;
    this.active = active;
}

var restaurantId = "";
var restaurantPassword = "";
var restaurantName = "";
var restaurantType = "";
function Restaurant(restaurantId, restaurantPassword, restaurantName, restaurantType) {
    this.restaurantId = restaurantId;
    this.restaurantPassword = restaurantPassword;
    this.restaurantName = restaurantName;
    this.restaurantType = restaurantType;
}

var userId = "";
var address = "";
var coordinateX = 0;
var coordinateY = 0;
function Address(userId, address, coordinateX, coordinateY) {
    this.userId = userId;
    this.address = address;
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
}

function Modification(restaurantId, modifiedName, modifiedType, modifiedAddress, approve) {
    this.restaurantId = restaurantId;
    this.modifiedName = modifiedName;
    this.modifiedType = modifiedType;
    this.modifiedAddress = modifiedAddress;
    this.approve = approve;
}