# Evolent Employee managment Steps to setup server application

**Clone the application**
     
   git clone https://github.com/gvibhute24/evolent.git
   
   cd evolentserver

**Create MySQL database**
     
   create database evolent
 
**Change MySQL username and password as per your MySQL installation**
   
  open src/main/resources/application-prod.properties file.
  change spring.datasource.username and spring.datasource.password properties as per your mysql installation
 
**Run app**

Please install java 8+ and maven 
 
  mvn clean package
  
  cd target
  
  java -jar -Dspring.profiles.active=prod evolent.jar
 
  The server will start on port 8080.
 
 
