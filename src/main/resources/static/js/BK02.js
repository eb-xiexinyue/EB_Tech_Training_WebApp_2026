const deptErrorMsgList = {
    FE008: "部署名を入力してください。"
};

// 入力チェック
function validateDepartmentForm() {
    const deptName = document.getElementById("departmentName").value.trim();
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.innerText = "";

    // 未入力チェック
    if (deptName === "") {
        errorMsg.innerText = deptErrorMsgList.FE008;
        return false;
    }

    return true;
}
