# language: pt
Funcionalidade: DemoQA - Elementos
  Como um estudante de automação
  Quero interagir com diferentes elementos no DemoQA
  Para praticar seletores e interações ricas

  @demoqa @elements
  Cenário: Preencher formulário de Text Box
    Dado que estou na página Text Box do DemoQA
    Quando eu preencho o nome "João Silva", email "joao@teste.com" e endereço "Rua A, 123"
    E clico em enviar
    Então devo ver os dados confirmados abaixo do formulário
