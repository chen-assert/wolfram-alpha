# Deployment
## 0.Install Apache Maven
See there:https://maven.apache.org/install.html
## 1.Install WolframAlpha lib
Firstly, we should install the wolframAlpha lib to local maven repository
By execute these command
>mvn install:install-file -Dfile=Assignment2Libs/WolframAlpha-1.1.jar -DgroupId=com.wolfram.alpha -DartifactId=wolfram -Dversion=1.1 -Dpackaging=jar
## 2.Running from Maven
directly running the project through maven
> mvn spring-boot:run

or use
> mvn package

to build jar file  
and use
> java -jar target/*.jar

to run it  

Then the server can be access through http://localhost:5000