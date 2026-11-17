# Gerenciador de Inimigos (CRUD JavaFX + ORMLite + SQLite)

Projeto desenvolvido para a disciplina de **Programação Orientada a Objetos (POO - Atividade 11-03)** utilizando a arquitetura **MVC (Model-View-Controller)** em Java, com interface gráfica construída em **JavaFX** e persistência de dados em **SQLite** através do **ORMLite**.

## Descrição da Entidade Escolhida

A entidade principal do sistema é o **`Inimigo`**, projetada para representar adversários ou monstros em um contexto de jogos digitais (como RPGs ou jogos de ação). Ela permite catalogar e balancear atributos básicos de combate.

### Atributos (`model.Inimigo` / Tabela `inimigo`)

| Atributo | Tipo (Java) | Tipo (Banco) | Descrição | 
| ----- | ----- | ----- | ----- | 
| **`id`** | `int` | `INTEGER` (PK, Auto-incremento) | Identificador único do inimigo gerado automaticamente pelo banco de dados (`@DatabaseField(generatedId = true)`). | 
| **`nome`** | `String` | `STRING` | Nome ou título de identificação do inimigo (ex: *"Zumbi"*). | 
| **`vida`** | `int` | `INTEGER` | Quantidade de pontos de vida (HP) que o inimigo possui. | 
| **`dano`** | `int` | `INTEGER` | Valor de ataque/dano causado pelo inimigo. | 

## Arquitetura do Projeto

O sistema está estruturado em três pacotes principais no **BlueJ**:

* **`model`**: Contém a classe de conexão com o SQLite (`Database.java`), a entidade mapeada para o banco (`Inimigo.java`) e o repositório responsável pelas operações de persistência via ORMLite (`InimigoRepositorio.java`).
* **`view`**: Contém a interface visual definida em FXML (`app.fxml`), a classe inicializadora da janela JavaFX (`AppView.java`) e o modelo adaptador de propriedades observáveis para a `TableView` (`Inimigo.java`).
* **`controller`**: Contém o controlador (`AppController.java`), que gerencia os eventos dos botões, validações de campos e a comunicação entre a interface gráfica e o banco de dados.

## Instruções para Executar o Projeto

### Pré-requisitos

* **Java JDK** (versão 11 ou superior com suporte ao **JavaFX** configurado).
* **IDE BlueJ** (versão 4.x ou 5.x).
* Bibliotecas necessárias no *Classpath* do BlueJ (incluídas na pasta `+libs` do projeto):
  * `ormlite-core` e `ormlite-jdbc`
  * `sqlite-jdbc`
  * Bibliotecas do **JavaFX** (`javafx.controls`, `javafx.fxml`, etc.)

### Passo a Passo

1. **Abra o Projeto:**
   * Inicie o **BlueJ**.
   * Vá em `Projeto` > `Abrir Projeto...` e selecione a pasta **`POO-atividade11-03`**.

2. **Compile as Classes:**
   * Clique no botão **Compilar** no painel lateral esquerdo (ou pressione `Ctrl + K`) para garantir que todos os pacotes (`model`, `view`, `controller`) estejam compilados sem erros.

3. **Execute a Aplicação:**
   * Abra o pacote **`controller`** (dê um duplo clique sobre ele).
   * Clique com o botão direito sobre a classe **`AppController`**.
   * Selecione o método **`void main(String[] args)`** e clique em **OK** na janela de argumentos.
   * *Alternativa:* Você também pode executar instanciando `AppView` dentro do pacote `view` e invocando o método `start` ou `run`.

4. **Banco de Dados:**
   * Na primeira execução, o arquivo `app.sqlite` e a tabela `inimigo` serão criados automaticamente na raiz do projeto.

## Capturas de Tela e Funcionalidades Implementadas

### 1. Estrutura do Projeto no BlueJ (Arquitetura MVC)

Visão geral dos pacotes principais (`model`, `view`, `controller`) e dos diagramas de classes internos de cada camada.

* **Pacotes Principais:**
  ![Estrutura de Pacotes MVC](assets/Captura%20de%20tela%202026-11-16%20225044.png)

* **Pacote `model` (`Database`, `Inimigo` e `InimigoRepositorio`):**
  ![Diagrama do Pacote Model](assets/Captura%20de%20tela%202026-11-16%20225049.png)

* **Pacote `view` (`AppView`, `app.fxml` e `Inimigo`):**
  ![Diagrama do Pacote View](assets/Captura%20de%20tela%202026-11-16%20225106.png)

* **Pacote `controller` (`AppController`):**
  ![Diagrama do Pacote Controller](assets/Captura%20de%20tela%202026-11-16%20225111.png)

### 2. Implementação das Camadas (Código-Fonte)

#### Camada Model
* **Conexão SQLite (`model.Database`):**
  ![Código Database](assets/Captura%20de%20tela%202026-11-16%20225118.png)

* **Entidade Mapeada (`model.Inimigo`):**
  ![Código Model Inimigo](assets/Captura%20de%20tela%202026-11-16%20225129.png)

* **Repositório ORMLite (`model.InimigoRepositorio`):**
  ![Código InimigoRepositorio Parte 1](assets/Captura%20de%20tela%202026-11-16%20225148.png)
  ![Código InimigoRepositorio Parte 2](assets/Captura%20de%20tela%202026-11-16%20225154.png)

#### Camada Controller
* **Controlador Principal (`controller.AppController`):**
  ![Código AppController Parte 1](assets/Captura%20de%20tela%202026-11-16%20225209.png)
  ![Código AppController Parte 2](assets/Captura%20de%20tela%202026-11-16%20225216.png)
  ![Código AppController Parte 3](assets/Captura%20de%20tela%202026-11-16%20225220.png)
  ![Código AppController Parte 4](assets/Captura%20de%20tela%202026-11-16%20225225.png)
  ![Código AppController Parte 5](assets/Captura%20de%20tela%202026-11-16%20225229.png)

#### Camada View
* **Inicialização JavaFX (`view.AppView`):**
  ![Código AppView](assets/Captura%20de%20tela%202026-11-16%20225244.png)

* **Modelo da Tabela (`view.Inimigo`):**
  ![Código View Inimigo Parte 1](assets/Captura%20de%20tela%202026-11-16%20225252.png)
  ![Código View Inimigo Parte 2](assets/Captura%20de%20tela%202026-11-16%20225254.png)

### 3. Demonstração das Operações CRUD na Interface

* **Tela Inicial e Listagem (`Read`):**
  Ao iniciar o sistema sem registros salvos, a tabela exibe a mensagem *"Não há conteúdo na tabela"* e mantém apenas o botão **Adicionar** habilitado.
  ![Tela Inicial](assets/Captura%20de%20tela%202026-11-16%20225302.png)

* **Cadastro de Novo Inimigo (`Create`):**
  Ao clicar em **Adicionar**, preencher os dados (`Id: 1`, `Nome: "Zumbi"`, `Vida: 10`, `Dano: 5`) e confirmar em **Salvar**, o registro é persistido no banco SQLite e exibido na tabela.
  ![Registro Criado](assets/Captura%20de%20tela%202026-11-16%20225338.png)

* **Edição de Registro (`Update`):**
  Ao selecionar o inimigo na tabela, clicar em **Atualizar**, alterar o campo `Vida` para `1678` e clicar em **Salvar**, a linha é atualizada tanto no banco quanto na interface.
  ![Registro Atualizado](assets/Captura%20de%20tela%202026-11-16%20225348.png)

* **Exclusão de Registro (`Delete`):**
  Com o registro selecionado, ao clicar em **Deletar**, o inimigo é removido do banco de dados SQLite e a tabela volta a ficar vazia.
  ![Registro Deletado](assets/Captura%20de%20tela%202026-11-16%20225432.png)