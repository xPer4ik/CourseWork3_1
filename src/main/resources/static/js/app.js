document.addEventListener('DOMContentLoaded', function () {
    // Функция для отображения/скрытия выпадающего меню
    function toggleLogoutMenu() {
        var menu = document.querySelector('.logout-dropdown');
        menu.style.display = (menu.style.display === 'block') ? 'none' : 'block';
    }

    // Навешиваем обработчик на клик по "Привет, user!"
    var greeting = document.querySelector('.greeting');
    if (greeting) {
        greeting.addEventListener('click', toggleLogoutMenu);
    }

    // Закрытие меню, если кликнуть вне области
    window.addEventListener('click', function(event) {
        var menu = document.querySelector('.logout-dropdown');
        var greeting = document.querySelector('.greeting');
        if (event.target !== greeting && !greeting.contains(event.target)) {
            menu.style.display = 'none';
        }
    });
});
