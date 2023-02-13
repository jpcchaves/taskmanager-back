
# Task Manager Back-end

O projeto trata-se de um C.R.U.D de tarefas, onde é possível criar, editar, listar e deletar tarefas.


## Stack utilizada

**Java (JDK 17), SpringBoot ˆ3.0.0**

**Database** MySQL

## Rodando localmente

Clone o projeto

```bash
  git clone https://github.com/jpcchaves/task-manager.git
```

Entre no diretório do projeto

```bash
  cd task-manager
```

Aguarde o Maven instalar as dependências

Inicie o servidor (se estiver utilizando o IntelliJ, utilize o comando abaixo. Caso não, busque a opção Run Application da sua IDE de preferência)

```bash
  CTRL + SHIFT + F10
```


## Documentação da API

#### Retorna todos as tasks

```http
  GET /api/v1/task
```

#### Retorna uma task

```http
  GET /api/v1/task/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `UUID` | **Obrigatório**. O ID da task que você quer buscar |

#### Cria uma task

```http
  POST /api/v1/task/new
```

| Request Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `task`      | `string` | **Obrigatório**. A task você quer criar |
| `deadine`      | `date` | **Obrigatório**. O prazo de conclusão da task |

#### Atualiza os dados de uma task

```http
  PUT /api/v1/task/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `UUID` | **Obrigatório**. O ID da task que você quer editar |

| Request Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `task`      | `string` | **Obrigatório**. A task você quer criar |
| `deadine`      | `date` | **Obrigatório**. O prazo de conclusão da task |
| `conluded`      | `boolen` | **Obrigatório**. A situção da task (se foi concluída ou não) |

#### Atualiza a situação de uma task

```http
  PATCH /api/v1/task/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `UUID` | **Obrigatório**. O ID da task que você quer editar |

| Request Body   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `conluded`      | `boolen` | **Obrigatório**. A situção da task (se foi concluída ou não) |



#### Deleta uma task

```http
  DELETE /api/v1/task/${id}
```

| Parâmetro   | Tipo       | Descrição                                   |
| :---------- | :--------- | :------------------------------------------ |
| `id`      | `UUID` | **Obrigatório**. O ID da task que você quer deletar |


## Aprendizados

Aprendi a utilizar o framework Spring Boot para desenvolver uma API Rest com os principais endpoints: GET, PUT, PATCH, POST, DELETE.

Também foi de grande valia para aprender mais sobre tratamento de exceções em Java e validação dos dados enviados pelo usuário por meio da validação disponibilizada pelo Spring Boot (Bean Validation with Hibernate validator).

Além disso, implementei HATEOAS (Hypermedia As the Engine Of Application State) e o padrão DTO, buscando deixar a API mais robusta e com padrões de projeto implementados, alcançando o nível REST (Representational State Transfer).

## Apêndice

Caso queira testar os endpoints da aplicação, importe o seguinte código JSON no seu aplicativo de testes de API (Postman, Insomnia, etc...)

``
{"openapi":"3.0.1","info":{"title":"Task Manager - REST API","description":"REST API built to manage a to do application made with Spring Boot","termsOfService":"https://porfolio-jpcchaves.vercel.app/","contact":{"url":"https://www.linkedin.com/in/joaopaulo-chaves/","email":"jpcchaves@outlook.com"},"license":{"name":"MIT","url":"https://porfolio-jpcchaves.vercel.app/"},"version":"v1"},"servers":[{"url":"http://localhost:8080","description":"Generated server url"}],"paths":{"/api/v1/task/{id}":{"get":{"tags":["Tasks"],"summary":"Finds a tasks","description":"Finds a tasks","operationId":"listTaskById","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string","format":"uuid"}}],"responses":{"200":{"description":"Success","content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskResponseDTO"}}}},"204":{"description":"No Content"},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"404":{"description":"Not Found"},"500":{"description":"Internal Error"}}},"put":{"tags":["Tasks"],"summary":"Updates a task","description":"Updates a task by passing in a JSON representation of the task!","operationId":"updateTask","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string","format":"uuid"}}],"requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskDTO"}}},"required":true},"responses":{"200":{"description":"Updated","content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskResponseDTO"}}}},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"404":{"description":"Not Found"},"500":{"description":"Internal Error"}}},"delete":{"tags":["Tasks"],"summary":"Deletes a task","description":"Deletes a task by passing in a JSON representation of the task!","operationId":"deleteTask","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string","format":"uuid"}}],"responses":{"204":{"description":"No Content"},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"404":{"description":"Not Found"},"500":{"description":"Internal Error"}}},"patch":{"tags":["Tasks"],"summary":"Update the situation of a task (if it's concluded or not)","description":"Updates a task by passing in a boolean that represents if the task is concluded or not","operationId":"updateTaskSituation","parameters":[{"name":"id","in":"path","required":true,"schema":{"type":"string","format":"uuid"}}],"requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskPatchDTO"}}},"required":true},"responses":{"200":{"description":"Updated","content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskResponseDTO"}}}},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"404":{"description":"Not Found"},"500":{"description":"Internal Error"}}}},"/api/v1/task/new":{"post":{"tags":["Tasks"],"summary":"Creates a new task","description":"Adds a new task by passing in a JSON representation of the task!","operationId":"createTask","requestBody":{"content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskCreateDTO"}}},"required":true},"responses":{"200":{"description":"Success","content":{"application/json":{"schema":{"$ref":"#/components/schemas/TaskResponseDTO"}}}},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"500":{"description":"Internal Error"}}}},"/api/v1/task":{"get":{"tags":["Tasks"],"summary":"Finds all tasks","description":"Finds all tasks","operationId":"listAllTasks","parameters":[{"name":"page","in":"query","required":false,"schema":{"type":"integer","format":"int32","default":0}},{"name":"size","in":"query","required":false,"schema":{"type":"integer","format":"int32","default":10}},{"name":"orderBy","in":"query","required":false,"schema":{"type":"string","default":"createdAt"}},{"name":"direction","in":"query","required":false,"schema":{"type":"string","default":"asc"}}],"responses":{"200":{"description":"Success","content":{"application/json":{"schema":{"type":"array","items":{"$ref":"#/components/schemas/TaskResponseDTO"}}}}},"400":{"description":"Bad Request"},"401":{"description":"Unauthorized"},"404":{"description":"Not Found"},"500":{"description":"Internal Error"}}}}},"components":{"schemas":{"Links":{"type":"object","additionalProperties":{"$ref":"#/components/schemas/Link"}},"TaskResponseDTO":{"required":["concluded","task"],"type":"object","properties":{"id":{"type":"string","format":"uuid"},"task":{"type":"string"},"concluded":{"type":"boolean"},"deadline":{"type":"string","format":"date-time"},"createdAt":{"type":"string","format":"date-time"},"_links":{"$ref":"#/components/schemas/Links"}}},"TaskDTO":{"required":["concluded","task"],"type":"object","properties":{"id":{"type":"string","format":"uuid"},"task":{"type":"string"},"concluded":{"type":"boolean"},"deadline":{"type":"string","format":"date-time"},"_links":{"$ref":"#/components/schemas/Links"}}},"TaskCreateDTO":{"required":["task"],"type":"object","properties":{"task":{"type":"string"},"deadline":{"type":"string","format":"date-time"},"_links":{"$ref":"#/components/schemas/Links"}}},"TaskPatchDTO":{"required":["concluded"],"type":"object","properties":{"concluded":{"type":"boolean"},"_links":{"$ref":"#/components/schemas/Links"}}},"Link":{"type":"object","properties":{"href":{"type":"string"},"hreflang":{"type":"string"},"title":{"type":"string"},"type":{"type":"string"},"deprecation":{"type":"string"},"profile":{"type":"string"},"name":{"type":"string"},"templated":{"type":"boolean"}}}}}}
``




