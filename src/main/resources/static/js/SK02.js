//定数
const errorMsgList = {
	FE001 : "社員の名前を入力してください。",
	FE002 : "社員のメールアドレスを入力してください。",
	FE003 : "社員の名前のフリガナを入力してください。",
    FE004 : "社員の性別を選択してください。",
    FE005 : "社員の生年月日を入力してください。",
    FE006 : "社員の所属部署を選択してください。",
    FE007 : "社員の所属案件を選択してください。"
}

//入力チェック
function validateForm() {
    const name = document.getElementById("name").value.trim();
    const kana = document.getElementById("nameKana").value.trim();
    const gender = document.getElementById("gender").value;
    const birth = document.getElementById("birthDate").value;
    const email = document.getElementById("email").value.trim();
    const dept = document.getElementById("departmentId").value;
    const anken = document.getElementById("ankenId").value;
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.innerText = "";

    // 名前
    if (name === "") {
        errorMsg.innerText = errorMsgList.FE001;
        return false;
    }

    // メール
    if (email === "") {
        errorMsg.innerText = errorMsgList.FE002;
        return false;
    }

    // フリガナ
    if (kana === "") {
        errorMsg.innerText = errorMsgList.FE003;
        return false;
    }

    // フリガナ（カタカナチェック）
    const kanaRegex = /^[ァ-ヶー　]+$/;
    if (!kanaRegex.test(kana)) {
        errorMsg.innerText = "フリガナは全角カタカナで入力してください。";
        return false;
    }

    // 性別
    if (gender === "") {
        errorMsg.innerText = errorMsgList.FE004;
        return false;
    }

    // 生年月日
    if (birth === "") {
        errorMsg.innerText = errorMsgList.FE005;
        return false;
    }
    const today = new Date().toISOString().split("T")[0];
    if (birth > today) {
        errorMsg.innerText = "未来の日付は選択できません。";
        return false;
    }

    // 所属部署
    if (dept === "") {
        errorMsg.innerText = errorMsgList.FE006;
        return false;
    }

    // 所属案件
    if (anken === "") {
        errorMsg.innerText = errorMsgList.FE007;
        return false;
    }

    return true;
}
//部署ドロップダウン選択時案件リスト更新処理
document.getElementById("departmentId").addEventListener("change", function(){
			const departmentId = this.value;
			const ankenSelect = document.getElementById("ankenId");
			
			ankenSelect.innerHTML = '<option value="">-- 選択してください --</option>';
			
			//選択部署空白の場合処理中断
			if(!departmentId){
				return;
			}
			
			//案件取得用リクエスト発生
			fetch('/anken/by-department?departmentId='+departmentId)
				.then(res => res.json())
				.then(ankens => {
					//取得案件情報をリストに設定
					ankens.forEach(anken => {
						const optionEl = document.createElement("option");
						optionEl.value = anken.anken_id
						optionEl.text = anken.anken_name
						
						ankenSelect.appendChild(optionEl)
					})
				})
		})