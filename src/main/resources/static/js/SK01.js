function editEmployee(btn){
	 let employeeId = btn.parentElement.parentElement.querySelector(".employeeIdCls").innerText​
	 
	 location.href = "/editEmployee?id=" + employeeId
}