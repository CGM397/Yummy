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