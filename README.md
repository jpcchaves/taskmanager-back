
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

https://task-manager-production-43ba.up.railway.app/swagger-ui/index.html

## Aprendizados

Aprendi a utilizar o framework Spring Boot para desenvolver uma API Rest com os principais endpoints: GET, PUT, PATCH, POST, DELETE.

Também foi de grande valia para aprender mais sobre tratamento de exceções em Java e validação dos dados enviados pelo usuário por meio da validação disponibilizada pelo Spring Boot (Bean Validation with Hibernate validator).

Além disso, implementei HATEOAS (Hypermedia As the Engine Of Application State) e o padrão DTO, buscando deixar a API mais robusta e com padrões de projeto implementados, alcançando o nível REST (Representational State Transfer).
