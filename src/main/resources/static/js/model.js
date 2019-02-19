function Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipLevel, active) {
    this.customerId = customerId;
    this.customerMail = customerMail;
    this.customerPassword = customerPassword;
    this.customerName= customerName;
    this.phoneNumber = phoneNumber;
    this.vipLevel = vipLevel;
    this.active = active;
}

function Restaurant(restaurantId, restaurantPassword, restaurantName, restaurantType) {
    this.restaurantId = restaurantId;
    this.restaurantPassword = restaurantPassword;
    this.restaurantName = restaurantName;
    this.restaurantType = restaurantType;
}

function Address(userId, address, coordinateX, coordinateY) {
    this.userId = userId;
    this.address = address;
    this.coordinateX = coordinateX;
    this.coordinateY = coordinateY;
}

function Modification(modificationDate, restaurantId, modifiedName, modifiedType, modifiedAddress,
                      checked, approve) {
    this.modificationId = "";
    this.modificationDate = modificationDate;
    this.restaurantId = restaurantId;
    this.modifiedName = modifiedName;
    this.modifiedType = modifiedType;
    this.modifiedAddress = modifiedAddress;
    this.checked = checked;
    this.approve = approve;
}