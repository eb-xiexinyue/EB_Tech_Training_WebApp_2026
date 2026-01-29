function editDepartment(btn){
    let departmentId = btn.parentElement.parentElement.querySelector(".departmentIdCls").innerText;
    location.href = "/editDepartment/" + departmentId;
}

function deleteDepartment(btn){
    let departmentId = btn.parentElement.parentElement.querySelector(".departmentIdCls").innerText;

    if(confirm("この部署を削除しますか？")){
        location.href = "/deleteDepartment/" + departmentId;
    }
}
