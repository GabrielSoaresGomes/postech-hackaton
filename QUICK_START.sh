#!/bin/bash
# Script de Quick Start - postech-hackaton

echo "🎯 POSTECH HACKATON - Quick Start"
echo "=================================="
echo ""

# Cores para output
GREEN='\033[0;32m'
BLUE='\033[0;34m'
YELLOW='\033[1;33m'
NC='\033[0m' # No Color

PROJECT_PATH="/Users/brunobender/git/postech-hackaton"

echo -e "${BLUE}📍 Caminho do projeto:${NC}"
echo "   $PROJECT_PATH"
echo ""

echo -e "${BLUE}1️⃣  INICIALIZAR GIT REPOSITORY${NC}"
echo "   cd $PROJECT_PATH"
echo "   git init"
echo "   git remote add origin https://github.com/SEU-USUARIO/postech-hackaton.git"
echo "   git add ."
echo "   git commit -m 'Initial commit: Clean Architecture for Smart Screening'"
echo "   git push -u origin main"
echo ""

echo -e "${BLUE}2️⃣  COMPILAR O PROJETO${NC}"
echo "   cd $PROJECT_PATH"
echo "   ./mvnw clean compile"
echo ""

echo -e "${BLUE}3️⃣  EXECUTAR COM DOCKER COMPOSE${NC}"
echo "   cd $PROJECT_PATH"
echo "   docker-compose up"
echo "   # Aguarde até: 'Started HackatonApplication'"
echo ""

echo -e "${BLUE}4️⃣  EXECUTAR LOCALMENTE (SEM DOCKER)${NC}"
echo "   cd $PROJECT_PATH"
echo "   docker-compose up postgres  # Apenas o DB"
echo "   ./mvnw spring-boot:run"
echo ""

echo -e "${BLUE}5️⃣  EXECUTAR TESTES${NC}"
echo "   cd $PROJECT_PATH"
echo "   ./mvnw test"
echo ""

echo -e "${BLUE}6️⃣  BUILD JAR PARA PRODUÇÃO${NC}"
echo "   cd $PROJECT_PATH"
echo "   ./mvnw clean package -DskipTests"
echo "   java -jar target/hackaton-0.0.1-SNAPSHOT.jar"
echo ""

echo -e "${BLUE}ENDPOINTS PRINCIPAIS${NC}"
echo "   API Base:              http://localhost:2005"
echo "   Swagger UI:            http://localhost:2005/swagger-ui.html"
echo "   Healthcheck:           http://localhost:2005/actuator/health"
echo ""

echo -e "${BLUE}AUTENTICAÇÃO PADRÃO${NC}"
echo "   Admin:                 login: admin / password: password"
echo "   Health Professional:   login: joao.silva / password: password"
echo "   Patient:               login: patient.test / password: password"
echo ""

echo -e "${YELLOW}⚠️  IMPORTANTE:${NC}"
echo "   - Altere as senhas padrão em produção!"
echo "   - Configure JWT antes de usar em produção"
echo "   - Verifique as variáveis de ambiente"
echo ""

echo -e "${GREEN}✅ Pronto para começar!${NC}"
echo ""

