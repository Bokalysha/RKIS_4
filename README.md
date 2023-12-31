# Практическая работа №4
## Spring, работа с БД
**Цель работы:** ознакомиться с механизмами работы с базами данных в Spring Framework.

**Общая постановка задачи:** В каждом варианте есть сущность базы данных.

*Необходимо:*

1. Описать класс сущности, который имеет как минимум три текстовых поля и два числовых (и, естественно, id). Она описывает некий товар (эта сущность и база будет использована в некоторых последующих работах).
2. Создать в СУБД PostgreSQL таблицу базы данных, соответствующую спроектированной сущности.
3. Реализовать консольное Spring приложение (должно иметь простейший консольный пользовательский интерфейс), которое должно позволять:
* Вводить (консольный ввод) пользователю поля сущности и добавлять её в таблицу БД.
* Выводить в консоль все записи из таблицы БД.
* Редактировать запись таблицы БД по id.
* Удалять запись по Id.
* Осуществлять поиск по любому из признаков (на выбор студента поле для поиска). Например, поиск всех студентов, средний балл которых выше введенного пользователем).
4. Способ работы с БД (JdbcTemplate, Hibernate, JPA или др.) студентом выбирается самостоятельно при одном ограничении: должен использоваться Spring Framework.

## Сборка и результат
_Вариант №2: Смартфон._

**Сборка проекта:**
1. Распаковать проект
2. Открыть директорию с проектом в терминале и проверить наличие [Maven](https://maven.apache.org/download.cgi)
```
mvn -version
```
3. Создать базу данных
```
psql -U postgres -h localhost -f create_db.sql
```
3. Собрать проект
```
mvn clean package
```
4. Запустить программу
```
java -jar target/Prac_4.jar 
```
