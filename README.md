# Magazine Luiza

# Desafio - Agendamento de Envio de Comunicação

## Requisitos do Projeto

Este projeto consiste no desenvolvimento de uma API para agendamento de envio de comunicações, seguindo as especificações abaixo:

### 1. Endpoint de Agendamento de Envio
- Deve receber uma solicitação de agendamento de envio de comunicação.
- Campos obrigatórios:
  - **Data/Hora para o envio**
  - **Destinatário**
  - **Mensagem a ser entregue**
- Tipos de comunicação suportados: **Email, SMS, Push e WhatsApp**.
- A solicitação deve ser **salva no banco de dados**.
- Observação: o envio em si **não será implementado nesta etapa**, mas a estrutura deve permitir que a funcionalidade de envio futura altere o status do registro no banco.

### 2. Endpoint de Consulta de Status
- Permite consultar o status de um agendamento feito no endpoint de agendamento.

### 3. Endpoint de Remoção de Agendamento
- Permite remover um agendamento existente do banco de dados.

---

## Tecnologias Utilizadas

- **Linguagem de Programação:** Java  
- **Banco de Dados:** MySQL  

**Justificativa da Escolha:**  
Escolhi Java e MySQL por ter maior conhecimento e experiência com essas ferramentas. Java permite construir APIs RESTful robustas e com boas práticas, enquanto MySQL oferece confiabilidade e facilidade de integração com aplicações Java.

---

## Estrutura do Banco de Dados

- A tabela principal **tb__Mensagem** foi criada automaticamente via **JPA/Hibernate**, usando a entidade `Mensagem`.
- O modelo de dados contempla:
  - `id` como chave primária auto-increment
  - `destinatario` (não nulo)
  - `tipoMensagem` (enum, não nulo)
  - `dataHoraEnvio` (não nulo)
- A criação automática garante que futuras funcionalidades de envio possam alterar o status do agendamento sem necessidade de alteração na tabela.

---

## Rotas da API

A API possui os seguintes endpoints:

| Método | Rota | Descrição | Request Body / Path Variable | Resposta |
|--------|------|-----------|-----------------------------|----------|
| POST   | `/mensagem/add` | Agenda uma nova mensagem | JSON do `MensagemRequestDTO` | Objeto `Mensagem` salvo |
| GET    | `/mensagem/show/{id}` | Consulta o status de uma mensagem agendada | `id` da mensagem | Objeto `Mensagem` correspondente |
| DELETE | `/mensagem/delete/{id}` | Remove um agendamento de mensagem | `id` da mensagem | `true` se deletado com sucesso |

Inicialização e Testes:
1. Configuração do Banco

Configure o MySQL e crie um banco ex: desafio_magalau

Atualize o arquivo application.properties com usuário, senha e nome do banco:

spring.datasource.url=jdbc:mysql://localhost:3306/desafio_magalau

spring.datasource.username=seuRoot

spring.datasource.password=suaSenha

spring.jpa.hibernate.ddl-auto=update

2. Git Clone:
   
git clone https://github.com/devSalles/Desafio_Magalau.git

cd Desafio_Magalau

./mvnw spring-boot:run


**Exemplo de JSON para POST `/mensagem/add`:**

```json
{
  "destinatario": "teste@exemplo.com",
  "tipoMensagem": "email",
  "dataHoraEnvio": "2025-09-19T15:00:00.000Z"
}
