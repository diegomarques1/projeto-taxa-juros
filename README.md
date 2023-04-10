# Projeto - Taxa de juros mensal
Um projeto em Java que consiste em uma API RESTful para puxar registros da API do Banco Central do Brasil, salvá-los no banco da aplicação e realizar operações em cima dos dados obtidos. Esses recursos são disponibilizados por endpoints e utilizáveis a partir de requisições HTTP.

# Tabela de Conteúdos
* [Informações gerais](#informações-gerais)
* [Tecnologias utilizadas](#tecnologias-utilizadas)
* [Funcionalidades](#funcionalidades)
    * [Onboarding](#onboarding)
        * [POST /onboarding/{top}](#post-httplocalhost8001onboardingtop)
    * [Aplicação principal](#aplicação-principal)
        * [GET /juros](#get-httplocalhost8001juros)
        * [GET /juros com paginação](#get-httplocalhost8001jurossize20page0)
        * [GET /juros/{id}](#get-httplocalhost8001jurosid)
        * [GET /juros/anoMes/{anoMes}](#get-httplocalhost8001jurosanomesanomes)
        * [POST /juros](#post-httplocalhost8001juros)
        * [PUT /juros/{id}](#put-httplocalhost8001jurosid)
        * [DELETE /juros/{id}](#delete-httplocalhost8001jurosid)
    * [Collection - Insomnia](#collection---insomnia)
* [Setup](#setup)
    * [Github - Site](#github---site)
    * [CLI](#cli)
    * [Importar projeto no IntelliJ](#importar-projeto-no-intellij)
    * [Criação do database](#criação-do-database)
    * [Execução do projeto no IntelliJ](#execução-do-projeto-no-intellij)
    * [Utilização da aplicação](#utilização-da-aplicação)
* [Exemplos de uso](#exemplos-de-uso)
* [Autor](#autor)

# Informações gerais
O projeto foi desenvolvido na linguagem Java e, sendo assim, tentou seguir padrões MVC para sua arquitetura, com controllers, models, services e afins. Como a aplicação é focada no backend, não existem classes views - não há frontend.
O fluxo da aplicação é definido pela seguinte maneira: primeiro, são obtidos os dados da API de taxas de juros mensal do Banco Central do Brasil. Em seguida, os registros são salvos no banco de dados da aplicação por meio de um endpoint POST.
Com os registros em posse, a aplicação fornece operações possíveis a serem realizadas pelo usuário, todas disponibilizadas por meio de endpoints da aplicação. Existem operações diversas, que consistem em:
* Requisições GET para: consultar registros com paginação, consultar registro por ID, e consultar registros por ano-mês;
* Requisição POST para: salvar novo registro no banco;
* Requisição PUT para: atualizar objeto do banco;
* Requisição DELETE para: deletar registro por ID.

Os endpoints serão documentados na seção de [Funcionalidades](#funcionalidades). As funcionalidades também foram testadas por meio de testes unitários e alguns testes de integração, resultando em uma cobertura de teste de 100% de linhas e métodos para as controladoras e para os serviços.

# Tecnologias utilizadas
Todas as tecnologias abaixo foram utilizadas durante o desenvolvimento do projeto, em conjunto da IDE [IntelliJ](https://www.jetbrains.com/pt-br/idea/) e do cliente REST [Insomnia](https://insomnia.rest/download).

- [Java versão: 11 - OpenJDK Azul Zulu 11.0.18](https://www.azul.com/downloads/?package=jdk)
- [Spring Boot versão: 2.7.8](https://start.spring.io/)
- [MySQL versão: 8.0.32](https://www.mysql.com/downloads/)
- [Git](https://git-scm.com/)
- JPA, OpenFeign, Lombok, JUnit 5, Mockito, MockMVC

# Funcionalidades
Nesta seção, as features presentes no projeto serão explicadas e referenciadas em relação aos seus endpoints.

Link da API do Banco Central: https://dadosabertos.bcb.gov.br/dataset/taxas-de-juros-de-operacoes-de-credito/resource/cb682eb9-e9a9-497f-a1c4-299b385d09b9

Link da base de dados da API: https://olinda.bcb.gov.br/olinda/servico/taxaJuros/versao/v2/odata/TaxasJurosMensalPorMes?$format=json&[Outros parâmetros]

Base URL: http://localhost:8001/
## Onboarding

### `POST` http://localhost:8001/onboarding/{top}

- `top` - quantidade de registros a serem recuperados da API externa

Por meio do FeignClient, recupera-se a quantidade de dados definida em `top` da API externa do Banco Central.

## Aplicação principal

### `GET` http://localhost:8001/juros

Retorna todos os registros no banco de dados.

### `GET` http://localhost:8001/juros?size=20&page=0

Adicionalmente, podem ser colocados parâmetros de paginação na requisição, como size e page.
- `size` - quantidade de registros a serem retornados
- `page` - página a ser exibida

Ou seja, a requisição de exemplo retorna a primeira página com 20 registros do banco de dados.

### `GET` http://localhost:8001/juros/{id}

- `id` - ID do registro no banco

Retorna o registro de ID `id`.

### `GET` http://localhost:8001/juros/anoMes/{anoMes}

- `anoMes` - ano-mês no formato `YYYY-MM`

Retorna os registros com tal `anoMes`.

### `POST` http://localhost:8001/juros

Cria um registro no banco de dados. Para ser utilizado, precisa ter no corpo da requisição um objeto com os atributos necessários no formato JSON.

### `PUT` http://localhost:8001/juros/{id}

- `id` - ID do registro no banco

Atualiza o registro no banco com ID `id` a partir de um objeto JSON descrito no corpo da requisição.

### `DELETE` http://localhost:8001/juros/{id}

- `id` - ID do registro no banco

Deleta o registro de ID `id` do banco.

## Collection - Insomnia

A collection do Insomnia pode ser encontrada [aqui](https://github.com/diegomarques1/projeto-taxa-juros/blob/main/insomnia/Insomnia_Collection.json). Além das requisições descritas acima, ela também contém alguns cenários de exceção e um GET na API do Banco Central, para conseguir verificar se a API externa está on/off.

# Setup

Para executar o projeto, todas as [tecnologias citadas](#tecnologias-utilizadas) devem ser utilizadas nas versões informadas. Além disso, é necessário que você já tenha instalado em sua máquina o seguinte:
- [Java versão: 11 - OpenJDK Azul Zulu 11.0.18](https://www.azul.com/downloads/?package=jdk)
- [MySQL versão: 8.0.32](https://www.mysql.com/downloads/)
- [IntelliJ IDEA](https://www.jetbrains.com/pt-br/idea/)
- [Insomnia](https://insomnia.rest/download)
- [Git](https://git-scm.com/)

**ATENÇÃO:** ao baixar a JDK do Java, não se esqueça de configurar as variáveis de ambiente JAVA_HOME e Path. Caso esteja com problemas na configuração, recomendo [este vídeo](https://www.youtube.com/watch?v=QekeJBShCy4) - apenas baixe alguma versão 11 ao invés da 17.

Feito isso, é preciso baixar o projeto aqui do Github. Podemos fazer isso de duas maneiras: pelo site ou pela interface de linha de comando (CLI). Escolha a mais agradável pra você.

## Github - Site

1. Clique no botão verde descrito por "Code";
2. Selecione a opção Download ZIP;
3. Selecione a pasta desejada e faça download do arquivo;
4. Descompacte o zip com alguma ferramenta adequada, tal como WinRAR.
## CLI

```bash
# 1. Clone o repositório
git clone https://github.com/diegomarques1/projeto-taxa-juros
```

Baixado o conteúdo do projeto, para executá-lo basta utilizar a IDE IntelliJ:

## Importar projeto no IntelliJ

1. Abra o IntelliJ;
2. Clique em "Open";
3. Procure a pasta na qual o conteúdo foi baixado;
4. Selecione o arquivo "pom.xml";
5. Selecione a opção "Open as project".

## Criação do database

1. Abra o MySQL Workbench;
2. Clique no + ao lado de MySQL Connections;
3. Coloque um nome qualquer - recomendo o nome taxa-juros;
4. Garanta que a porta da conexão é 3306;
5. Atualize as informações de username e password no arquivo /com/desafio/taxajuros/application.properties, se necessário.

## Execução do projeto no IntelliJ

1. Abra o arquivo "pom.xml" e verifique se as dependências foram baixadas com sucesso;
2. Procure o arquivo TaxajurosApplication;
3. Clique com botão direito e selecione a opção "Run ...";

Após um certo tempo, devem começar a aparecer informações no terminal, algo similar à imagem abaixo:

![Screenshot](https://github.com/diegomarques1/projeto-taxa-juros/blob/main/img/exemplo-spring-execu%C3%A7%C3%A3o.png)

Se a aplicação continuar rodando, o projeto Spring Boot está funcionando.

## Utilização da aplicação

Para utilizar a aplicação, recomendo o uso do Insomnia. Você pode importar a collection que deixei [aqui](#collection---insomnia) ou então fazer suas próprias requisições.

**ATENÇÃO:** garanta que a sua aplicação esteja rodando de fundo no IntelliJ.

1. Abra o Insomnia;
2. Clique no + e escolha "HTTP Request";
3. Selecione o tipo da sua requisição (GET, POST, ...);
4. Em seguida, coloque a URL base (http://localhost:8001/) e complete com o endpoint desejado;
5. Por fim, clique em "Send" e verá o resultado da sua requisição HTTP.

# Exemplos de uso

Abaixo, seguem exemplos de como utilizar o Insomnia para acessar recursos da aplicação. A única diferença é que utilizei `_.base_url` como um apelido para o localhost: 
- POST /onboarding/100
![Screenshot](https://github.com/diegomarques1/projeto-taxa-juros/blob/main/img/insomnia-onboarding.png)
- GET /juros/10
![Screenshot](https://github.com/diegomarques1/projeto-taxa-juros/blob/main/img/insomnia-getbyid.png)
- POST /juros
![Screenshot](https://github.com/diegomarques1/projeto-taxa-juros/blob/main/img/insomnia-post.png)

# Autor
Feito por Diego Souza Lima Marques [![Linkedin Badge](https://img.shields.io/badge/-LinkedIn-blue?style=flat-square&logo=Linkedin&logoColor=white&link=https://www.linkedin.com/in/diego-souza-lima-marques-6a0a3b22b/)](https://www.linkedin.com/in/diego-souza-lima-marques-6a0a3b22b/) 
