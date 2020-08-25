# API de Funcionários
### Introdução

Esse sistema tem como objetivo um CRUD de funcionários. Além disso possui uma chama a API do site webmaniabr para transformar o CEP em um endereço.

Foram utilizados os seguintes frameworks e ferramentas:

- Spring -> Para a criação do ambiente web
- JPA    -> Para realização da persistência dos dados
- H2     -> Para armazenamento dos dados
- Fasterxml -> Para conversão dos dados em JSON
- Lombok -> Para reduzir a verbosidade do código
- Feign Client -> Para chamadas a outros microserviços
- Junit   -> para criação dos testes unitários
- Fixture Factory -> Para criação de templates

### Pre-requisitos

- É necessário ter o Java 8 e maven instalado e configurado.

### Build

Para realizar o build é necessário compilar o projeto:

```sh
$ mvn compile
```
### Iniciando os Serviços

Para iniciar o serviço os seguintes comandos:

```sh
$ cd campanha-service
$ mvn compile spring-boot:run
```

### Testes

Para verificar os testes unitários do serviço de campanha realize os seguintes comandos:

```sh
$ cd campanha-service
$ mvn clean test
```
