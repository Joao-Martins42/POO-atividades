# Relatório de Implementação, Operações CRUD e Persistência de Dados

Este documento apresenta a descrição detalhada da implementação do padrão Repository com ORMLite em Java/BlueJ para a entidade **Player**, demonstrando as operações de **Create**, **Read**, **Update** e **Delete** (CRUD) e a efetiva persistência dos dados em banco SQLite.

---

## 1. Descrição da Arquitetura e Estrutura de Classes

A arquitetura do projeto foi desenvolvida utilizando a biblioteca **ORMLite** para o mapeamento objeto-relacional (ORM) e conexões com banco de dados SQLite. O projeto é composto por três classes principais:

- **Player:** Classe de modelo anotada com `@DatabaseTable` e `@DatabaseField` e ela foi escolhida devido a ideia do projeto ser um mini videogame e a estrutura dela é basicamente a geração de fatores/status basicos que um player precisa dentro da estrutura de um jogo.
- **Database:** Classe responsável por estabelecer e encerrar a conexão JDBC com o banco de dados SQLite.
- **PlayerRepository:** Classe responsável por intermediar as operações do DAO (*Data Access Object*) entre a aplicação e o banco.

### Diagrama de Classes
![Diagrama de Classes](assets/Captura%20de%20tela%202026-11-02%20194600.png)

---

## 2. Código Fonte da Implementação

### Classe Player
A classe possui os atributos `id` (chave primária auto-incrementada), `hp`, `dano` e `nome`, acompanhados de seus respectivos getters e setters.

![Código da Classe Player - Parte 1](assets/Captura%20de%20tela%202026-11-02%20194622.png)

![Código da Classe Player - Parte 2](assets/Captura%20de%20tela%202026-11-02%20194637.png)

### Classe PlayerRepository
Implementa a inicialização da tabela via ORMLite (`TableUtils.createTableIfNotExists`) e os métodos das operações CRUD: `create`, `update`, `delete`, `loadFromId` e `loadAll`.

![Código PlayerRepository - Métodos de Inicialização, Create e Update](assets/Captura%20de%20tela%202026-11-02%20194646.png)

![Código PlayerRepository - Métodos Delete, LoadFromId e LoadAll](assets/Captura%20de%20tela%202026-11-02%20194657.png)

### Classe Database
Gerencia a string de conexão `jdbc:sqlite:` e disponibiliza a fonte de conexão via driver JDBC.

![Código da Classe Database](assets/Captura%20de%20tela%202026-11-02%20194705.png)

---

## 3. Inicialização e Criação de Tabelas no Banco de Dados

Ao executar a aplicação e associar o repositório à classe `Database`, o ORMLite executa a instrução DDL para criação da tabela `player` no SQLite, caso ela não exista.

![Log do ORMLite confirmando a criação da tabela player](assets/Captura%20de%20tela%202026-11-02%20194735.png)

---

## 4. Demonstração e Comprovação das Operações CRUD

### 4.1. Operação CREATE (Inserção)

Para realizar a inserção, um objeto `Player` é instanciado em memória e enviado ao método `create` do `PlayerRepository`.

1. Instanciação e inspeção do objeto `player1` ("Jonas", HP 20, Dano 10):
![Inspeção do objeto player1 antes de salvar](assets/Captura%20de%20tela%202026-11-02%20194810.png)

2. Execução do método `playerRe1.create(player1)` gravando o primeiro registro:
![Retorno da execução de create para player1](assets/Captura%20de%20tela%202026-11-02%20194835.png)

3. Inserção de um segundo registro (`player3`):
![Retorno da execução de create para player3](assets/Captura%20de%20tela%202026-11-02%20194941.png)

---

### 4.2. Operação READ (Consulta)

As consultas são demonstradas buscando um registro por seu ID ou listando todos os registros persistidos.

1. Consulta por ID específico através do método `loadFromId(2)`:
![Resultado da busca por ID com loadFromId](assets/Captura%20de%20tela%202026-11-02%20194954.png)

2. Consulta geral de todos os registros persistidos através do método `loadAll()`:
![Execução do método loadAll](assets/Captura%20de%20tela%202026-11-02%20195004.png)

3. Inspeção da lista retornada confirmando a recuperação dos objetos armazenados (tamanho = 2):
![Inspeção da lista retornada confirmando 2 registros recuperados](assets/Captura%20de%20tela%202026-11-02%20195014.png)

---

### 4.3. Operação UPDATE (Atualização)

A atualização é demonstrada ao alterar os atributos de uma instância em memória e persistir a mudança no banco de dados através do método `update`.

![Inspeção do objeto player2 com atributos modificados (HP 20, Dano 10, ID 1)](assets/Captura%20de%20tela%202026-11-02%20194822.png)

---

### 4.4. Operação DELETE (Exclusão)

A exclusão é realizada chamando o método `delete(player)`. A confirmação da remoção é comprovada ao inspecionar a nova lista no banco, que passa a ter tamanho 1.

![Inspeção de arrayLis2 indicando que a lista passou a ter tamanho 1 após a exclusão](assets/Captura%20de%20tela%202026-11-02%20195051.png)

---

## 5. Evidências de Persistência de Dados e Ambiente de Execução

As imagens abaixo comprovam os objetos mantidos na área de trabalho do BlueJ durante a execução dos testes e a criação física dos arquivos de banco de dados SQLite no sistema operacional.

### Objetos Instanciados no BlueJ
![Instâncias de Database, PlayerRepository, Player e listas na bancada do BlueJ](assets/Captura%20de%20tela%202026-11-02%20195331.png)

### Gravação Física do Banco de Dados no Disco
Confirmação da criação dos arquivos `db` e `db1` no diretório do projeto, garantindo a permanência dos dados após o encerramento da execução.

![Listagem do diretório do sistema operacional exibindo os arquivos de banco db e db1](assets/Captura%20de%20tela%202026-11-02%20195245.png)