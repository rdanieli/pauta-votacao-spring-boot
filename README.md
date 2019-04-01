# Projeto pautas para votação.

### Apresentação:
#### Objetivo
Crie uma solução de backend para ser executada em nuvem que proveja as seguintes funcionalidades através de serviços REST:

 - Cadastrar uma nova pauta
 - Abrir uma sessão de votação em uma pauta (a sessão de votação deve ficar aberta por um tempo determinado na chamada de abertura ou 1 minuto por default)
 - Receber votos dos associados em pautas (os votos são apenas 'Sim'/'Não'. Cada associado é identificado por um id único e pode votar apenas uma vez por pauta)
 - Contabilizar os votos e dar o resultado da votação na pauta

### Decisões técnicas
Neste projeto foi considerado a criação de 3 entidades principais do dominio de negócio da solução:

  - Pauta
  - Sessão de Votação
  - Voto

Atualmente a solução preve uma Sessão de Votação por pauta. No entanto o fato de ter uma entidade intermediária Sessão de Votação, pode flexibilizar a solução no longo prazo.

O Resultado da votação pode ser parcial ou definitivo, o que permite ao usuário ao consultar uma pauta e já obter a quantidade de respostas SIM/NAO escolhidas.

### Ferramentas
Java - O java é hoje uma das linguagens mais utilizadas em todo o mundo, isso acontece porque ela não é somente uma linguagem,
mas também uma plataforma de desenvolvimento, no entanto vinha perdendo espaço no contexto da nuvem e criei um projeto inteiro
para mostrar como o conceito de código nativo vem chegando forte no ambiente Cloud.

Gradle - Gerenciamento de dependências e com a possibilidade de criar scripts nativos

Spring Boot - Framework largamente utilizado e com DSL para geração de consultas SQL propria através do Spring Data, o qual facilita a criação de consultas e manipulação de objetos. Além de que, é um framework rubusto, a anos no mercado contanto com várias evoluções, fácil de usar e escalar.

PostgreSQL - Banco de dados orientado forte, rubusto e altamente escalavél, além de manter as garantias de ACID.

Docker - Gerenciamento de containers que agilizam o desenvolvimento e a "movimentação" de um projeto de uma máquina para outra.

### Executando o projeto
O projeto utiliza como banco o PostgreSQL e é preciso ter uma instância em execução para o projeto funcionar, a qual já é fornececida neste teste através de um Docker-compose.

- Subindo a aplicação com docker-compose :  
  ``docker-compose -f src/main/docker/docker-compose.yml up --build -d``    

Após acessar [localhost:8080](http://localhost:8080)

Documentação foi feita utilizando o Swagger: [swagger](http://localhost:8080/swagger-ui.html#/)
