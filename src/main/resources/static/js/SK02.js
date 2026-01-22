document.getElementById("departmentId").addEventListener("change", function(){
			const departmentId = this.value;
			const ankenSelect = document.getElementById("ankenId");
			
			ankenSelect.innerHTML = '<opiton value="">-- 選択してください --</option>'
			
			if(!departmentId){
				return;
			}
			
			fetch('/anken/by-department?departmentId='+departmentId)
				.then(res => res.json())
				.then(ankens => {
					ankens.forEach(anken => {
						const optionEl = document.createElement("option");
						optionEl.value = anken.anken_id
						optionEl.text = anken.anken_name
						
						ankenSelect.appendChild(optionEl)
					})
				})
		})