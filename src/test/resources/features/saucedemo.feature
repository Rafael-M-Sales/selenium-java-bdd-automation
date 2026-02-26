# language: pt
Funcionalidade: SauceDemo - Selenium
  Como um usuário da Sauce Labs Demo Shop
  Quero validar o login e produtos
  Para garantir a integridade do sistema no Selenium Java

  @saucedemo @login
  Cenário: Login com usuário padrão e verificar inventário
    Dado que estou na página de login do SauceDemo (Selenium)
    Quando eu logo com "standard_user" e "secret_sauce"
    Então sou levado à página de inventário
    E vejo "6" produtos na lista
