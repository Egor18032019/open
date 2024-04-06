# open
Система учета времени выполнения методов в приложении с использованием Spring AOP. 
Система должна быть способна асинхронно логировать и анализировать данные о времени выполнения методов.
Проект создан с использованием java 17.
Запуск 
 ```shell
docker-compose up --build
```
Приложение содержит таблицу PokemonEntity, таблицу MethodEntity, таблицу Times.
* Документация http://localhost:8080/swagger-ui/index.html#/
* Примеры запросов:

Получить список покемонов
```shell
curl -i -X GET http://localhost:8080/v2/pokemons?limit=111&offset=1
```
Получить url на покемона по имени(если есть в бд). bulbasaur как пример
 ```shell
curl -i -X GET http://localhost:8080/v2/bulbasaur
```

Получить список выполнения всех методов + список со временем выполнения при каждом вызове
```shell
curl -i -X GET http://localhost:8080/v2/execution
```
Получить список выполение всех методов со среднем временем для каждого метода.
```shell
curl -i -X GET http://localhost:8080/v2/average
```
Получить список всех методов с общей суммой времени для каждого метода.
```shell
curl -i -X GET http://localhost:8080/v2/total
```
 