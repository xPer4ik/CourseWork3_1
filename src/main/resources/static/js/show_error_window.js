window.onload = function() {
    var errorMessage = /*[[${errorMessage}]]*/ null;

    if (errorMessage) {
        var modal = document.getElementById('errorMessageModal');
        modal.style.display = 'block';  // Показываем модальное окно
    }
};

function closeErrorMessage() {
    var modal = document.getElementById('errorMessageModal');
    modal.style.display = 'none';  // Закрываем модальное окно
}
