# 🚚 Fleet Management -- Sistema de Controle de Frotas

**Plataforma de gestão de frotas baseada em microsserviços**, construída
com tecnologias modernas como **Java**, **Spring Boot**, **Kafka**,
**Hibernate**, **Docker** e **PostgreSQL**.\
Este projeto nasceu após a conclusão de um curso completo de arquitetura
de microsserviços --- e continua em evolução! 💡

Atualmente implementados:

✅ `driver-service`\
✅ `vehicle-service`\
✅ `trip-service`\
🔜 `evidence-service` *(em planejamento --- registro de evidências de
chegada/estacionamento)*

------------------------------------------------------------------------

## 🌐 Arquitetura e Visão Geral

Cada microsserviço é responsável por um domínio específico:

### 👤 `driver-service`

Gerenciamento de motoristas: - Cadastro\
- Atualização\
- Alteração de status\
- Desativação

### 🚗 `vehicle-service`

Gerenciamento de veículos: - Cadastro\
- Atualização\
- Alteração de status\
- Desativação

### 🧭 `trip-service`

Gerencia o ciclo de vida das viagens: - Início\
- Finalização\
- Cancelamento\
- Publicação de eventos Kafka (`TripStarted`, `TripFinished`,
`TripCanceled`)

### 🔄 Comunicação entre serviços

-   **Síncrona:** via **Spring Cloud OpenFeign**\
-   **Assíncrona:** via **Apache Kafka**

------------------------------------------------------------------------

## 🛠️ Tecnologias Utilizadas

-   ☕ **Java 17+**\
-   🌱 **Spring Boot**\
-   🌐 **Spring Cloud OpenFeign**\
-   📦 **Maven**\
-   💾 **PostgreSQL / MySQL / H2** (configurável por propriedades)\
-   🐘 **Hibernate/JPA**\
-   🐳 **Docker**\
-   🔊 **Apache Kafka**\
-   ✔ **Spring Validator**

------------------------------------------------------------------------

## 📡 Endpoints Principais

### 👤 Driver Service

**Base:** `/fleet/management/api/drivers`

  Método   Caminho                   Descrição
  -------- ------------------------- ---------------------
  POST     `/`                       Criar motorista
  GET      `/`                       Listar motoristas
  GET      `/{id}`                   Buscar por ID
  PUT      `/{id}`                   Atualizar motorista
  PUT      `/status/{id}/{status}`   Alterar status
  DELETE   `/{id}`                   Desativar motorista

------------------------------------------------------------------------

### 🚗 Vehicle Service

**Base:** `/fleet/management/api/vehicles`

  Método   Caminho                   Descrição
  -------- ------------------------- -------------------
  POST     `/`                       Criar veículo
  GET      `/`                       Listar veículos
  GET      `/{id}`                   Buscar por ID
  PUT      `/{id}`                   Atualizar veículo
  PUT      `/status/{id}/{status}`   Alterar status
  DELETE   `/{id}`                   Desativar veículo

------------------------------------------------------------------------

### 🧭 Trip Service

**Base:** `/fleet/management/api/trips`

  Método   Caminho          Descrição
  -------- ---------------- ------------------
  POST     `/`              Iniciar viagem
  POST     `/finish/{id}`   Finalizar viagem
  POST     `/cancel/{id}`   Cancelar viagem
  GET      `/`              Listar viagens
  GET      `/{id}`          Buscar por ID
  PUT      `/{id}`          Atualizar viagem

------------------------------------------------------------------------

## 🧪 Exemplos de Requisições

### Criar motorista

``` bash
curl -X POST http://localhost:8081/fleet/management/api/drivers  -H "Content-Type: application/json"  -d '{"fullName":"João Silva","cpf":"00000000000","cnhCategory":"B","cnhNumber":"123456789","cnhExpiresDate":"2026-12-31"}'
```

### Criar veículo

``` bash
curl -X POST http://localhost:8082/fleet/management/api/vehicles  -H "Content-Type: application/json"  -d '{"plate":"ABC-1234","brand":"Marca","model":"Modelo","year":2020}'
```

### Iniciar viagem

``` bash
curl -X POST http://localhost:8083/fleet/management/api/trips  -H "Content-Type: application/json"  -d '{"driverId":1,"vehicleId":1,"startLocation":"Ponto A","destination":"Ponto B","startDateTime":"2025-12-01T09:00:00"}'
```

------------------------------------------------------------------------

## ▶ Como Executar Localmente

### 📋 Pré-requisitos

-   ✔ **Java 17+**\
-   ✔ **Maven 3.8+**\
-   ✔ **Kafka + Zookeeper** rodando\
-   ✔ **3 bancos PostgreSQL**: `fleetDrivers`, `fleetVehicles`,
    `fleetTrips`\
-   ✔ Configurar `application.properties` ou `application.yml`

------------------------------------------------------------------------

### 🚀 Subindo serviços

``` bash
cd driver-service
mvn spring-boot:run

cd ../vehicle-service
mvn spring-boot:run

cd ../trip-service
mvn spring-boot:run
```

Ou gerar JARs:

``` bash
mvn clean package
java -jar driver-service/target/driver-service-*.jar
java -jar vehicle-service/target/vehicle-service-*.jar
java -jar trip-service/target/trip-service-*.jar
```
