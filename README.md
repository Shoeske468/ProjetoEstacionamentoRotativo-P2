# Projeto Estacionamento rotativo MaromoP2

Leo Leme de Oliveira / Inacio Bianchi <br>
Projeto EstacionamentoRotativo

## Histórico de prompts de IA utilizados

### Prompt 1 — Organização das classes do projeto

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como organizar as classes de um sistema de estacionamento rotativo em Java utilizando programação orientada a objetos? O projeto precisa ter gerenciamento de veículos, controle de vagas, registro de entrada e saída e cálculo de valores.
- **Resposta obtida:** A IA sugeriu dividir as responsabilidades entre classes específicas, utilizando uma classe para representar os veículos, outra para gerenciar o estacionamento e outra para armazenar as informações dos tickets. Também explicou como essa divisão facilita a manutenção e a organização do código.
- **Refinamentos feitos pela dupla:** Analisamos a estrutura sugerida e adaptamos os nomes e as responsabilidades das classes conforme a organização definida para o projeto.

### Prompt 2 — Validação dos dados dos veículos

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como implementar uma classe Veiculo em Java contendo placa e modelo, com validações para impedir o cadastro de informações inválidas?
- **Resposta obtida:** A IA apresentou uma estrutura de classe com atributos privados, construtor, métodos getters e validações para os dados recebidos. Também explicou a utilização de exceções para impedir que informações inválidas fossem armazenadas.
- **Refinamentos feitos pela dupla:** Adaptamos as validações para os campos utilizados no sistema e verificamos se os dados eram recebidos corretamente durante o cadastro.

### Prompt 3 — Utilização de Collections

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como utilizar Collections em Java para armazenar os veículos que estão estacionados e permitir consultas e ordenações?
- **Resposta obtida:** A IA explicou a utilização de listas por meio da interface `List` e da implementação `ArrayList`. Também apresentou exemplos de inclusão, remoção, pesquisa e ordenação dos elementos armazenados.
- **Refinamentos feitos pela dupla:** Utilizamos a estrutura apresentada como referência para organizar os veículos cadastrados e adaptar os métodos de consulta e listagem.

### Prompt 4 — Criação de exceção personalizada

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como criar uma exceção personalizada em Java para informar quando todas as vagas do estacionamento estiverem ocupadas?
- **Resposta obtida:** A IA demonstrou como criar uma classe de exceção personalizada utilizando `Exception` e como lançar essa exceção com `throw` quando não existirem vagas disponíveis. Também apresentou a utilização de `try` e `catch` para tratar a situação.
- **Refinamentos feitos pela dupla:** Integramos a exceção à lógica de registro de entrada, permitindo que o sistema informe quando não há vagas disponíveis.

### Prompt 5 — Cálculo do valor da estadia

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como calcular o valor que um veículo deve pagar ao sair do estacionamento, considerando o horário de entrada e o horário de saída?
- **Resposta obtida:** A IA apresentou uma solução utilizando as classes de data e horário do Java, especialmente `LocalDateTime` e `Duration`, para calcular o tempo de permanência. Também explicou como utilizar o resultado para determinar o valor da estadia.
- **Refinamentos feitos pela dupla:** Adaptamos o cálculo às regras de cobrança estabelecidas para o estacionamento e integramos o resultado à geração do ticket.

### Prompt 6 — Implementação da interface Tarifavel

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como criar e implementar uma interface chamada `Tarifavel` em Java para definir um método responsável pelo cálculo do preço da estadia?
- **Resposta obtida:** A IA explicou como declarar uma interface com um método abstrato e como fazer uma classe implementar essa interface utilizando `implements`. Também apresentou um exemplo de implementação do método responsável pelo cálculo.
- **Refinamentos feitos pela dupla:** Aplicamos a interface na estrutura do projeto e ajustamos o método para atender à lógica de cobrança utilizada no estacionamento.

### Prompt 7 — Ordenação dos veículos

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como implementar a ordenação dos veículos estacionados em ordem alfabética ou por placa utilizando Java?
- **Resposta obtida:** A IA apresentou alternativas de ordenação utilizando `Comparable`, `Comparator` e `Collections.sort()`. Explicou a diferença entre definir uma ordenação padrão na própria classe e criar critérios de ordenação externos.
- **Refinamentos feitos pela dupla:** Utilizamos a alternativa compatível com a estrutura existente e ajustamos o método de consulta para apresentar os veículos ordenados.

### Prompt 8 — Conversão do sistema para interface gráfica

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como transformar o sistema de estacionamento que funciona pelo terminal em uma interface gráfica utilizando Java Swing, mantendo as funcionalidades já implementadas?
- **Resposta obtida:** A IA sugeriu utilizar `JFrame` para criar a janela principal, `JPanel` para organizar os componentes e `JButton` para executar as operações. Também explicou como conectar os elementos gráficos aos métodos já existentes no sistema.
- **Refinamentos feitos pela dupla:** Optamos por manter as classes responsáveis pelas regras de negócio e criar uma interface separada para realizar as operações por meio dos botões.

### Prompt 9 — Criação dos campos de entrada

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como adicionar campos de texto na interface Swing para que o usuário possa informar a placa e o modelo do veículo antes de registrar sua entrada?
- **Resposta obtida:** A IA apresentou a utilização de `JTextField` para receber os dados e `JLabel` para identificar cada campo. Também mostrou como recuperar os valores digitados utilizando o método `getText()`.
- **Refinamentos feitos pela dupla:** Posicionamos os campos na janela e conectamos as informações digitadas ao método responsável pelo registro de entrada.

### Prompt 10 — Organização dos botões e eventos

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como criar botões na interface Swing para registrar entrada, registrar saída, consultar veículos estacionados, visualizar o histórico e consultar as vagas disponíveis?
- **Resposta obtida:** A IA demonstrou a criação de componentes `JButton` e a utilização de `addActionListener()` para executar métodos quando o usuário clicar em cada botão. Também explicou como associar cada evento à funcionalidade correspondente.
- **Refinamentos feitos pela dupla:** Organizamos os botões na janela e associamos cada um aos métodos responsáveis pelas operações do estacionamento.

### Prompt 11 — Exibição de relatórios na interface

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como criar uma área de relatório na interface gráfica para exibir os resultados das operações, como entradas, saídas, consultas e informações dos tickets?
- **Resposta obtida:** A IA sugeriu utilizar `JTextArea` para apresentar as informações e `JScrollPane` para permitir a rolagem do conteúdo. Também explicou a diferença entre `setText()`, que substitui o conteúdo, e `append()`, que adiciona novas informações ao final do texto.
- **Refinamentos feitos pela dupla:** Utilizamos `append()` para manter os registros anteriores na tela e permitir que novas operações fossem adicionadas ao relatório sem apagar as informações já exibidas.

### Prompt 12 — Integração e revisão da interface

- **Ferramenta:** ChatGPT
- **O que foi pedido:** Como integrar os componentes da interface gráfica com a classe de gerenciamento do estacionamento, garantindo que os botões executem as operações e apresentem os resultados corretamente?
- **Resposta obtida:** A IA apresentou uma estrutura de integração utilizando métodos específicos para cada operação, com tratamento de exceções e atualização da área de relatório. Também explicou a importância de separar a interface gráfica das regras de negócio.
- **Refinamentos feitos pela dupla:** Realizamos ajustes na organização da janela, nos eventos dos botões e na apresentação das mensagens, buscando manter a interface simples e funcional.

### Considerações finais

A inteligência artificial foi utilizada como ferramenta de apoio durante o desenvolvimento do projeto, auxiliando na compreensão de conceitos de Java, na implementação de funcionalidades e na criação da interface gráfica.

Sugestões foram analisadas e adaptadas conforme as necessidades do sistema, sendo necessário realizar ajustes para integrar os códigos à estrutura desenvolvida pela dupla.