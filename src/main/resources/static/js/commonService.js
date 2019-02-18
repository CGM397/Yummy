function getDate() {
    var date = new Date();
    var hour = date.getHours();
    var min = date.getMinutes();
    var sec = date.getSeconds();
    if(hour < 10)
        hour = "0" + hour;
    if(min < 10)
        min = "0" + min;
    if(sec < 10)
        sec = "0" + sec;
    return date.getFullYear() + "-" + (date.getMonth() + 1) + "-" + date.getDate() + " " +
        hour + ":" + min + ":" + sec;
}