document.addEventListener("DOMContentLoaded", () => {

    // 後ほど内容にアクセスするために、予め入力ボックスのエレメントを取得しておく
    const form = document.getElementById("ankenForm");

    const nameInput = document.getElementById("ankenName");
    const nameErr = document.getElementById("ankenNameError");

    const deptSelect = document.getElementById("deptSelect");
    const deptErr = document.getElementById("deptSelectError");

    //　入力された内容がなかった場合、入力ボックスが赤変転し、下方にエラーメッセージが表示
    function setError(input, errEl, msg) {
        input.classList.add("is-invalid");
        errEl.textContent = msg;
    }

    //　再び操作した場合、赤変転とエラーメッセージが除去される
    function clearError(input, errEl) {
        input.classList.remove("is-invalid");
        errEl.textContent = "";
    }

    //　入力された内容を検証する
    function validate() {
        // 整体验证通过是否的标识符
        let ok = true;

        // 拿到案件名
        const nameVal = nameInput.value.trim();
        // 如果案件名是空白的话
        if (!nameVal) {
            // 使用上面的setError进行样式变更并且显示文字
            setError(nameInput, nameErr, "案件名を入力してください。");
            ok = false;
        } else {
            // 确实输入了文字的话清空错误式样
            clearError(nameInput, nameErr);
        }

        // 如上的方式验证部署是否选择了
        // 拿到部署所选的值
        const deptVal = deptSelect.value.trim();
        if (!deptVal) {
            // 没有选择的话显示错误信息
            setError(deptSelect, deptErr, "本案件の所属部署を選択してください。");
            ok = false;
        } else {
            // 确实选择了的话清空错误式样
            clearError(deptSelect, deptErr);
        }

        return ok;
    }

    // 検証が通らなかったら、提出がキャンセルされる
    form.addEventListener("submit", (e) => {
        if (!validate()) e.preventDefault();
    });

    // 入力/変更/フォーカスでエラーを消す
    ["input", "focus"].forEach(evt => {
        nameInput.addEventListener(evt, () => clearError(nameInput, nameErr));
    });

    ["change", "focus"].forEach(evt => {
        deptSelect.addEventListener(evt, () => clearError(deptSelect, deptErr));
    });
});
