// 定数（エラーメッセージ）
const errorMsgList = {
    FE001: "社員の名前を入力してください。",
    FE002: "社員のメールアドレスを入力してください。"
};

document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("employeeForm");

    const nameInput = document.getElementById("name");
    const emailInput = document.getElementById("email");
    const deptSelect = document.getElementById("departmentId");
    const ankenSelect = document.getElementById("ankenId");

    const nameErr = document.getElementById("nameError");
    const emailErr = document.getElementById("emailError"); // ← サーバ側 errorMsg もここに入る（SK02.html側で th:text）

    function setError(input, errEl, msg) {
        input.classList.add("is-invalid");
        errEl.textContent = msg;
    }

    function clearError(input, errEl) {
        input.classList.remove("is-invalid");
        // サーバ側エラー（th:text）の表示も含めてクリアする
        errEl.textContent = "";
    }

    function validate() {
        let ok = true;

        // 名前（必須）
        const nameVal = (nameInput.value || "").trim();
        if (!nameVal) {
            setError(nameInput, nameErr, errorMsgList.FE001);
            ok = false;
        } else {
            clearError(nameInput, nameErr);
        }

        // メール（必須）
        const emailVal = (emailInput.value || "").trim();
        if (!emailVal) {
            setError(emailInput, emailErr, errorMsgList.FE002);
            ok = false;
        } else {
            // ここで消すと「サーバ側エラー」が見えなくなるので、入力OKなら消さない
            // clearError(emailInput, emailErr);
            emailInput.classList.remove("is-invalid");
        }

        return ok;
    }

    // 送信時チェック
    form.addEventListener("submit", (e) => {
        if (!validate()) e.preventDefault();
    });

    // 入力/フォーカスでエラー解除（赤枠・メッセージ）
    ["input", "focus"].forEach((evt) => {
        nameInput.addEventListener(evt, () => clearError(nameInput, nameErr));
        emailInput.addEventListener(evt, () => clearError(emailInput, emailErr));
    });

    // ✅ サーバ側（重複など）のエラーメッセージが既に表示されていたらメールを赤くする
    if (emailErr.textContent && emailErr.textContent.trim() !== "") {
        emailInput.classList.add("is-invalid");
    }

    // 部署変更→案件リスト更新
    deptSelect.addEventListener("change", () => {
        const departmentId = deptSelect.value;

        // 初期化
        ankenSelect.innerHTML = '<option value="">-- 選択してください --</option>';

        // 未選択なら終了
        if (!departmentId) return;

        fetch("/anken/by-department?departmentId=" + encodeURIComponent(departmentId))
            .then((res) => res.json())
            .then((ankens) => {
                ankens.forEach((anken) => {
                    const optionEl = document.createElement("option");
                    optionEl.value = anken.anken_id;
                    optionEl.textContent = anken.anken_name;
                    ankenSelect.appendChild(optionEl);
                });
            })
            .catch(() => {
                // 通信エラー時は何もしない（必要なら通知を追加）
            });
    });
});
