document.addEventListener("DOMContentLoaded", () => {

    // 後ほど内容にアクセスするために、予めフォームと入力ボックスのエレメントを取得しておく
    const form = document.getElementById("deptForm");

    const nameInput = document.getElementById("departmentName");
    const nameErr = document.getElementById("departmentNameError");

    // 入力された内容がなかった場合、
    // 入力ボックスを赤色表示し、下にエラーメッセージを表示する
    function setError(input, errEl, msg) {
        input.classList.add("is-invalid");
        errEl.textContent = msg;
    }

    // 再び操作した場合、
    // 赤枠とエラーメッセージを削除する
    function clearError(input, errEl) {
        input.classList.remove("is-invalid");
        errEl.textContent = "";
    }

    // 入力された内容を検証する
    function validate() {

        // 全体の検証結果（true：正常、false：エラーあり）
        let ok = true;

        // 部署名を取得
        const val = nameInput.value.trim();

        // 部署名が空白の場合
        if (!val) {
            // エラー表示
            setError(nameInput, nameErr, "部署名を入力してください。");
            ok = false;
        } else {
            // 入力されている場合はエラー解除
            clearError(nameInput, nameErr);
        }

        return ok;
    }

    // フォーム送信時のチェック
    // 検証NGの場合、送信をキャンセル
    form.addEventListener("submit", (e) => {
        if (!validate()) e.preventDefault();
    });

    // 入力・フォーカス時にエラー表示を消す
    ["input", "focus"].forEach(evt => {
        nameInput.addEventListener(evt, () => {
            clearError(nameInput, nameErr);
        });
    });

});
