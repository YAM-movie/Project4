function displayDate() {
    document.getElementById("firstDis").disabled= false;
    document.getElementById("lastDis").disabled= false;
    document.getElementById("birthlDis").disabled= false;
    document.getElementById("emailDis").disabled= false;
    document.getElementById("passDiv").hidden = false;
    document.getElementById("rePassDiv").hidden = false;
    document.getElementById("saveAppear").hidden = false;
    document.getElementById("cancel").hidden = false;
    document.getElementById("EditDisappered").hidden = true;
}
function cancelDate(){
    document.getElementById("firstDis").disabled= true;
    document.getElementById("lastDis").disabled= true;
    document.getElementById("birthlDis").disabled= true;
    document.getElementById("emailDis").disabled= true;
    document.getElementById("passDiv").hidden = true;
    document.getElementById("rePassDiv").hidden = true;
    document.getElementById("saveAppear").hidden = true;
    document.getElementById("cancel").hidden = true;
    document.getElementById("EditDisappered").hidden = false;
}

