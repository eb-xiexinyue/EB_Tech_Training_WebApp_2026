function editDepartment(id) {
    location.href = "/editDepartment/" + id;
}// 編集ボタンの呼び出しのメソッドです

//削除ボタンの呼び出しのメソッドです。選択式のアラートが表示される。
function deleteDepartment(id) {
    if (confirm("削除します。よろしいでしょうか？")) {
        location.href = "/deleteDepartment/" + id;
    }
}
