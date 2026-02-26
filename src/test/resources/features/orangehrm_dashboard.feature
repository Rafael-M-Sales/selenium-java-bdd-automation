# language: pt
Funcionalidade: Dashboard e Navegação - OrangeHRM
  Como um administrador logado no OrangeHRM
  Quero navegar pelo sistema
  Para acessar os módulos administrativos de RH

  Contexto:
    Dado que estou logado no OrangeHRM como "Admin"

  @orangehrm @dashboard @happy-path
  Cenário: Verificar elementos do Dashboard
    Então devo ver o painel principal do Dashboard
    E devo ver o menu lateral disponível

  @orangehrm @navegacao @admin
  Cenário: Navegar para o módulo Admin
    Quando eu clico em "Admin" no menu lateral
    Então devo ver a listagem de usuários do sistema
    E o título da página deve ser "Admin"

  @orangehrm @navegacao @pim
  Cenário: Navegar para o módulo PIM
    Quando eu clico em "PIM" no menu lateral
    Então devo ver a lista de funcionários
    E o título da página deve ser "PIM"

  @orangehrm @busca @admin
  Cenário: Buscar usuário Admin na listagem
    Quando eu clico em "Admin" no menu lateral
    E eu pesquiso pelo usuário "Admin" na busca
    E eu clico em "Search"
    Então devo ver "Admin" na lista de resultados

  @orangehrm @navegacao @leave
  Cenário: Verificar módulo de Licenças
    Quando eu clico em "Leave" no menu lateral
    Então o título da página deve ser "Leave"
