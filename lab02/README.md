## Lab 2 : Java at the server-side and the role of application containers

### 2.1 Server-side programming with servlets

​	O Java Servlet é a especificação da web base no ambiente de Java Enterprise. Um Servlet é uma classe Java que é executada no servidor, trata de pedidos (do cliente), processa-os e responde. Um Servlet deve ser deployed num Servlet Container (multithreaded) para poder ser usado. Os Containes processam vários pedidos concorrentemente. Um Servlet é um interface genérico e o HttpServlet é uma extensão desse interface (tipo mais usado de Servlets). 

​	Quando uma aplicação está a correr num servidor web recebe um pedido, o servidor passa o pedido para o Servlet Container que por sua vez passa-o para o Servlet alvo. O Servlet Container é uma parte do conjunto normal de serviços que podemos encontrar num Java Aplication Server.

a) 

```bash
$ sudo ./catalina.sh 
(...)
Using CATALINA_OPTS:   
Usage: catalina.sh ( commands ... )
commands:
  debug             Start Catalina in a debugger
  debug -security   Debug Catalina with a security manager
  jpda start        Start Catalina under JPDA debugger
  run               Start Catalina in the current window
  run -security     Start in the current window with security manager
  start             Start Catalina in a separate window
  start -security   Start in a separate window with security manager
  stop              Stop Catalina, waiting up to 5 seconds for the process to end
  stop n            Stop Catalina, waiting up to n seconds for the process to end
  stop -force       Stop Catalina, wait up to 5 seconds and then use kill -KILL if still running
  stop n -force     Stop Catalina, wait up to n seconds and then use kill -KILL if still running
  configtest        Run a basic syntax check on server.xml - check exit code for result

```

b)

```java
//Source Code for Request Parameter Example
import java.io.*;
import java.util.*;
import javax.servlet.*;
import javax.servlet.http.*;

public class RequestParamExample extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
    throws IOException, ServletException
    {
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        out.println("<html>");
        out.println("<head>");
        out.println("<title>Request Parameters Example</title>");
        out.println("</head>");
        out.println("<body>");
        out.println("<h3>Request Parameters Example</h3>");
        out.println("Parameters in this request:<br>");

        String firstName = request.getParameter("firstname");
        String lastName = request.getParameter("lastname");

        if (firstName != null || lastName != null) {
            out.println("First Name:");
            out.println(" = " + HTMLFilter.filter(firstName) + "<br>");
            out.println("Last Name:");
            out.println(" = " + HTMLFilter.filter(lastName));
        } else {
            out.println("No Parameters, Please enter some");
        }
        out.println("<P>");
        out.print("<form action=\"");
        out.print("RequestParamExample\" ");
        out.println("method=POST>");
        out.println("First Name:");
        out.println("<input type=text size=20 name=firstname>");
        out.println("<br>");
        out.println("Last Name:");
        out.println("<input type=text size=20 name=lastname>");
        out.println("<br>");
        out.println("<input type=submit>");
        out.println("</form>");
        out.println("</body>");
        out.println("</html>");
    }

    public void doPost(HttpServletRequest request, HttpServletResponse res)
    throws IOException, ServletException
    {
        doGet(request, response);
    }
}
```

​	Este exemplo de Request Parameters extende a classe abstrata HttpServlet que permite criar um servlet HTTP apropriado para um Web site. Uma subclasse do HttpServlet deve fazer override de pelo menos um dos seus métodos:

- doGet() : para pedidos HTTP GET
- doPost() : para pedidos HTTP POST
- doPut() : para pedidos HTTP PUT
- doDelete() : para pedidos HTTP DELETE
- init() e destroy() : para gerir os recursos que são usados na vida do servlet
- getServletInfo() : para o servlet fornecer informação sobre si próprio

c)  Projeto maven

```bash
$ mvn archetype:generate -DgroupId=org.<>.mojo.archetypes -DartifactId=webapp-javaee7 -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.1 -DinteractiveMode=false
```

h) Adicionei o Servlet (UsernameServlet.java) e uma classe integrante (HTMLFilter.java) dentro da pasta main/java. Após fazer deploy de webapp-javaee7-1.0-SNAPSHOT.war presente na pasta target, o path para testar a app é http://localhost:8080/webapp-javaee7-1.0-SNAPSHOT/UsernameServlet 

---

### 2.2 Server-side programming with embedded servers

a) 

```bash
$ mvn archetype:generate -DgroupId=com.javacodegeeks.example -DartifactId=EmbeddedJettyExample -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false

---
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="com.javacodegeeks.example.EmbeddedJettyExample" 
```

Depois abrir em http://localhost:8680/

---

### 2.3 Introduction to web apps with a full-featured framework (Spring Boot)

b)

[Serving Web Content](https://spring.io/guides/gs/serving-web-content/)

Quando usamos Spring para construir web sites, os HTTP requests são tratados por um controller (@Controller).  No caso deste exemplo, o GreetingController trata dos GET requests retornando o nome de uma View. 

Uma View é responsável por renderizar o conteúdo HTML.

O @SpringBootApplication (neste caso, na DemoAplication) adiciona

- @Configuration : faz o tag da classe como fonte das bean definitions para o contexto da aplicação.
- @EnableAutoCofiguration : diz ao Spring Boot para começar a adicionar beans, baseado nas definições de classpath, noutros beans e vários property settings.
- @ComponentScan : diz ao Spring para procurar por outros componentes, configurações e serviços na package com/example, deixando-o encontrar os controllers.

O método main() usa o método SpringApplication.run() do Spirng Boot para lançar a aplicação.

Para correr:

```bash
$ ./mvnw spring-boot:run
```

Ir ao http://localhost:9000/, para ver variações na mensagem, escrever http://localhost:9000/greeting?name=Sophie, por exemplo.

c)

[Rest Service](https://spring.io/guides/gs/rest-service/)

Para correr (exemplo):

```
$ ./mvnw spring-boot:run
$ curl -v http://localhost:8080/greeting
ou
$ curl -v http://localhost:8080/greeting?name=Sophie
```

---

### 2.4 Wrapping-up & integrating concepts

```bash
./mvnw spring-boot:run
```

No browser:

 Random Quote - http://localhost:8080/api/quote 

All Shows - http://localhost:8080/api/shows

Random Quote from a show (by id) - http://localhost:8080/api/quotes?show=<id> (o id é opcional, se nada for dito, é assumido show= 0)

---

### Review questions

A. What are the responsibilities/services of a “servlet container”?

Um Servlet Container pode carregar, inicializar e executar servlets. Consegue tratar de um grande número de pedidos já que pode ter vários servlets ativos em simultâneo.  Os servlets precisam de estar registados primeiro para o container poder recolhê-los na inicialização. O container instancia um servlet ao chamar o seu método init(). Assim que a inicialização está concluída, o servlet está pronto para aceitar pedidos.  Quando recebe um pedido, o container gere esse pedido para este ser processado no método service() do servlet. Depois disso, delega o pedido ao método apropriado, Get() ou Post(), dependendo do tipo de pedido HTTP.

Existe duas categorias para os servlet containers:

- Um servlet container simples que não é 100% funcional e, por isso, apenas corre servlets simples. Espera pelo HTTP request, constrói os objetos ServletRequest e ServletResponse. Se o pedido for um recurso estático, invoca o método process() da instância StatisResourceProcessor, passando o o ServletRequest e ServletResponse. Se o pedido for para um servlet, carrega a classe do servlet e invoca o seu método service(), passando o ServletRequest e o ServletResponse. É de notar que neste servlet container, a classe do servlet é carregada sempre que o servlet é invocado.
- Um servlet container completamente funcional. Quando o servlet é chamado pela primeira vez, carrega a classe do servlet e chama o seu método init() (apenas uma vez). Para cada pedido, constrói uma instância de javax.servlet.ServletRequest e uma instância de javax.servlet.ServletResponse. Invoca o método service() do servlet, passando os objetos ServletRequest e ServletResponse. Quando a classe do servlet estiver shut down, invoca o método destroy() do servlet e descarrega a classe do servlet.

B. Explain, in brief, the “dynamics” of Model-View-Controller approach used in Spring Boot to serve web content. (You may exemplify with the context of the previous exercises.)

​	Enquanto que o Spring MVC é uma framework essencial que existe dentro do Spring, o Spring Boot é um módulo opcional que é usado para facilitar o processo de desenvolvimento, que pode incluir integrar o Spring MVC durante o build process. Sem o Spring Boot, o Spring MVC leva mais tempo para ser usado e requer configurações manuais e deployments descriptors separados (o Spring Boot faz auto-configuração).

​	O padrão MVC permite separar diferentes partes da aplicação.

- Model, que lida com os dados da aplicação
- View, que é responsável por renderizar os dados do Model e gera, regra geral, um output em HTML que o browser do cliente consegue interpretar
- Controller, que é responsável por processar os pedidos do user e por construir um Model apropriado e passa-lo à View para ser renderizada

​	A Spring Web MVC framework está desenhada à volta de um DispatcherServlet que trata de todos os pedidos e respostas HTTP. 

​	Depois de receber um pedido HTTP, o DispatcherServlet consulta o HandlerMapping para chamar o controller apropriado. Este por sua vez pega nos pedidos e chama o método service apropriado a cada um (GET ou POST). Esse método irá definir os dados do model e retorna o nome da view ao DispatcherServlet. O DispatcherServlet irá ter ajuda do ViewResolver para ir buscar a view definida para o pedido atual. Quando a view está finalizada, o DispatcherServlet passa os dados do model para a view que é finalmente renderizada no browser.

C. Inspect the POM.xml for the previous SpringBoot projects. What is the role of the “starters” dependencies?

​	As starter dependencies são um conjunto de dependecy descriptors comvenientes para incluir numa aplicação. Podem ajudar a reduzir o número de dependências que têm de ser adicionadas manualmente para apenas uma. As suas vantagens são aumentar a produtividade dos developers diminuindo o tempo de configuração, a gestão do ficheiro POM é muito mais simples pois o número de dependências é reduzido, são configurações de dependência testadas, prontas para produção e com suporte e deixa de ser necessário lembrar o nome e a versão das dependências.

D. Which annotations are transitively included in the @SpringBootApplication?

​	Usar a anotação @SpringBootApplication é equivalente a usar @Configuration, @EnableAutoConfiguration e @ComponentScan com os seus atributos default. A anotação @SpringBootApplication permite que as aplicações tenham auto-configuração, component scan e que consigam definir configurações extra na sua classe de aplicação. Estas 3 features estão presentes nas anotações menconadas previamente. A @EnableAutoConfiguration permite o mecanismo de auto-configuração do Spring Boot, a @ComponentScan permite que o scan da @Component na package onde se localiza a aplicação e a @Configuration permite registar, no contexto, beans extra ou importar classes de configuração adicionais.

E. Search online for the topic “Best practices for REST API design”. From what you could learn, select your “top 5”practices,and briefly explain them in you own words.

 - Aceitar e responder com JSON

   O JSON é um formato standard para transferência de dados. É um formato muito fácil de usar e compreender pelos humanos. É compatível com muitos browsers. O facto dos dados enviados não serem tão facilmente manipuláveis no lado do cliente, como os browsers, faz com que enviar dados na sua forma normal seja bastante complexo e o formato JSON vem facilitar todo esse processo.

 - Usar nomes em vez de verbos nos paths de endpoint

   Devem ser usados nomes que representem corretamente aquilo que estamos a manipular no endpoint, nomes claros e concisos. Os verbos são mais úteis para nomear os métodos de pedidos HTTP.

 - Tratar dos bem erros e retornar códigos de erro standard

   Quando ocorrem erros, estes devem ser mostrados aos utilizadores da API da forma mais "simpática" possível para que possam compreender o que correu mal. Devem ser mostrados os códigos de erro de forma a ser mais informativo para os gestores da API , exemplos: 400 Bad Request, 401 Unauthorized, etc.

 - Usar nesting lógico nos endpoints 

   ​	Os endpoints devem ser agrupados se contiverem informação de os associe. Ou seja, se um endpoint depender de outro de alguma forma, é uma boa prática que os paths reflitam essa dependência. Por exemplo, se tivermos um path pastries e um path croissant, croissant deve ser acrescentado após o path pastries : /bread/croissant, porque um faz parte do outro.

- Fazer cache dos dados para melhorar a performance

  Retornar dados da base de dados é muito mais dispendioso do que aceder a uma cache de memória. Para além disso, aceder à cache permite que a  informação seja devolvida muito mais rapidamente. O tipo de caching pode ser adaptado consoante as necessidades.
