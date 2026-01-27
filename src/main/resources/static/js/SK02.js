//定数
const errorMsgList = {
	FE001 : "社員の名前を入力してください。",
	FE002 : "社員のメールアドレスを入力してください。"
}

//入力チェック
function validateForm() {
    const name = document.getElementById("name").value.trim();
    const email = document.getElementById("email").value.trim();
    const errorMsg = document.getElementById("errorMsg");

    errorMsg.innerText = "";

    if (name === "") {
        errorMsg.innerText = errorMsgList.FE001;
        return false; 
    }

    if (email === "") {
        errorMsg.innerText = errorMsgList.FE002;
        return false;
    }

    return true; 
}


//部署ドロップダウン選択時案件リスト更新処理
document.getElementById("departmentId").addEventListener("change", function(){
			const departmentId = this.value;
			const ankenSelect = document.getElementById("ankenId");
			
			ankenSelect.innerHTML = '<opiton value="">-- 選択してください --</option>'
			
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