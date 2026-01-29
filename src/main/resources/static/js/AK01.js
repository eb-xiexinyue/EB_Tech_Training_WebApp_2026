function editAnken(id) {
    location.href = "/editAnken/" + id;
}

function deleteAnken(id) {
    if (confirm("削除します。よろしいでしょうか？")) {
        location.href = "/deleteAnken/" + id;
    }
}
