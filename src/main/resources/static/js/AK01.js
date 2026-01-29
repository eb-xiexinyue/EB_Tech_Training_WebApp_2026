
function confirmDelete(ankenId) {

    const result = confirm("この案件を削除してもよろしいですか？");

    if (result) {
    
        location.href = "/deleteAnken?anken_id=" + ankenId;
    }


}
