# Projeto DimDim: Modernização com Docker Compose

## 1. Visão Geral do Projeto

Este projeto simula a modernização tecnológica da empresa "DimDim", conforme o desafio proposto na disciplina de **DevOps Tools & Cloud Computing** da FIAP.

O objetivo principal é migrar uma aplicação de portfólio, originalmente em uma arquitetura tradicional, para um ambiente totalmente containerizado utilizando Docker Compose.
Essa migração visa resolver desafios como deploys manuais, inconsistências entre ambientes e dificuldades de escalabilidade, adotando uma arquitetura moderna, automatizada e eficiente.

A aplicação escolhida para este desafio foi a **API de Gerenciamento de Usuários**, que consiste em um serviço de backend e um banco de dados.

## 2. Arquitetura

Conforme solicitado, abaixo estão os diagramas que representam a arquitetura do sistema antes e depois da containerização.

### Arquitetura Atual (Antes da Migração)

A aplicação operava em um modelo tradicional, com o backend e o banco de dados instalados e configurados diretamente em um servidor dedicado, levando a um processo de deploy manual e demorado.

`[Inserir aqui o desenho da sua arquitetura antiga]`

### Arquitetura Futura (Após a Migração)

A nova arquitetura utiliza Docker Compose para orquestrar os serviços da aplicação e do banco de dados em contêineres isolados.
Isso garante ambientes padronizados, escalabilidade e um processo de deploy contínuo e confiável.

`[Inserir aqui o desenho da sua arquitetura nova com Docker]`

## 3. Tecnologias Utilizadas

* **Backend:** Java 21, Spring Boot 3
* **Banco de Dados:** MySQL / PostgreSQL
* **Build Tool:** Gradle
* **Containerização:** Docker e Docker Compose

## 4. Pré-requisitos

Antes de começar, garanta que você tenha as seguintes ferramentas instaladas:
* [Docker](https://www.docker.com/get-started)
* [Docker Compose](https://docs.docker.com/compose/install/) (geralmente já vem com o Docker Desktop)
* [Git](https://git-scm.com/)

## 5. Processo de Deploy (Passo a Passo)

Siga as instruções abaixo para clonar, configurar e executar o projeto em seu ambiente local.

### Passo 1: Clonar o Repositório
```bash
git clone https://github.com/Lu1zEdu/API-User
cd API-User
```

### Passo 2: Configurar Variáveis de Ambiente
Este projeto utiliza um arquivo `.env` para gerenciar as configurações sensíveis, como senhas de banco de dados. Crie um arquivo chamado `.env` na raiz do projeto, copiando o exemplo abaixo e preenchendo com os valores desejados.

**Exemplo de arquivo `.env`:**
```env
# Variáveis para o Banco de Dados MySQL
MYSQL_ROOT_PASSWORD=myrootpassword
MYSQL_DATABASE=user_api_db
MYSQL_USER=myuser
MYSQL_PASSWORD=mypassword

# URL de conexão usada pela API para se comunicar com o banco na rede Docker
SPRING_DATASOURCE_URL=jdbc:mysql://db-mysql:3306/user_api_db
```

### Passo 3: Subir os Contêineres
Com o Docker em execução, utilize o Docker Compose para construir as imagens e iniciar todos os serviços definidos no arquivo `docker-compose.yml`.

```bash
docker-compose up --build -d
```
* O comando `--build` força a reconstrução da imagem da sua aplicação, caso haja alterações no código.
* A flag `-d` (detached) executa os contêineres em segundo plano.

### Passo 4: Verificar os Serviços
Após alguns instantes, verifique se todos os contêineres estão rodando e saudáveis (`healthy`).

```bash
docker-compose ps
```
Você deverá ver o serviço da aplicação e do banco de dados com o status "Up" ou "running".

### Passo 5: Acessar a Aplicação
A aplicação estará disponível no seu navegador ou cliente de API no seguinte endereço:

* **API:** `http://20.195.170.11`
* **Documentação (Swagger):** `http://20.195.170.11:8080/swagger-ui.html`

## 6. Comandos Essenciais do Docker Compose

Abaixo estão os comandos essenciais para gerenciar o ciclo de vida da aplicação:

* **Iniciar todos os serviços em segundo plano:**
    ```bash
    docker-compose up -d
    ```

* **Parar e remover todos os contêineres, redes e volumes:**
    ```bash
    docker-compose down --volumes
    ```

* **Listar os contêineres em execução:**
    ```bash
    docker-compose ps
    ```

* **Visualizar os logs de um serviço específico (ex: api-app):**
    ```bash
    docker-compose logs -f api-app
    ```
    * Use `-f` para acompanhar os logs em tempo real.

## 7. Troubleshooting Básico

[cite_staAqui estão algumas soluções para problemas comuns que podem ocorrer durante a execução do projeto:

* **Problema:** A porta `8080` (ou `3306`) já está em uso.
    * **Solução:** Pare o serviço que está utilizando a porta ou altere a porta no arquivo `docker-compose.yml`. Por exemplo, para `ports: - "8081:8080"`.

* **Problema:** A aplicação não consegue se conectar ao banco de dados.
    * **Solução:** Verifique se os nomes dos serviços e as variáveis no arquivo `.env` (especialmente `SPRING_DATASOURCE_URL`) correspondem exatamente ao que está definido no `docker-compose.yml`.
    * Certifique-se também que a condição `service_healthy` está sendo usada no `depends_on`.

* **Problema:** Erros durante o comando `docker-compose up --build`.
    * **Solução:** Verifique a sintaxe do seu `Dockerfile` e do `docker-compose.yml`. Certifique-se de que o daemon do Docker está em execução. Execute o comando `docker-compose down --volumes` para limpar o ambiente antes de tentar novamente.

## 8. Equipe

| Nome Completo        | RM      |
| -------------------- | ------- |
| `[Seu Nome Completo]` | `[Seu RM]` |