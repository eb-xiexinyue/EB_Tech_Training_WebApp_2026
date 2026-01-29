function editEmployee(id) {
    location.href = "/editEmployee/" + id;
}

function deleteEmployee(id) {
    if (confirm("削除しますか？")) {
        location.href = "/deleteEmployee/" + id;
    }
}
