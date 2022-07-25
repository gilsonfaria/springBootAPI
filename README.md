### Trabalho Final - APIs REST

###### Stack utilizada:
- Desenvolvimento da API: Java + Spring Boot
- Banco de dados: H2
-- Banco de dados relacional em memória. Utilizado para facilitar os testes e apresentação. A troca para um BD como Postgres pode ser feita com uma simples alteração das configurações do Spring.
- Cache: Spring Boot
-- A funcionalidade de cache do Spring Boot foi habilitada e está sendo utilizada em todas as rotas de listagem da API desenvolvida;
- Autenticação via JWT: Biblioteca JJWT ( Json Web Token Support for the JVM).
-- Todas as rotas da api (exceto /auth) requerem token de autenticação do tipo Bearer.
- Monitoramento: Spring Actuator
-- Funcionalidade do Spring Boot que, ao ser configurada, disponibiliza as rotas /actuator/* onde podem ser obtidas diversas informações do servidor da API, como logs, cache, health, status do BD etc.
