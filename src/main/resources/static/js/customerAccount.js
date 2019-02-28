function setAccountInfo() {
    var accountStore = showUserAccount(localStorage.getItem("customerId"));
    if(accountStore.length > 0){
        document.getElementById("newAccount").style.display = "none";
        document.getElementById("accountInUse").style.display = "block";
        var accountInUse = new Account();
        for(var i = 0; i < accountStore.length; i++){
            if(accountStore[i].inUse){
                accountInUse = accountStore[i];
                break;
            }
        }
    }
}

function saveAccount() {
    
}

function modify() {
    
}

function modifyPassword() {
    
}

function modifyAccount() {
    
}