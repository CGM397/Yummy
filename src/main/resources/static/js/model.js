function Customer(customerId, customerMail, customerPassword, customerName, phoneNumber, vipPoints,
                  vipLevel, active) {
    this.customerId = customerId;
    this.customerMail = customerMail;
    this.customerPassword = customerPassword;
    this.customerName= customerName;
    this.phoneNumber = phoneNumber;
    this.vipPoints = vipPoints;
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
    this.modificationId = 0;
    this.modificationDate = modificationDate;
    this.restaurantId = restaurantId;
    this.modifiedName = modifiedName;
    this.modifiedType = modifiedType;
    this.modifiedAddress = modifiedAddress;
    this.checked = checked;
    this.approve = approve;
}

function CommodityInfo(restaurantId, commodityName, type, commodityPrice, amount, items, releaseDate,
                       duration) {
    this.commodityInfoId = 0;
    this.restaurantId = restaurantId;
    this.commodityName = commodityName;
    this.type = type;
    this.commodityPrice = commodityPrice;
    this.amount = amount;
    this.items = items;
    this.releaseDate = releaseDate;
    this.duration = duration;
}

function CommodityItem(restaurantId, itemName, itemPrice) {
    this.commodityItemId = 0;
    this.restaurantId = restaurantId;
    this.itemName = itemName;
    this.itemPrice = itemPrice;
}

function DiscountInfo(restaurantId, commodityName, discount, discountAmount, releaseDate, duration) {
    this.discountInfoId = 0;
    this.restaurantId = restaurantId;
    this.commodityName = commodityName;
    this.discount = discount;
    this.discountAmount = discountAmount;
    this.releaseDate = releaseDate;
    this.duration = duration;
}