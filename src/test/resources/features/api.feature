# language: pt
Funcionalidade: API de Usuários - Reqres.in
  Como um consumidor da API
  Quero realizar operações CRUD de usuários
  Para validar o comportamento do serviço

  @api @happy-path
  Cenário: Listar usuários com sucesso
    Dado que a API está disponível em "https://reqres.in"
    Quando eu faço uma requisição GET em "/api/users?page=1"
    Então o status da resposta deve ser 200
    E a resposta deve conter a lista de usuários
    E a resposta deve conter o campo "page" com valor "1"

  @api @happy-path
  Cenário: Buscar usuário por ID com sucesso
    Dado que a API está disponível em "https://reqres.in"
    Quando eu faço uma requisição GET em "/api/users/2"
    Então o status da resposta deve ser 200
    E a resposta deve conter o campo "data.id" com valor "2"

  @api @crud
  Cenário: Criar um novo usuário
    Dado que a API está disponível em "https://reqres.in"
    Quando eu envio uma requisição POST em "/api/users" com os dados:
      | nome  | cargo       |
      | Maria | QA Engineer |
    Então o status da resposta deve ser 201
    E a resposta deve conter o campo "name" com valor "Maria"
    E a resposta deve conter o campo "job" com valor "QA Engineer"

  @api @crud
  Cenário: Atualizar um usuário existente
    Dado que a API está disponível em "https://reqres.in"
    Quando eu envio uma requisição PUT em "/api/users/2" com os dados:
      | nome  | cargo          |
      | Maria | Senior QA Lead |
    Então o status da resposta deve ser 200
    E a resposta deve conter o campo "job" com valor "Senior QA Lead"

  @api @sad-path
  Cenário: Buscar usuário inexistente
    Dado que a API está disponível em "https://reqres.in"
    Quando eu faço uma requisição GET em "/api/users/9999"
    Então o status da resposta deve ser 404
