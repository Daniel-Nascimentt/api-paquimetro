# API DE PAQUIMETROS - Tech challenge Arquitetura e desenvolvimento Java - Fase3

# ÍNDICE

* [Tecnologias](#tecnologias)
* [Start](#start)
* [Decisoes](#decisoes)
* [Desenho da nova arquitetura](#desenhoNovaArquitetura)
* [Exemplo Collection Mongo](#exemploCollectionMongo)
* [Sobre API-paquimetro](#sobreApiPaquimetro)
* [Sobre ms-email](#sobrems-email)
* [Sobre ms-alerta](#sobrems-alerta)
* [Sobre ms-recibo](#sobrems-recibo)
* [Swagger api-paquimetro](#swaggerApi-paquimetro)
* [Swagger ms-email](#swaggerms-email)
* [Swagger ms-recibo](#swaggerms-recibo)
* [Swagger ms-alerta](#swaggerms-alerta)
* [Postman Collection](#postmanCollection)

# 
# Tecnologias 👨🏻‍💻 

* Arquitetura: Micro services
* Dependencias
    * Java 17 (Padrão Spring Initializr)
    * Spring boot 3.1.5 (Padrão Spring Initializr)
    * DevTools (Facilitar setup no ambiente de desenvolvimento dando Restart no servidor a cada modificação feita)
    * Lombok (Facilitar criação de métodos acessores e construtores quando necessário)
    * Spring Web (Para usar uma API REST)
    * Open API (Habilitar Swagger)
    * Spring Data Mongo db
    * Bean Validation (Para fazer validações de campos na borda mais externa da API, as REQUESTS)
    * Open Feign (Comunicação entre micro serviços)
    * RabbitMq (Uso de menssageria)
    * Java Mail sender (Envio de e-mails)
* GIT (Controle de versão do projeto)
* IDE's (Intellij, VS Code)
* Postman (Testes da API)
* Mongo Compass (Client MongoDb)
* Docker (Para subir um container com rabbit MQ) - docker run -it  --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3-management

#

# Start 👨🏻‍🔧

Nesse documento não possui o swagger, endpoints e payloads de request e response. Mais abaixo você encontrara um print com os principais endpoints mais para ilustração, caso queria ver no detalhe, basta fazer o download do projeto e executar seus respectivos swaggers.

Para executar local você precisara ter o docker para que consiga executar o RabbitMq e crirar suas respectivas filas, e o mongodb, no caso utilizei ele local e não pelo docker, mas fica a seu criterio a forma de start do mongo db.

Caso faça o teste dos micro-services local, recomendo que crie uma conta gmail de teste e siga a documentação do Google para gerar uma chave para que seja utilizado no mail sender.

Neste caso, criei uma conta chamada: *ms.email.fiap@gmail.com*  e segui a documentação do link: https://support.google.com/accounts/answer/185833

Ao gerar a senha de 16 dígitos, incluí ela na propriedade: 
> spring.mail.password=${SPRING.MAIL.PASS}

Por motivos de segurança, optei por deixar essa key como uma váriavel de ambiente.

As filas criadas no RabbitMq são:
* cancelar-alerta 
    * Cada vez que o usuário iniciar o estacionamento, é programado um horario para notifica-lo quando estiver prestes a completar mais uma hora de seu período, e quando o cliente faz o pagamento, é necessário dar baixa no alerta que está ativo na collection do mongo. Para isso o micro service de alerta possui um job que varre a base a cada 2 minutos verificando se existe algum alerta dentro de um range, se houver, ele notificara o usuário respectivo.
* criar-alerta
    * Vai criar o alerta na collection do mongo de forma assincrona, sem prender a requisição para isso.
* email-recibo-enviado
    * Contém uma lógica dentro da collection de recibo, com um boolean que informa se o email foi enviado ou não.
* emissao-recibo
    * Contém a lógica para gerar o recibo e fazer o envio por e-mail para o usuário.

# 
# Decisoes ✍🏻 

*Arquitetura de micro-service:*
Como é um sistema que precisa ser escalável, separar algumas responsabilidades, utilizar menssageria para processamento assincrono nos permite ser mais performatico em certos casos. Como esse sistema dificilmente haverá concorrencia, justamente porque usuários diferentes não acessarão os mesmos recursos, foi criado 4 APIs spring boot para compor a nova arquitetura da empresa de paquimetros. No caso de uma implementação em cloud, poderiamos criar load balancers e distribuir as cargas entre nossos micro serviços, mas como não é o foco de implantação em nuvem, seguimos.

*Uso do Spring mail sender:* Foi uma solução para enviar e-mails personalizados para cada usuário de forma simples. Poderia ser implementado usando o SES da AWS, porém pela praticidade o mail sender, optei pelo uso do mesmo para fazer o envio de alertas e optei pelo envio de recibo também por e-mail.  

*Tratamentos de erros:* Os erros são tratados via exceptions handler e algumas propriedades adiconadas a todos micro serviços que não deixa a aplicação cuspir o trace de erro.

```application.properties
server.error.include-binding-errors= never
server.error.include-message= always
server.error.include-stacktrace= never
```

```java
@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlerMethodArgumentNotValidException(MethodArgumentNotValidException ex){

        List<String> fieldsError = new ArrayList<>();

        ex.getFieldErrors().forEach(f -> fieldsError.add("PARAMETRO: [" + f.getField() + "] Mensagem: [" + f.getDefaultMessage() + "]"));

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
                "Por favor, verifique se todos os campos foram preenchidos corretamente!",
                HttpStatus.BAD_REQUEST.value(),
                fieldsError,
                new Date().getTime()));
    }

    @ExceptionHandler(DocNotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ResponseEntity<?> handlerDocNotFoundException(DocNotFoundException ex){

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorResponseDetails(
                "Documento não encontrado!!",
                HttpStatus.NOT_FOUND.value(),
                Arrays.asList(ex.getMessage()),
                new Date().getTime()));
    }

    @ExceptionHandler(PagamentoInvalidoException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ResponseEntity<?> handlerPagamentoInvalidoException(PagamentoInvalidoException ex){

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorResponseDetails(
                "Forma de pagamento inválida!",
                HttpStatus.BAD_REQUEST.value(),
                Arrays.asList(ex.getMessage()),
                new Date().getTime()));
    }
```

## Desenho da nova arquitetura: 
![Untitled (1)](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/4de155ea-77e9-492e-9844-93d5e9b92051)

#

# Exemplo Collection Mongo 
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/a96d6cdb-d9b4-4dd2-ab2d-6d33a8f90eed)

> OBS: CPF gerado no site 4Devs

![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/03314626-90ef-4599-b774-d2ea0aba150d)

![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/0d26d8cb-5d94-4d1b-b742-827bebed74e8)

> OBS: O tempo de estacionamento está zerado porque é um exemplo

![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/d7b691a9-1c49-4ffc-8ea2-74a2047ba6d1)

![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/74dbd7c8-d2e6-4405-bbb6-943747b4b021)

#
# Sobre API-paquimetro 

A API-paquimetro é responsavel por iniciar e finalizar/pagar o estacionamento, enviar mensagens para demais micro serviços (via RabbitMq), bem como o *ms-alerta* para programar o alerta com base no inicio do estacionamento, o *ms-recibo* para gerar o recibo e enviar ao cliente. Também executar alguns relatórios úteis. Sempre que um paquimetro é iniciado, ele solicita a placa do veículo, a partir da placa do veículo é possivel identificar o condutor, facilitando a usabilidade do sistema para o condutor.

#

# Sobre ms-email 

Como o próprio nome ja diz, sua unica responsabilidade é para o envio de e-mails, esse micro serviço possui um único endpoint com os atributos necessários para o envio de e-mail, e ele pode ser acionado pelos micro services *ms-alerta* e *ms-recibo*.

#

# Sobre ms-alerta 

Como o próprio nome ja diz, sua unica responsabilidade é para gerenciar alertas para os usuários com relação ao periodo estacionado. O ms-alerta tem a inteligencia para criar e cancelar os alertas baseado em notificações/mensagens recebidas via RabbitMq. E possui um Schedule que executa a cada 2 minutos para verificar os próximos alertas a serem enviados, acionando via OpenFeign/REST o *ms-email* para notificação do usuário.


## EXEMPLO do e-mail de alerta:
#### OBS: Repare no e-mail remetente
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/43c10c63-04c1-403c-b22c-098e0210fe1e)

#

# Sobre ms-recibo 

Como o próprio nome ja diz, sua unica responsabilidade é para o gerenciamento dos recibos após o pagamento. Com isso é construido um recibo com base nos dados do estacionamento, como período, valor, tipo do estacionamento, etc. E faz o envio do recibo via e-mail pelo *ms-email*.

## EXEMPLO do e-mail de recibo:
#### OBS: Repare no e-mail remetente
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/efc720d1-1991-4c85-b83c-c60da7d621ad)
#

# Swagger api-paquimetro 

> Swagger url: http://localhost:8080/api-paquimetro/swagger-ui/index.html#/
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/0b6f658b-e9ed-4aed-a2f4-dacca6af84b7)
#

# Swagger ms-email 
> Swagger url: http://localhost:8081/ms-email/swagger-ui/index.html#/
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/50ee6702-1076-496e-a3f9-cf3d3b339840)
#

# Swagger ms-recibo 
> Swagger url: http://localhost:8082/swagger-ui/index.html#/
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/d92398f2-a064-4adf-998e-517174e39e15)

#

# Swagger ms-alerta 

> Swagger url: http://localhost:8083/swagger-ui/index.html#/
![image](https://github.com/Daniel-Nascimentt/api-paquimetro/assets/65513073/5960fdc2-255e-43bb-b2e2-e480dd77f782)

#

# Postman Collection 
#### OBS: Nos endpoints de iniciar, finalizar e pagar, atente-se que na URL é solicitado o id do paquimetro. Ao iniciar o paquimetro, ele é retornado no endpoint.

Basta clicar no link: *api-paquimetro.postman_collection.json* que o download vai começar, feito isso basta importar a collection no postman.


[api-paquimetro.postman_collection.json](https://github.com/Daniel-Nascimentt/api-paquimetro/files/13196821/api-paquimetro.postman_collection.json)
