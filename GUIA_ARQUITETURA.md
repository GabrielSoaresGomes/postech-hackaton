# PosTech Hackaton - Triagem Inteligente com IA

Este é um projeto de exemplo estruturado com **Clean Architecture**, **Spring Security** e **JWT Authentication** para um sistema de triagem inteligente com IA.

## 🚀 Quick Start

### Pré-requisitos
- Java 21
- Docker e Docker Compose
- Maven 3.9.9

### Instalando o Ollama e seu modelo necessário
```text 
1- Instalar o Ollama para seu sistema operacional. (https://ollama.com/)
2- Assim que a instalação finalizar, já abrirá a GUI ( Não necessária ) e permitirá comandos no terminal/powershell
3- Instalar o modelo que usamos no projeto através do terminal/powershell:
>>> ollama pull llama3.1
```

### Instalação e Execução

#### Opção 1: Com Docker Compose
```bash
cd /Users/brunobender/git/postech-hackaton
docker-compose up
```

A aplicação estará disponível em: `http://localhost:2005`

#### Opção 2: Execução Local
```bash
cd /Users/brunobender/git/postech-hackaton

# Instalar e compilar
./mvnw clean install

# Executar a aplicação
./mvnw spring-boot:run
```

## 📚 Documentação da API

Acesse a documentação interativa do Swagger:
```
http://localhost:2005/swagger-ui.html
```

## 🏗️ Arquitetura do Projeto

O projeto segue o padrão **Clean Architecture** com as seguintes camadas:

### Estrutura de Diretórios

```
src/main/java/com/postech/hackaton/
├── domain/                    # Entidades de domínio (regras de negócio)
│   ├── User.java
│   ├── Screening.java
│   ├── ScreeningItem.java
│   ├── ScreeningResult.java
│   ├── UserAddress.java
│   └── enums/
│       ├── UserType.java
│       └── ScreeningPriority.java
│
├── application/               # Casos de uso (business logic)
│   ├── gateways/             # Interfaces para abstração
│   │   ├── UserGateway.java
│   │   └── ScreeningGateway.java
│   ├── mappers/              # Conversão entre camadas
│   └── usecases/             # Lógica de negócio
│       ├── CreateUserUseCase.java
│       ├── CreateScreeningUseCase.java
│       └── GetScreeningUseCase.java
│
├── interface_adapter/         # Adaptadores de interface
│   ├── gateways/             # Implementações das gateways
│   │   ├── UserGatewayImpl.java
│   │   └── ScreeningGatewayImpl.java
│   ├── controllers/          # REST Controllers (será expandido)
│   └── presenters/           # Formatadores de resposta
│
├── infrastructure/           # Implementação técnica
│   ├── config/              # Configurações (Security, OpenAPI)
│   │   ├── SecurityConfig.java
│   │   └── OpenApiConfig.java
│   ├── controllers/         # Controllers REST
│   │   ├── AuthController.java
│   │   ├── ScreeningController.java
│   │   └── UserController.java
│   ├── entities/            # Entidades JPA
│   │   ├── UserEntity.java
│   │   ├── ScreeningEntity.java
│   │   ├── ScreeningItemEntity.java
│   │   ├── ScreeningResultEntity.java
│   │   └── UserAddressEntity.java
│   ├── data_sources/        # Repositórios JPA
│   │   ├── UserRepository.java
│   │   ├── ScreeningRepository.java
│   │   ├── ScreeningItemRepository.java
│   │   ├── ScreeningResultRepository.java
│   │   └── UserAddressRepository.java
│   └── mappers/             # Conversão Entity <-> Domain
│       ├── UserMapper.java
│       └── ScreeningMapper.java
│
├── dtos/                     # Data Transfer Objects
│   ├── common/              # DTOs genéricos
│   │   └── ApiErrorResponse.java
│   ├── requests/            # DTOs de entrada
│   │   ├── LoginRequest.java
│   │   ├── CreateUserRequest.java
│   │   └── CreateScreeningRequest.java
│   ├── responses/           # DTOs de saída
│   │   ├── UserResponse.java
│   │   └── ScreeningResponse.java
│   ├── security/            # DTOs de segurança
│   │   └── JwtToken.java
│   └── transfer/            # DTOs de transferência
│
├── exceptions/              # Exceções customizadas
│   ├── BusinessException.java
│   ├── ResourceNotFoundException.java
│   ├── UserNotFoundException.java
│   ├── UserAlreadyExistsException.java
│   ├── InvalidEmailException.java
│   └── ScreeningNotFoundException.java
│
└── HackatonApplication.java  # Classe principal da aplicação
```

## 🔐 Segurança

O projeto implementa:

- **Spring Security** para controle de acesso
- **BCrypt** para criptografia de senhas
- **JWT** (pronto para implementação completa)
- **Roles Based Access Control (RBAC)** com perfis:
  - `ADMIN`: Gerenciador do sistema
  - `HEALTH_PROFESSIONAL`: Profissional de saúde
  - `PATIENT`: Paciente

### Usuários Padrão (após migração)

| Login | Senha | Tipo |
|-------|-------|------|
| admin | password | ADMIN |
| joao.silva | password | HEALTH_PROFESSIONAL |
| patient.test | password | PATIENT |

**Nota**: Altere as senhas em produção!

## 🗄️ Banco de Dados

### Tecnologia
- PostgreSQL 15+

### Migrações
As migrações são gerenciadas com **Flyway** e estão em:
```
src/main/resources/db/migration/
```

#### Tabelas Criadas

1. **users** - Usuários do sistema
2. **user_addresses** - Endereços dos usuários
3. **screenings** - Triagens dos pacientes
4. **screening_items** - Itens/questões das triagens
5. **screening_results** - Resultados da análise IA

## 📝 Endpoints Principais

### Autenticação
- `POST /api/auth/login` - Login de usuário
- `POST /api/auth/register` - Registrar novo usuário

### Triagens
- `GET /api/screenings` - Listar todas as triagens
- `GET /api/screenings/{id}` - Obter triagem específica
- `POST /api/screenings` - Criar nova triagem
- `PUT /api/screenings/{id}` - Atualizar triagem
- `DELETE /api/screenings/{id}` - Deletar triagem

### Usuários
- `GET /api/users` - Listar usuários
- `GET /api/users/{id}` - Obter usuário específico
- `DELETE /api/users/{id}` - Deletar usuário

## 🛠️ Tecnologias Utilizadas

- **Java 21**
- **Spring Boot 3.4.4**
- **Spring Security**
- **Spring Data JPA**
- **PostgreSQL 15+**
- **Lombok** - Redução de boilerplate
- **Swagger/OpenAPI** - Documentação da API
- **Flyway** - Versionamento de banco de dados
- **Maven** - Gerenciamento de dependências
- **Docker & Docker Compose** - Containerização
- **JUnit 5** - Testes unitários
- **TestContainers** - Testes de integração

## 🧪 Testes

Execute os testes com:
```bash
./mvnw test
```

## 📊 Cobertura de Código

O projeto utiliza **JaCoCo** para medir cobertura de testes:
```bash
./mvnw verify
```

Relatório em: `target/site/jacoco/index.html`

## 🔄 Próximos Passos

Este projeto é um template inicial. Para implementação completa:

1. **Implementar JWT completo** - Token generation e validation
2. **Integração com IA** - APIs de IA para análise de triagens
3. **Notificações** - Sistema de notificações em tempo real
4. **Logging avançado** - ELK Stack, Datadog ou similares
5. **Caching** - Redis para performance
6. **Tratamento de erros** - Exception handlers customizados
7. **Testes abrangentes** - Unit tests e integration tests

## 📞 Suporte

Para dúvidas sobre a arquitetura ou implementação, consulte:
- [Clean Architecture](https://blog.cleancoder.com/uncle-bob/2012/08/13/the-clean-architecture.html)
- [Spring Boot Documentation](https://spring.io/projects/spring-boot)
- [Spring Security Documentation](https://spring.io/projects/spring-security)

## 📄 Licença

Projeto desenvolvido como parte do programa PosTech de Arquitetura e Desenvolvimento em Java.

