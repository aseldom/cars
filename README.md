# cars

Данное приложение представляет собой сайт по продаже машин.

Данное web-приложение реализует:
- создание объявления о продаже автомобиля
- регистрацию/вход пользователя
- вывод всех автомобилей в виде списка
- просмотр подробной информации о конкретном автомобиле

## Стек технологий
* Spring Boot
* Thymeleaf
* Bootstrap
* Liquibase
* Hibernate
* MapStruct
* Lombok
* H2
* PostgreSQL
* GitHub Action

## Окружение
Java 17, Maven 3.8, PostgreSQL 14, Hibernat-Core 5.6.11, H2 2.1.214, ;

## Запуск проекта
- Импортировать проект в IntelliJ IDEA
- В PostgreSQL создать БД cars_web
- В Maven выполнить команду Plugins\liquibase\liquibase:update
- Выполнить метод main
- Открыть веб-браузер по адресу: 127.0.0.1:8080

## Структура данных
![](/img/Car_table_structure.drawio.png)

## Screenshot
- Главная страница
  ![](/img/main_page.png)
- Создание объявления о продаже автомобиля
  ![](/img/create_post.png)
- Просмотр объявления
  ![](/img/car_view.png)

---
#### Контакты для связи:
* email: a.seldom@gmail.com
* telegram: @aseldom