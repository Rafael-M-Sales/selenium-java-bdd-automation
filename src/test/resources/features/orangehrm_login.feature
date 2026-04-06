# language: pt
Funcionalidade: Login - OrangeHRM
  Como um administrador do OrangeHRM
  Quero realizar login no sistema
  Para acessar o painel de gerenciamento de recursos humanos

  @orangehrm @login @happy-path
  Cenário: Login com credenciais de administrador válidas
    Dado que estou na página de login do OrangeHRM
    Quando eu faço login com usuário "Admin" e senha "admin123"
    Então devo ser redirecionado para o Dashboard
    E devo ver o menu "Dashboard" ativo

  @orangehrm @login @sad-path
  Cenário: Login com senha incorreta
    Dado que estou na página de login do OrangeHRM
    Quando eu faço login com usuário "Admin" e senha "senhaerrada"
    Então devo ver a mensagem de erro no OrangeHRM "Invalid credentials"

  @orangehrm @login @sad-path
  Cenário: Login com campos em branco
    Dado que estou na página de login do OrangeHRM
    Quando eu clico em "Login" sem preencher os campos
    Então devo ver as mensagens de validação obrigatórias

  @orangehrm @login @logout
  Cenário: Logout após login com sucesso
    Dado que estou logado no OrangeHRM como "Admin"
    Quando eu clico no menu do usuário e seleciono "Logout"
    Então devo ser redirecionado para a página de login
