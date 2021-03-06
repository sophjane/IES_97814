---> 1.2 Build management with the Maven tool

c)

CRIAR um PROJETO Maven pela linha de comandos:

    $ mvn archetype:generate -DgroupId=com.mycompany.app -DartifactId=my-app -DarchetypeArtifactId=maven-archetype-quickstart -DarchetypeVersion=1.4 -DinteractiveMode=false
        
    Notas:
        O archetype é um plugin - conjunto de objetivos com um propósito geral comum -  as outras informações são parâmetros para atingir esse objetivo

        Alterar groupId e o artifactId de acordo com o projeto. 
        groupId = identifica o projeto de forma única em relação aos outros projetos. Segue as regras de nomes de Java packages, isto é, começa por ter o nome de um domínio da internet, ex. sun.com, que por sua vez é invertido, ex. com.sun, que passa a ser o prefixo das packages e depois podemos usar os subgrupos necessários para identificar com mais especificidade (tipo path). 

        artifactId = é o nome do jar sem versão. Se formos nós a criar podemos escolher um nome em lower case, se for de outro, manter o nome do jar distribuído, ex. Maven. (nome do projeto ou biblioteca)

        “-D” usado para definir ou passar uma propriedade para o Maven em CLI. 
  
        version = versão se for distribuído (número e pontos - 1.0, 1.1, 1.0.1, ...). Usar versão atual, se for de outro. Exemplos: 2.0, 2.0.1, 1.3.1


Após a criação do projeto é criado uma diretoria com o nome do artifactId.


ESTRUTURA geral do projeto:

    my-app
    |-- pom.xml
    `-- src
        |-- main
        |   `-- java
        |       `-- com
        |           `-- mycompany
        |               `-- app
        |                   `-- App.java
        `-- test
            `-- java
                `-- com
                    `-- mycompany
                        `-- app
                            `-- AppTest.java

    pom.xml = centro da configuração de um projeto em Maven. Contém a maioria da informação necessária para o build de um projeto.


BUILD de um projeto (dentro da diretoria):
    
    $ mvn package

    Nota:
        Package aqui já não é um objetivo mas sim uma fase. Uma fase é um passo no build lifecycle (sequência ordenada de fases). Quando é dada uma fase, o Maven executa todas as fases até à atual (inclusive). 


FASES defaut do lifecycle do Maven:

    -> validate (faz a validação de que o projeto está correto e tem toda a informação necessária disponível)
    -> compile (compila o código fonte do projeto)
    -> test (testa o código fonte compilado)
    -> package (passa o código compilado e coloca-o numa package no seu formato distribuído, ex. JAR)
    -> integration-test (processa e faz deploy da package, se necessário, para um ambiente onde possam correr os testes de integração)
    -> verify (valida a package e se confere os critérios de qualidade)
    -> install (instala a package num repositório local)
    -> deploy (copia a package final para o repositório remoto para partilha com outro developers e etc, em ambeinte de integração ou release)

    Duas outras fases possiveis são clean (limpa os artifactos criados em builds anteriores) e site (gera documentação)

    As fases e objetivos podem ser executadas em sequência, ex. $ mvn clean dependency:copy-dependencies package (limpa o projeto, copia as dependências, e coloca o projeto numa package).


LINKS importantes:

    Maven in 5 minutes: https://maven.apache.org/guides/getting-started/maven-in-five-minutes.html
    Guide to naming conventions on groupId, artifactId, and version: https://maven.apache.org/guides/mini/guide-naming-conventions.html
    Unique package names (Java): https://docs.oracle.com/javase/specs/jls/se6/html/packages.html#7.7
    Introduction to the Standard Directory Layout: https://maven.apache.org/guides/introduction/introduction-to-the-standard-directory-layout.html


d) 

$ curl https://api.ipma.pt/open-data/forecast/meteorology/cities/daily/1010500.json | json_pp
  % Total    % Received % Xferd  Average Speed   Time    Time     Time  Current
                                 Dload  Upload   Total   Spent    Left  Speed
100  1150  100  1150    0     0   3898      0 --:--:-- --:--:-- --:--:--  3885
{
   "globalIdLocal" : 1010500,
   "owner" : "IPMA",
   "dataUpdate" : "2021-10-15T19:31:04",
   "country" : "PT",
   "data" : [
      {
         "latitude" : "40.6413",
         "idWeatherType" : 5,
         "tMax" : "20.0",
         "predWindDir" : "NW",
         "forecastDate" : "2021-10-15",
         "classWindSpeed" : 2,
         "precipitaProb" : "0.0",
         "longitude" : "-8.6535",
         "tMin" : "11.9"
      },
      {
         "tMin" : "13.5",
         "longitude" : "-8.6535",
         "precipitaProb" : "59.0",
         "classPrecInt" : 2,
         "classWindSpeed" : 1,
         "forecastDate" : "2021-10-16",
         "predWindDir" : "W",
         "tMax" : "20.1",
         "latitude" : "40.6413",
         "idWeatherType" : 9
      },
      {
         "longitude" : "-8.6535",
         "tMin" : "16.4",
         "classPrecInt" : 2,
         "precipitaProb" : "94.0",
         "classWindSpeed" : 1,
         "predWindDir" : "S",
         "tMax" : "22.1",
         "idWeatherType" : 9,
         "latitude" : "40.6413",
         "forecastDate" : "2021-10-17"
      },
      {
         "forecastDate" : "2021-10-18",
         "idWeatherType" : 9,
         "latitude" : "40.6413",
         "tMax" : "22.0",
         "predWindDir" : "S",
         "classWindSpeed" : 1,
         "classPrecInt" : 2,
         "precipitaProb" : "47.0",
         "tMin" : "18.3",
         "longitude" : "-8.6535"
      },
      {
         "longitude" : "-8.6535",
         "tMin" : "16.1",
         "classWindSpeed" : 2,
         "precipitaProb" : "21.0",
         "predWindDir" : "SW",
         "idWeatherType" : 2,
         "latitude" : "40.6413",
         "tMax" : "24.2",
         "forecastDate" : "2021-10-19"
      }
   ]
}


f)

POM é um acrónimo para Project Object Model. Este ficheiro contém informação do projeto e de configuração para o maven fazer build do projeto, incluindo as dependências, build directory, source directory, test source directory, plugins, entre outras coisas.
O Maven lê este ficheiro e depois executa o objetivo.
É constituído por várias tags (elementos):
-> <project></project> é o elemento raiz (root element)
-> <modelVersion></modelVersion> é um sub elemento, especifica a versão do modelo e deve ser 4.0.0 
-> <groupId></groupId> é um sub elemento, indica o id do grupo do projeto
-> <artifactId></artifactId> é um sub elemento, indica o id do artifacto (projeto). Um artifacto é algo produzido ou usado por um projeto. Exs. JARs
-> <version></version> é um sub elemento, especifica a versão do artifacto de um grupo

Pode apresentar outros elementos como <packaging></packaging>, <name></name>, <url></url>, <dependencies></dependencies>, <dependecy></dependency>, <scope></scope>, cujos os nomes são auto-explicativos.


h)

O Gson é uma biblioteca  do Java que pode ser usada para converter objetos Java nas suas representações em JSON. Pode trabalhar com objetos de Java arbitrários incluindo objetos pre-existentes dos quais não temos o código fonte.
Maven: 
	<dependency>
	  	<groupId>com.google.code.gson</groupId>
	  	<artifactId>gson</artifactId>
	  	<version>2.8.8</version>
	</dependency>

-> https://sites.google.com/site/gson/gson-user-guide


O Retrofit é um cliente HTTP type-safe para o Java, permite o mapeamento de uma REST API (*) externa a um interface (Java) local.

(*) Uma API, ou Application Programming Interface, é um conjunto de regras que definem como as aplicações ou dispositivos podem conectar e comunicar umas com as outras. Uma REST API é uma API que está de acordo com os principios de design REST, que são um conjunto de restrições de arquitetura. (https://www.redhat.com/en/topics/api/what-is-a-rest-api)
Maven:
	<dependency>
		<groupId>com.squareup.retrofit2</groupId>
		<artifactId>retrofit</artifactId>
		<version>2.7.0</version>
	</dependency>


k)

Executar código com args recebidos pela linha de comandos:
$ mvn exec:java -Dexec.mainClass=test.Main -Dexec.args="arg1 arg2 arg3" 

Para correr:
$ mvn package
$ mvn exec:java -Dexec.mainClass="com.mycompany.weather.WeatherStarter" -Dexec.args="1010500"


---> 1.3 Source code management using Git

Inicialização:

- Criar um repositório a partir da linha de comandos (também pode ser feito diretamente no Github)

       $ git init

- Clonar, ie, copiar o repositorio online para o pc

      $ git clone endereçoDoRepositorio

O repositório guarda todas as modificações do nosso código (uma BD).

O README.md contém informações sobre o código.

Adicionar informação:

	$ git add README.md

	$ git add nomeDoFicheiro

Acrescentar modificações ao repositório master, ie, o do pc:

	$ git commit -m "adicionar o Readme" - O -m indica que se segue uma mensagem de commit. O commit passa os ficheiros staged para a história do projeto

Apagar ficheiro:

	$ git rm README.md

Sincronizar o repositório, estando só eu no repositório basta enviar para o repositório local:

	$ git push 

Se quisermos mandar o código para outra pessoa, enviamos para o servidor remoto, temos de fazer o seguinte:

	$ git remote add origin endereçoDoRepositorio

	$ git push -u origin master - estamos a puxar tudo para a main branch na origin (repositório remoto)

Para receber código temos de fazer:

	$ git pull (fetch+merge)

Pode entrar em conflito se houver código alterado e há duas versões, um local e outro do repositório remoto, aí temos de fazer o merge nós.

Estado da working tree:

	$ git status - dá-nos informação do que necessita de ser committed ou não e se está atualizado com a origin. Basicamente, permite ver como o projeto está a progredir em comparação como o repositório. Quando um ficheiro está untracked significa que o Git encontrou um ficheiro que não fazia parte do commit anterior e irá sugerir adicioná-lo.

	$ git log - permite ver o histórico guardado no git

Diferenças entre commits:

Fazendo primeiro $ git log nomeDoFicheiro

	$ git diff commit_id - dá-nos as diferenças entre duas versões

	$ git show commit_id - mostra o que era importante eu guardar naquele commit

Criar uma branch:

	$ git branch nomeDaBranch - cria a branch mas não estamos dentro dela
	
Para entrar na branch temos de fazer:
	$ git checkout nomeDaBranch
	
Para voltar à main quando estamos numa branch:
	$ git checkout main
	
Adicionar as alterações da branch à main:
	(na main) $ git merge nomeDaBranch
	
Eliminar branch:
	$ git branch -d nomeDaBranch 


e) 

Link: https://www.baeldung.com/java-logging-intro

Colocar no pom.xml: 
	<dependency>
	    <groupId>org.apache.logging.log4j</groupId>
	    <artifactId>log4j-api</artifactId>
	    <version>2.6.1</version>
	</dependency>
	<dependency>
	    <groupId>org.apache.logging.log4j</groupId>
	    <artifactId>log4j-core</artifactId>
	    <version>2.6.1</version>
	</dependency>

O logging tem diversos usos e pode ser imprimido no terminal ou num ficheiro. Podemos fazer logging de debug, de informações, de erros, entre outros. É uma ferramenta fundamental para um programador pois permite fazer o tracking dos eventos que ocorrem enquanto o software corre. Podemos ver os eventos através de mensagens descritivas e, opcionalmente, conter dados caso seja útil, por exemplo, dados ou valores que possam variar quando o programa é corrido. 

Para correr:
$ mvn package
$ mvn exec:java -Dexec.mainClass="com.mycompany.weather.WeatherStarter" -Dexec.args="1010500" -Dlog4j.configurationFile=./log4j2.xml


Para fazer commits, devem ser feitos da mesma forma habitual.
$ git add .
$ git commit -m "some message"
$ git push origin master

---> 1.4 Introduction do Docker

Links:

https://www.airpair.com/docker/posts/8-proven-real-world-ways-to-use-docker
https://docs.docker.com/get-started/

O Docker é uma plataforma de desenvolvimento de software open source. Permite colocar as aplicações em containers, permitindo que estas possam ser suportadas por qualquer sistema que estiver a correr num sistema operativo. 

Quando estamos a correr um container, ele irá ter um sistema de ficheiros isolado. Este sistema de ficheiros é fornecido por um container image. Como a image contém o sistema de ficheiros, deve também conter tudo o que é necessário para correr a aplicação. 

Casos de uso do Docker: https://www.airpair.com/docker/posts/8-proven-real-world-ways-to-use-docker

$ docker run -d -p 80:80 docker/getting-started
-d - correr o container em detached mode (in the background)
-p 80:80 - mapear o port 80 do host ao port 80 no container
docker/getting-started - a image a usar

O Portainer é constituído por dois elementos: o Portainer Server, e o Portainer Agent. Ambos correm como containers lightweight do Docker num Docker engine.

Para fazer o build de uma aplicação, precisamos de usar um Dockerfile. Este ficheiro é simplesmente um script text-based com instruções para criar uma container image.

# syntax=docker/dockerfile:1
FROM node:12-alpine
RUN apk add --no-cache python g++ make
WORKDIR /app
COPY . .
RUN yarn install --production
CMD ["node", "src/index.js"]
- Depois da image ter sido baixada, copiámos a aplicação e usamos o yarn para instalar as dependências da aplicação. A diretiva CMD especifica o comando default a correr quando iniciarmos um container desta image.

$ docker build -t getting-started .
Este comando usa o Dockerfile para fazer o build de um novo container image
A flag -t faz o tag da image. Já que nomeamos a image getting-started, podemos agora referir-nos a essa image quando corremos o container.
O . no fim do comando do Docker build diz que o Docker deve procurar pelo Dockerfile na diretoria atual.

Running docker command (example) :  $ docker run -dp 3000:3000 getting-started

Estamos a correr o novo container em "detached" mode e a mapear o port do hosto 3000 para o port do container 3000. Sem este mapeamento não conseguiriamos aceder à aplicação.

Se fizermos $ docker ps, vemos que um novo container foi lançado no port 3000. Para ver o resultado vamos a http://localhost:3000/.

O container image funciona como o ambiente em que a aplicação deve correr, o "script" aka Dockerfile vai ditar as condições desse ambiente. A nível empresarial, por exemplo, vários programadores podem estar a desenvolver features numa aplicação, se quiserem testar num ambiente comum, todos devem ter o mesmo Dockerfile.

e)
# syntax=docker/dockerfile:1
FROM python:3.7-alpine
WORKDIR /code
ENV FLASK_APP=app.py
ENV FLASK_RUN_HOST=0.0.0.0
RUN apk add --no-cache gcc musl-dev linux-headers
COPY requirements.txt requirements.txt
RUN pip install -r requirements.txt
EXPOSE 5000
COPY . .
CMD ["flask", "run"]

This tells Docker to:
Build an image starting with the Python 3.7 image.
Set the working directory to /code.
Set environment variables used by the flask command.
Install gcc and other dependencies
Copy requirements.txt and install the Python dependencies.
Add metadata to the image to describe that the container is listening on port 5000
Copy the current directory . in the project to the workdir . in the image.
Set the default command for the container to flask run.

version: "3.9"
services:
  web:
    build: .
    ports:
      - "5000:5000"
  redis:
    image: "redis:alpine"

O ficheiro Compose define dois serviços: web e redis.

Web service
-> O serviço web usa uma image que é criada do Dockerfile na diretoria atual. Faz o binding do container e a host machine ao port exposto, 5000. Este exemplo de service usa o port default para o Flask Web Server, 5000.
Redis service
-> O serviço Redis usa uma image Redis pública do Docker Hub registry.

Fazer build e correr a app com Compose: $ docker-compose up

O Compose faz o pull de uma image Redis, faz o build de uma image para o nosso código, e começa os serviços definidos.

Para-se a aplicação, correndo $ docker-compose down na pasta do projeto num segundo terminal (se não estiver a correr em background) ou fazendo CTRL+C no terminal original onde iniciámos a app (se estiver a correr em background).

version: "3.9"
services:
  web:
    build: .
    ports:
      - "5000:5000"
    volumes:
      - .:/code
    environment:
      FLASK_ENV: development
  redis:
    image: "redis:alpine"

The new volumes key mounts the project directory (current directory) on the host to /code inside the container, allowing you to modify the code on the fly, without having to rebuild the image. The environment key sets the FLASK_ENV environment variable, which tells flask run to run in development mode and reload the code on change. This mode should only be used in development.

Usar $ docker-compose ps para ver o que está atualmente a correr.

Para ver que variáveis de ambeinte estão disponíveis no web service : $ docker-compose run web env.

Podemos mandar tudo abaixo, removendo inteiramente os containers com o comando down. Podemos passar --volumes para também remover dados de volume usados pelo container Redis. $ docker-compose down --volumes

---> 1.5 Wrapping-up & integrating concepts

Comandos para correr:

> Dentro do ipma-api-client: $ mvn install
> Dentro do weather-forecast-by-city: $ mvn package e $ mvn exec:java -Dexec.mainClass="com.mycompany.WeatherForecastByCity.App" -Dexec.args="Castelo Branco" (por exemplo)

O projeto WeatherForecastByCity só tem uma main classe (App.java) que recebe e devolve informação do terminal mas está dependente do projeto IpmaApiClient que irá tratar das chamadas às APIs para buscar a informação meteriológica adequada. A "main" classe do projeto IpmaApiClient (APIHandler.java) é responsável por fazer o tratamento das chamadas às APIs. Em primeiro lugar, é invocada uma API  que vai buscar as informações das cidades, com principal relevância para o ID que irá se usado para a chamada da segunda API. Nesta se encontram os dados meteriológicos da cidade em causa. As restantes classes estão ligadas às chamadas às APIs (IpmaService) ou contêm métodos dos dados que procuramos obter. 

--> Review questions

A) Esta questão já foi respondida no início do README.md no exercício 1.2 - c)

B) O Maven pode ser muito mais do que um build software. Pode também "colaborar" na execução de web applications. É principalmente usado o comando exec (um plugin), que permite correr programas do sistema e programas Java.

C) 
1. Clonar repositório
	$ git clone [url]

2. Criar branch
	$ git checkout -b [nome] 

3. Depois de terminado o desenvolvimento, puxar a branch para o repositorio remoto.
	$ git add .
	$ git commit -m [mensagem]
	$ git push --set-upstream [nome do repositorio (origin)] [nome da branch]

4. Criar um pull request no Github, que vai ser revisto pelos colaboradores aceitarem a nova feature.

5. Após as aprovações necessárias, ou se usa o Github para fazer o merge automático ou no terminal voltamos à branch master e fazemos merge manualmente.
	$ git checkout -b master
	$ git merge [nome da branch]


D) <tipo>[alcance opcional]: <descrição>

Os tipos mais utilizados são:
- feat, quando é adicionada uma feature;
- fix, quando é resolvido um bug;
- chore, quando é desenvolvido algo que não afeta diretamente o código. Exemplos: mudar README.md, criar testes, entre outros.
	
Nota: existem outros tipos como, build, ci, docs, style, refactor, perf, test.

O alcance opcional permite fornecer um maior contexto ao que está a ser adicionado. Exemplos: número de um ticket ou uma categoria do código.

A descrição deve ser breve e "straight to the point", ou seja, explicita.

Sharing data among multiple running containers. If you don’t explicitly create it, a volume is created the first time it is mounted into a container. When that container stops or is removed, the volume still exists. Multiple containers can mount the same volume simultaneously, either read-write or read-only. Volumes are only removed when you explicitly remove them.

E) Devemos configurar os volumes para uma base de dados (produção) pois eles permite partilhar dados entre múltiplos containers que estão a correr em simultâneo. Quando um container para ou é removido, o volume continua a existir, ou seja, os dados estão seguros e protegidos. Para além disso, vários containers podem estar a montar o mesmo volume em simultâneo, com operações de read-write ou read-only, funcionando como back-ups. Os volumes são só removidos se os removermos explicitamente. 



