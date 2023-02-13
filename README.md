
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



