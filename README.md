<h1 align="center" style="font-weight: bold;">TODOLIST ✅</h1>

<p align="center">
 <a href="#technologies">Technologies</a> • 
 <a href="#started">Getting Started</a> • 
  <a href="#routes">API Endpoints</a> •
</p>

<p align="center">
    <b>A TodoList API é um sistema simples de gerenciamento de tarefas desenvolvido com Java e Spring Boot, seguindo a arquitetura hexagonal para melhor organização e separação de responsabilidades.</b>
</p>

<h2 id="technologies">Technologies</h2>

- Java 17
- Spring Boot (Spring Data JPA, Lombok, DevTools)
- H2 Database (Banco de dados em memória)
- Arquitetura Hexagonal (Separação entre domain, application e adapters)

<h2 id="started">🚀 Getting started</h2>

<h3>Prerequisites</h3>

- Java 17 ou superior
- Maven
- Git

<h3>Cloning</h3>

```bash
  git clone https://github.com/KaioFerr/todolist.git
```

<h3>Starting</h3>

How to start your project

```bash
mvn spring-boot:run
```

<h2 id="routes">📍 API Endpoints</h2>
​


| **Rota**                             | **Descrição**  
|--------------------------------------|-------------------------------------------------------------  
| **`POST /task/`**                    | Cria uma nova tarefa para o usuário autenticado.  
| **`GET /task/`**                     | Retorna todas as tarefas do usuário autenticado.  
| **`PUT /task/{id}`**                  | Atualiza uma tarefa específica do usuário autenticado.  
| **`DELETE /task/delete/{id}`**        | Exclui uma tarefa específica do usuário autenticado.  
| **`POST /user/register`**             | Registra um novo usuário no sistema.  


