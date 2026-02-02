# 🎯 Projeto PosTech Hackaton - Setup Completo

## 📋 Resumo da Estrutura Criada

### ✅ Arquivos de Configuração
- ✅ `pom.xml` - Configuração Maven com todas as dependências
- ✅ `Dockerfile` - Containerização da aplicação
- ✅ `docker-compose.yaml` - Orquestração com PostgreSQL
- ✅ `application.yml` - Configurações da aplicação
- ✅ `.gitignore` - Arquivos ignorados pelo Git

### ✅ Estrutura Java (Clean Architecture)

#### **Domain Layer** (Regras de Negócio)
```
domain/
  ├── User.java
  ├── UserAddress.java
  ├── Screening.java
  ├── ScreeningItem.java
  ├── ScreeningResult.java
  └── enums/
      ├── UserType.java
      └── ScreeningPriority.java
```

#### **Application Layer** (Casos de Uso)
```
application/
  ├── gateways/
  │   ├── UserGateway.java
  │   └── ScreeningGateway.java
  └── usecases/
      ├── CreateUserUseCase.java
      ├── CreateScreeningUseCase.java
      └── GetScreeningUseCase.java
```

#### **Interface Adapter Layer** (Adaptadores)
```
interface_adapter/
  ├── gateways/
  │   ├── UserGatewayImpl.java
  │   └── ScreeningGatewayImpl.java
  ├── controllers/ (para expansão futura)
  └── presenters/ (para expansão futura)
```

#### **Infrastructure Layer** (Implementação Técnica)
```
infrastructure/
  ├── config/
  │   ├── SecurityConfig.java (Spring Security + BCrypt)
  │   └── OpenApiConfig.java (Swagger/OpenAPI)
  ├── controllers/
  │   ├── AuthController.java
  │   ├── ScreeningController.java
  │   └── UserController.java
  ├── entities/ (JPA Entities)
  │   ├── UserEntity.java
  │   ├── UserAddressEntity.java
  │   ├── ScreeningEntity.java
  │   ├── ScreeningItemEntity.java
  │   └── ScreeningResultEntity.java
  ├── data_sources/ (Repositórios JPA)
  │   ├── UserRepository.java
  │   ├── UserAddressRepository.java
  │   ├── ScreeningRepository.java
  │   ├── ScreeningItemRepository.java
  │   └── ScreeningResultRepository.java
  └── mappers/ (Conversores)
      ├── UserMapper.java
      └── ScreeningMapper.java
```

#### **DTOs & Exceptions**
```
dtos/
  ├── common/
  │   └── ApiErrorResponse.java
  ├── requests/
  │   ├── LoginRequest.java
  │   ├── CreateUserRequest.java
  │   └── CreateScreeningRequest.java
  ├── responses/
  │   ├── UserResponse.java
  │   └── ScreeningResponse.java
  └── security/
      └── JwtToken.java

exceptions/
  ├── BusinessException.java
  ├── ResourceNotFoundException.java
  ├── UserNotFoundException.java
  ├── UserAlreadyExistsException.java
  ├── InvalidEmailException.java
  └── ScreeningNotFoundException.java
```

### ✅ Banco de Dados (Flyway Migrations)

```
db/migration/
  ├── V0001__create_table_users.sql
  ├── V0002__create_table_user_addresses.sql
  ├── V0003__create_table_screenings.sql
  ├── V0004__create_table_screening_items.sql
  ├── V0005__create_table_screening_results.sql
  └── V0006__insert_default_users.sql
```

**Tabelas Criadas:**
1. `users` - Usuários do sistema (ADMIN, HEALTH_PROFESSIONAL, PATIENT)
2. `user_addresses` - Endereços dos usuários
3. `screenings` - Triagens de pacientes
4. `screening_items` - Questões/items das triagens
5. `screening_results` - Resultados da análise IA

### ✅ Testes
- ✅ `HackatonApplicationTests.java` - Teste básico de context
- ✅ Estrutura pronta para testes de unidade e integração

## 🚀 Como Usar

### Opção 1: Docker Compose (Recomendado)
```bash
cd /Users/brunobender/git/postech-hackaton
docker-compose up
```

Aguarde até ver:
```
app-1        | Started HackatonApplication in X.XXX seconds
```

A API estará em: **http://localhost:2005**
Swagger em: **http://localhost:2005/swagger-ui.html**

### Opção 2: Execução Local
```bash
cd /Users/brunobender/git/postech-hackaton

# Build
./mvnw clean install

# Run
./mvnw spring-boot:run
```

### Opção 3: Apenas Banco de Dados (para desenvolvimento)
```bash
# Inicie apenas o PostgreSQL
docker-compose up postgres

# Depois execute a aplicação localmente
./mvnw spring-boot:run
```

## 📊 Endpoints Disponíveis

### Autenticação
- `POST /api/auth/login` - Login (mock)
- `POST /api/auth/register` - Registrar (mock)

### Triagens
- `GET /api/screenings` - Listar triagens
- `GET /api/screenings/{id}` - Obter triagem
- `POST /api/screenings` - Criar triagem
- `PUT /api/screenings/{id}` - Atualizar triagem
- `DELETE /api/screenings/{id}` - Deletar triagem

### Usuários
- `GET /api/users` - Listar usuários
- `GET /api/users/{id}` - Obter usuário
- `DELETE /api/users/{id}` - Deletar usuário

## 🔐 Segurança

- ✅ **Spring Security** configurado
- ✅ **BCrypt** para criptografia de senhas
- ✅ **JWT** (DTOs prontos para implementação completa)
- ✅ **RBAC** com perfis (ADMIN, HEALTH_PROFESSIONAL, PATIENT)
- ✅ **CSRF** desabilitado (API REST)
- ✅ **CORS** pronto para configuração

## 🛠️ Stack Tecnológico

| Componente | Versão |
|-----------|--------|
| Java | 21 |
| Spring Boot | 3.4.4 |
| Spring Security | 6.x |
| Spring Data JPA | 3.4.4 |
| PostgreSQL | 15+ |
| Flyway | Atual (via Spring Boot) |
| Lombok | 1.18.36 |
| Swagger/OpenAPI | 2.8.6 |
| Maven | 3.9.9 |
| Docker | Latest |

## 📝 Próximos Passos Recomendados

### 1. **Implementar JWT Completo**
   - Gerar tokens no login
   - Validar tokens nas requisições
   - Refresh tokens

### 2. **Integração com IA**
   - API para análise de triagens
   - Score de confiança
   - Recomendações automáticas

### 3. **Ampliar Testes**
   - Testes unitários dos Use Cases
   - Testes de integração dos Controllers
   - Cobertura mínima de 80%

### 4. **Exception Handlers Globais**
   - `@ControllerAdvice` com handlers customizados
   - Tratamento de erros padronizado

### 5. **Validação de Entrada**
   - `@Valid` nos DTOs
   - Bean Validation (jakarta.validation)

### 6. **Logging e Monitoring**
   - SLF4J + Logback
   - Métricas com Actuator/Prometheus
   - Distributed Tracing

### 7. **Documentação**
   - Adicionar descrições no Swagger
   - Exemplos de requests/responses
   - Guias de uso

### 8. **CI/CD**
   - GitHub Actions
   - SonarQube para análise de código
   - Testes automatizados

## 💾 Inicializar Git Repository

```bash
cd /Users/brunobender/git/postech-hackaton

# Inicializar Git
git init

# Adicionar remote (substitua pela URL do seu repositório)
git remote add origin https://github.com/seu-usuario/postech-hackaton.git

# Adicionar todos os arquivos
git add .

# Commit inicial
git commit -m "Initial commit: Clean Architecture setup for Smart Screening with AI"

# Push para o repositório
git branch -M main
git push -u origin main
```

## 📞 Suporte e Documentação

Consulte os seguintes arquivos para mais informações:
- **README.md** - Visão geral do projeto
- **GUIA_ARQUITETURA.md** - Detalhes da arquitetura
- **pom.xml** - Dependências e configurações Maven

## ✨ Características Implementadas

✅ Clean Architecture com 4 camadas  
✅ Spring Boot 3.4.4  
✅ Spring Security com BCrypt  
✅ Spring Data JPA  
✅ PostgreSQL com migrations (Flyway)  
✅ Swagger/OpenAPI 3.0  
✅ DTOs para todas as operações  
✅ Exceções customizadas  
✅ Mappers para conversão de dados  
✅ Gateways para abstração  
✅ Use Cases para lógica de negócio  
✅ Docker & Docker Compose  
✅ Lombok para redução de boilerplate  
✅ Testes básicos configurados  
✅ Documentação completa  

---

**Versão**: 0.0.1-SNAPSHOT  
**Data**: Fevereiro 2025  
**Status**: 🟢 Pronto para desenvolvimento

