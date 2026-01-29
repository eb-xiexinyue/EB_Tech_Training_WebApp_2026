function editEmployee(btn){
	 let employeeId = btn.parentElement.parentElement.querySelector(".employeeIdCls").innerText;
	 location.href = "/editEmployee/" + employeeId;
}

function deleteEmployee(btn){
	 let employeeId = btn.parentElement.parentElement.querySelector(".employeeIdCls").innerText;
	 location.href = "/deleteEmployee/" + employeeId;
}