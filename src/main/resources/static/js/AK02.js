const ankenErrorMsgList = {
    FE009: "案件名を入力してください。",
    FE010: "所属部署を選択してください。"
};


function validateAnkenForm() {

    const ankenName = document.getElementById("ankenName").value.trim();
    const departmentId = document.getElementById("departmentId").value;
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.innerText = "";

    if (ankenName === "") {
        errorMsg.innerText = ankenErrorMsgList.FE009;
        return false;
    }

    if (!departmentId) {
        errorMsg.innerText = ankenErrorMsgList.FE010;
        return false;
    }

    return true;
}
