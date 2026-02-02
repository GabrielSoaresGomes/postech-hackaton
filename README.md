# 🔬 Sistema Inteligente de Triagem com IA

Projeto backend desenvolvido em Java com Spring Boot 3.4.4 e Java 21. Esta aplicação é um sistema de triagem inteligente que utiliza IA para gerenciar e priorizar solicitações de triagem:

## 🚀 Tecnologias Utilizadas

- Java 21
- Spring Boot 3.4.4
- Spring Security com JWT
- PostgreSQL
- Docker & Docker Compose
- Lombok
- Swagger
- Maven
- Spring Data JPA

## 🏛️ Arquitetura do Projeto

A arquitetura utilizada no projeto segue o padrão Clean Architecture com separação clara de responsabilidades entre camadas (application, domain, infrastructure, interface_adapter), com base em princípios DDD (Domain-Driven Design) e SOLID.

Toda a aplicação é conteinerizada com Docker e orquestrada com Docker Compose, permitindo a replicação do ambiente em diferentes máquinas com facilidade. O banco de dados utiliza PostgreSQL rodando em um contêiner isolado.

## 📦 Estrutura do Projeto

```
📁 src
┣ 📁 main
┃ ┣ 📁 java
┃ ┃ ┗ 📁 com.postech.hackaton
┃ ┃ ┣ 📁 application
┃ ┃ ┃ ┣ 📁 gateways
┃ ┃ ┃ ┣ 📁 mappers
┃ ┃ ┃ ┗ 📁 usecases
┃ ┃ ┣ 📁 domain
┃ ┃ ┃ ┣ 📁 enums
┃ ┃ ┃ ┣ 📄 Screening
┃ ┃ ┃ ┣ 📄 ScreeningItem
┃ ┃ ┃ ┣ 📄 ScreeningResult
┃ ┃ ┃ ┣ 📄 User
┃ ┃ ┃ ┣ 📄 UserAddress
┃ ┃ ┃ ┗ 📄 UserType
┃ ┃ ┣ 📁 dtos
┃ ┃ ┃ ┣ 📁 common
┃ ┃ ┃ ┣ 📁 exceptions
┃ ┃ ┃ ┣ 📁 requests
┃ ┃ ┃ ┣ 📁 responses
┃ ┃ ┃ ┣ 📁 security
┃ ┃ ┃ ┗ 📁 transfer
┃ ┃ ┣ 📁 exceptions
┃ ┃ ┣ 📁 infrastructure
┃ ┃ ┃ ┣ 📁 api
┃ ┃ ┃ ┣ 📁 config
┃ ┃ ┃ ┣ 📁 controllers
┃ ┃ ┃ ┣ 📁 data_sources
┃ ┃ ┃ ┣ 📁 entities
┃ ┃ ┃ ┗ 📁 mappers
┃ ┃ ┗ 📁 interface_adapter
┃ ┃ ┃ ┣ 📁 controllers
┃ ┃ ┃ ┣ 📁 gateways
┃ ┃ ┃ ┗ 📁 presenters
┃ ┗ 📁 resources
┃ ┃ ┣ 📄 application.yml
┃ ┃ ┗ 📁 db/migration
┗ 📁 test
```

## 🔐 Segurança

O projeto implementa autenticação e autorização com Spring Security e JWT tokens, com suporte para diferentes perfis de usuário:
- ADMIN: Gerenciador do sistema
- HEALTH_PROFESSIONAL: Profissional de saúde
- PATIENT: Paciente

## 🐳 Como Executar

### Com Docker Compose
```bash
docker-compose up
```

### Sem Docker (local)
```bash
mvn clean install
mvn spring-boot:run
```

## 📊 Documentação da API

A documentação Swagger estará disponível em:
```
http://localhost:2005/swagger-ui.html
```

## 🔄 Migrações de Banco de Dados

As migrações são gerenciadas com Flyway e estão localizadas em `src/main/resources/db/migration/`

## 📝 Licença

Este projeto é desenvolvido como parte do programa PosTech de Arquitetura e Desenvolvimento em Java.

