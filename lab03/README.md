## Lab 3 Multi-layer web applications with Spring Boot

### 3.1 Accessing databases in SpringBoot

​	[What is JPA? Introduction to the Java Persistence API](https://www.infoworld.com/article/3379043/what-is-jpa-introduction-to-the-java-persistence-api.html)

​	A JPA (Java Persistent API) define um interface standard de gestão de dados sobre base de dados relacionais. O Spring Data usa e realça a JPA. Quando usamos Spring data, o nosso código java é independente de uma implementação específica da base de dados.

​	A JPA lida com a persistência, permite definir quais objetos devem ser persistidos e como devem ser persistidos nas nossas aplicações Java. Em si, a JPA não é uma ferramenta ou uma framework mas um conjunto de conceitos que podem ser implementados por qualquer ferramenta ou framework.

​	Qualquer implementação JPA fornece uma camada ORM (Object-Relational Mapping), que é responśavel por gerir a conversão de objetos de software para interagir com tabelas e colunas de uma base e dados relacional. 

​	Regra geral, o nome do objeto que está a ser persistido torna-se o nome da tabela e os campos tornam-se os nomes das colunas. Quando a tabela está pronta, cada linha da tabela corresponde a um ojeto da aplicação.

​	Quando se usa JPA, cria-se um mapa da datastore para os data model objects da nossa aplicação. Em vez de definir como é que os objetos vão ser guardados ou devolvidos, definimos o mapeamento entre os objetos e a base de dados e depois invocamos a JPA para os persistir. 

​	a) [Spring Boot CRUD](https://www.baeldung.com/spring-boot-crud-thymeleaf)

​	A Spring Data JPA permite que possamos implementar respositórios JPA-based facilmente. A Spring Data JPA é uma componente chave do spring-boot-starter-data-jpa do Spring Boot que facilita a adição de funcionalidade CRUD através de uma camada de abstração poderosa que é colocada no topo da implementação JPA. 

```bash
$ mvn clean compile
$ mvn exec:java -Dexec.mainClass="com.example.springbootcrud.SpringbootcrudApplication" 

Ir ao http://localhost:8080
```

​	b) [Acessing data JPA](https://spring.io/guides/gs/accessing-data-jpa/)

  - The “UserController” class gets an instance of “userRepository” through its constructor; how is this new repository instantiated?

    ​	O repositório é instanciado através da anotação @AutoQired, não sendo necessário criar um objeto com o operador new.

  - List the methods invoked in the “userRepository” object by the “UserController”. Where are these methods defined?

    Os métodos invocados são: save(), findAll(), findById(), delete(). Estes métodos estão definidos no interface CrudRepository. 

    Nota: A equipa do Spring Data JPA criou uma solução para construir métodos standards (que maior parte das aplicações iriam usar) de forma a nós não termos de os implementar. Só temos de criar um interface porque o Spring Data JPA encarrega-se de providenciar a classe. Por isso, vamos ter um interface que vai usar os métodos que vêm do Spring Data JPA. Como todos os repositórios têm os mesmos métodos, usasse o repositório comum (CrusRepository) que é um interface que contém a lógica para qualquer classe Entity (a única coisa que muda é o tipo), ie, os métodos standard. O CrudRepository tem um tipo genérico, temos de especificar o tipo de Entity, neste caso, User e o tipo de dados do Id, que é Long.

  - Where is the data being saved?

    Os dados são armazenados na base de dados através do método save(). 

  - Where is the rule for the “not empty” email address defined?

    O email address não pode estar vazio graças há anotação @NotBlank definida na classe User. Esta anotação usa a classe NotBlankValidator que verifica se o comprimento trimmed de uma sequência de caracteres não está vazio.

c) Adicionei o Phone number em String (int não estava a funcionar).

---

### 3.2 Multilayer applications: exposing data with REST interface

​	a) 

```bash
$ sudo docker run --name mysql5 -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=demo -e MYSQL_USER=demo -e MYSQL_PASSWORD=secret2 -p 33010:3306 -d mysql/mysql-server:5.7

```

​	b) - e) estão no código

​	f) 

```bash
$ mvn package
$ mvn exec:java -Dexec.mainClass="net.guides.springboot2.springboot2jpacrudexample.Springboot2JpaCrudExampleApplication"

```

Insert employee (example):	![](/home/sophie/Pictures/Screenshot from 2021-11-18 10-12-47.png)

Get employees (example):

![](/home/sophie/Pictures/Screenshot from 2021-11-18 10-19-40.png)

Get employee by id (example):![](/home/sophie/Pictures/Screenshot from 2021-11-18 10-15-17.png)

Edit employee by id (example):

![](/home/sophie/Pictures/Screenshot from 2021-11-18 10-20-10.png)

Delete employee by id:

![](/home/sophie/Pictures/Screenshot from 2021-11-18 10-21-16.png)

g) 

![](/home/sophie/Pictures/Screenshot from 2021-11-18 15-24-07.png)

---

### 3.3 Wrapping-up and integrating concepts

​	a) - c) (código)

```bash
$ sudo docker run --name moviequote -e MYSQL_ROOT_PASSWORD=secret1 -e MYSQL_DATABASE=moviedemo -e MYSQL_USER=moviedemo -e MYSQL_PASSWORD=secret2 -p 3303:3306 -d mysql/mysql-server:5.7

---

$ mvn package
$ mvn exec:java -Dexec.mainClass="com.exercise.moviesquotes.MoviesQuotesApplication"
```

​	d)

---

### Review questions

A) Explain the differences between the RestController and Controller components used in different parts of this lab.

​	[Controller vs. RestController](https://www.baeldung.com/spring-controller-vs-restcontroller)

​	[Controller vs. RestController 2](https://medium.com/@akshaypawar911/java-spring-framework-controller-vs-restcontroller-3ef2eb360917)

​	A anotação @Controller permite anotar controladores clássicos. É uma especialiação da classe @Component que permite-nos auto-detetar classes de implementação através de classpath scanning. Normalmente usa-se o @Controller em combinação com a antação @RequestMapping para os métodos de tratameno de requests.

​	A anotação @restController permite simplificar a criação de serviços web RESTful. É uma anotação conveniente pois combina o @Controller e o @RespondeBody, o que elimina a necessidade de anotar cada método de tratameto de requests da classe do Controller com o @ResponseBody. É uma versão especiaizada do Controller. 

B) Create a visualization of the Spring Boot layers (UML diagramor similar), displaying the key abstractions in the solution of 3.3, in particular: entities, repositories, services and REST controllers. Describe the role of the elements modeled in the diagram.

​	Service (MovieService) - trata das funcionalidades. É responsável por tratar os métodos que são chamados pelo Controller e "comunicar" com o Repository. Por exemplo, para adicionar um filme, temos a função addMovie que invoca a função saveMovie do MovieService, esta função por sua vez irá invocar a função save do MovieRepository que irá guardar o novo filme na base de dados.

​	REST Controller (MovieController e QuoteController) - trata-se de uma classe que funciona como um request handler.  em runtime é criado um REST web service no qual vão ser feitos vários pedidos GET, POST, PUT, DELETE, etc que irão ser tratados pelo Controller. Por exemplo, quando o user fizer http://localhost:8080/api/v2/addQuote fazendo um GET e introduzindo os dados de uma Quote (em JSON), o QuoteController fica responsável de lidar com esse pedido, neste caso, invoca o método saveQuote do Service.

​	Entity (Movie e Quote) - estão relacionadas com as tabelas na base de dadose representam os dados. Por exemplo, a classe Movie é uma Entity que vai dar origem a uma tabela na base de dados cujo nome será MOVIE_TABLE e que terá os mesmo campos que esta classe.

​	Repository - trata da ligação entre a base de dados e o Java. É responsável por permitir a manipulação dos dados na base de dados, tanto para aceder como para persistir. Por exemplo, quando no Service invocamos o saveQuote(), vamos estar a invocar o método save do QuoteRepository que é responsável por persistir esta nova Quote na base de dados

C) Explain the annotations @Table, @Column, @Id found in the Employee entity.

​	@Table - permite especificar os detalhes da tabela que irá ser usada para guardar/persistir os dados da entity na base de dados. A anotação tem 4 atributos aos quais podemos fazer override (neste caso, estamos a fazer override do nome para ser employees).

​	@Column - usado para especificar a coluna mapeada para uma propriedade ou campo persistente (neste caso, vamos ter as colunas: first_name, last_name e email_address na tabela employees). 

​	@Id -  usado para definir o atributo/campo que irá estar relacionado com a chave primária da tabela (neste caso, o Id não é nenhum dos atributos de Employee mas sim um valor gerado)

D) Explain the use of the annotation @AutoWired (in the Rest Controller class).

​	Ajuda a fazer o autowire do bean (instanciação) sem ter de criar um objeto usando a palavra new. Por exemplo, no 3.2,  o @AutoWired está a dizer ao contexto da aplicação para injetar uma instância de EmployeeRepository naquele lugar.

​	



