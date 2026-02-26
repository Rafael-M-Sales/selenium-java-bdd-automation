# language: pt
Funcionalidade: Automation Exercise - Selenium
  Como um usuário do site Automation Exercise
  Quero validar o login e busca de produtos no Selenium Java

  @ae @login
  Cenário: Login com sucesso no Automation Exercise
    Dado que estou na página inicial do Automation Exercise (Selenium)
    Quando eu navego para Login
    E eu entro com e-mail "qa_teste@automationexercise.com" e senha "Senha@123"
    Então verifico o login com sucesso
