# Supero

Documentação do TaskList, projeto feito para desafio no processo de seletivo.

Caso queira Olhar a aplicação: [Clique aqui](https://desafio-supero-frontend.herokuapp.com/) 🙂

## Principais tecnologias

* [Spring Boot](https://spring.io/projects/spring-boot)
* [Lombok](https://projectlombok.org/)
* [Maven](http://maven.apache.org/)
* [Spring HATEOAS](https://spring.io/projects/spring-hateoas)
* [Mysql](https://www.mysql.com/)
* [JPA/Hibernate](https://spring.io/projects/spring-data-jpa)

## Pre-requisitos
Necessário ter o SGBD Mysql para conexão ao banco de dados.


## Configuração

Vá até o `application.properties` para realizar algumas alterações referentes a conexão e porta da aplicação.

```
spring.datasource.url=Url de conexão
spring.datasource.username=Seu Username
spring.datasource.password=Sua Senha
```

## Entidades

A aplicação contém somente um modelo: `Task` que irá ser espelhada como tabela no banco de dados.

```java
private Stage stage;
```
o stage(estado) define em qual estado nossa task está, podendo ter 2 estados:
`COMPLETED`
`NO-COMPLETED`

### Controllers

Nesta aplicação existe somente 1 controlador, o TaskController, ele trás alguns endpoints para serviço

#### TaskController
* /tasks `[GET]` => retorna lista de tasks.
* /tasks/{id} `[GET]` => retorna task específica e espera um id no parâmetro.
* /tasks `[POST]` => Salva task.
* /tasks `[PUT]` => Atualiza task por id.
* /tasks/{id} `[PATCH]` => Utilizado para alterar estado da task somente.
* /tasks/{id} `[DELETE]` => Deleta task por id.
