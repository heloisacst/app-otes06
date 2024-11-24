# Sistema de Gerenciamento de Consultas

Este app foi desenvolvido utilizando Spring 3 e MySQL como banco de dados. Ele permite o gerenciamento de consultas médicas por meio de operações de CRUD (Criar, Ler, Atualizar e Deletar).

## Funcionalidades
- Agendamento de Consultas: Permite agendar e listar consultas.
- CRUD de Médicos: Permite cadastrar, listar os médicos, editar e excluir.
- Pacientes: Permite registrar e listar dados dos pacientes.

## Tecnologias Utilizadas
- Back-end: Spring Framework 3
- Front-end: Android Studio
- Banco de Dados: MySQL
- IDEs: IntelliJ, Android Studio

### Dependências
- Spring Boot Starter Data JPA: Para integração com banco de dados usando JPA/Hibernate.
- Spring Boot Starter Web: Para criar e gerenciar a API REST.
- Flyway Core e Flyway MySQL: Para versionamento e migração de banco de dados.
- Spring Boot DevTools: Para facilitar o desenvolvimento com hot-reload.
- MySQL Connector: Driver para conexão com o banco de dados MySQL.
- Lombok: Para reduzir o código boilerplate com anotações como @Getter, @Setter, etc.
- Spring Boot Starter Validation: Para validações no back-end.
- Spring Boot Starter Test: Para criação de testes unitários e de integração.

# Configuração
1. Clone o repositório:

```bash
    git clone https://github.com/heloisacst/app-otes06.git
```
2. Configure o arquivo ``application.properties`` conforme abaixo:
```bash
spring.application.name=scheduling
spring.datasource.url=jdbc:mysql://localhost:3306/vollmed_api?createDatabaseIfNotExist=true
spring.datasource.username=root
spring.datasource.password=root
spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.format_sql=true
```
# End-Points Disponíveis:
1. Criar e listar Agendamento e Pacientes e CRUD de médicos.
2. Sufixo padrão para os end-points: http://localhost:8080

## 1. Agendamento
### Criar um agendamento
- URL: POST | /agendar
```json
{
  "idMedico": 1,
  "idPaciente": 2,
  "dataHoraConsulta": "2024-12-01T15:00:00"
}
```
### Listar todas as consultas/agendamentos
- URL: GET | /agendar

## 2. Pacientes

### Criar um Paciente:
- URL: POST | /pacientes
- Exemplo requisção:
```json
{
"pacte_nome": "João da Silva",
"pacte_email": "joao.silva@gmail.com",
"pacte_telefone": "47999999999",
"endereco": {
    "logradouro": "Rua das Palmeiras",
    "bairro": "Flores",
    "cep": "12345678",
    "cidade": "Brasilia",
    "uf": "DF",
    "numero": "589",
    "complemento": "Casa 1"
    }
}
```
