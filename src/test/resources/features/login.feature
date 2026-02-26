# language: pt
Funcionalidade: Login - The Internet
  Como um usuário da aplicação
  Quero realizar login com minhas credenciais
  Para acessar as funcionalidades protegidas do sistema

  Contexto:
    Dado que estou na página de login

  @login @happy-path
  Cenário: Login com sucesso com credenciais válidas
    Quando eu preencho o campo de usuário com "tomsmith"
    E eu preencho o campo de senha com "SuperSecretPassword!"
    E eu clico no botão de login
    Então eu devo ser redirecionado para a área segura
    E devo ver a mensagem "You logged into a secure area!"

  @login @sad-path
  Cenário: Login com senha incorreta
    Quando eu preencho o campo de usuário com "tomsmith"
    E eu preencho o campo de senha com "senhaerrada"
    E eu clico no botão de login
    Então devo ver a mensagem de erro "Your password is invalid!"

  @login @sad-path
  Cenário: Login com usuário inválido
    Quando eu preencho o campo de usuário com "usuario_invalido"
    E eu preencho o campo de senha com "SuperSecretPassword!"
    E eu clico no botão de login
    Então devo ver a mensagem de erro "Your username is invalid!"

  @login @logout
  Cenário: Logout após login com sucesso
    Quando eu preencho o campo de usuário com "tomsmith"
    E eu preencho o campo de senha com "SuperSecretPassword!"
    E eu clico no botão de login
    E eu clico no botão de logout
    Então eu devo ver a mensagem de logout "You have successfully signed out!"
