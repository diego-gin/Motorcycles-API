# Motorcycles API

[English](README.md) | [Português](README.pt-BR.md)

Uma aplicação full-stack de catálogo de motocicletas desenvolvida com Spring Boot, PostgreSQL e JavaScript.

O projeto fornece uma API REST para gerenciamento de dados de motocicletas, uma interface web responsiva para navegação pelo catálogo, autenticação baseada em sessão para operações administrativas e documentação interativa da API com Swagger UI.

## Funcionalidades

- Catálogo de motocicletas com especificações técnicas detalhadas
- Busca e ordenação por nome, ano de lançamento e cilindrada
- Frontend responsivo desenvolvido com HTML, CSS e JavaScript
- API RESTful com operações CRUD
- Persistência em PostgreSQL com Spring Data JPA
- Validação de dados e respostas de erro estruturadas
- Autenticação de administrador baseada em sessão
- Proteção CSRF
- Documentação interativa da API com Swagger UI

## Interface Web

Usuários podem fazer **Log in** para autenticar sua sessão e acessar **Swagger Docs**, utilizando os privilégios da role de administrador para realizar operações protegidas da API.

```text
Usuário público
│
└── Catálogo → Busca / Ordenação / Detalhes

Administrador
│
├── Log in → sessão autenticada
│
└── Swagger Docs → POST / PUT / DELETE
```

## Tecnologias

**Backend**
- Java 25
- Spring Boot 4.1
- Spring Web
- Spring Data JPA
- Spring Security
- Hibernate
- PostgreSQL
- Maven

**Frontend**
- HTML
- CSS
- JavaScript

**Documentação**
- OpenAPI
- Swagger UI

## API

| Método | Endpoint | Descrição | Acesso |
| --- | --- | --- | --- |
| GET | `/api/auth/status` | Verificar o status da autenticação | Público |
| GET | `/api/motorcycles` | Listar motocicletas | Público |
| GET | `/api/motorcycles/{id}` | Obter detalhes de uma motocicleta | Público |
| POST | `/api/motorcycles` | Criar uma motocicleta | Admin |
| PUT | `/api/motorcycles/{id}` | Atualizar uma motocicleta | Admin |
| DELETE | `/api/motorcycles/{id}` | Excluir uma motocicleta | Admin |

## Autenticação

A aplicação utiliza Spring Security com autenticação baseada em sessão.

A consulta ao catálogo é pública. A criação, atualização e exclusão de motocicletas requerem autenticação como administrador.

A proteção CSRF está habilitada para operações autenticadas.

## Configuração

A aplicação requer PostgreSQL e as seguintes variáveis de ambiente:

```text
DB_PASSWORD
ADMIN_USERNAME
ADMIN_PASSWORD
```

A configuração do banco de dados espera:

```text
Database: motorcycles_db
Port: 5432
```

Para instruções detalhadas de configuração, consulte o guia [Variáveis de Ambiente](https://github.com/diego-gin/Motorcycles-API/wiki/Vari%C3%A1veis-de-Ambiente).

## Executando o Projeto

Clone o repositório:

```bash
git clone https://github.com/diego-gin/Motorcycles-API.git
cd Motorcycles-API
```

Crie o banco de dados PostgreSQL:

```sql
CREATE DATABASE motorcycles_db;
```

Configure as variáveis de ambiente necessárias: [consulte o guia aqui](https://github.com/diego-gin/Motorcycles-API/wiki/Vari%C3%A1veis-de-Ambiente).

Em seguida, inicie a aplicação:

**Windows**

```powershell
.\mvnw spring-boot:run
```

**Linux / macOS**

```bash
./mvnw spring-boot:run
```

Depois, acesse:

```text
http://localhost:8080
```

## Documentação da API

O Swagger UI está disponível em:

```text
http://localhost:8080/swagger-ui/index.html
```

Os endpoints públicos podem ser testados diretamente. Após realizar o login como administrador, as operações protegidas também podem ser executadas pelo Swagger UI utilizando a sessão autenticada do navegador.

## Arquitetura

```text
Frontend
HTML / CSS / JavaScript
        │
        ├── Spring Security
        │
        ├── REST Controller
        │
        ├── Service
        │
        ├── Repository
        │
        └── JPA / Hibernate
                    │
                PostgreSQL
```

## Licença

Este projeto foi desenvolvido para fins acadêmicos e de portfólio.