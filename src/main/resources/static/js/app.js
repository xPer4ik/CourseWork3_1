// app.js

// Функция для отображения/скрытия выпадающего меню
function toggleLogoutMenu() {
    console.log('toggleLogoutMenu was called'); // Логируем вызов функции
    var menu = document.querySelector('.logout-dropdown');
    menu.style.display = (menu.style.display === 'block') ? 'none' : 'block';
}

// Закрытие меню, если кликнуть вне области
window.onclick = function(event) {
    console.log('window.onclick triggered'); // Логируем событие клика
    var menu = document.querySelector('.logout-dropdown');
    var greeting = document.querySelector('.greeting');
    if (event.target !== greeting && !greeting.contains(event.target)) {
        menu.style.display = 'none';
    }
}
