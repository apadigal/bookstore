# Bookstore Demo
 Bookstore Demo.
 ## About
  This is RESTfull implementation of Bookstore app. The goal of this implementation is.
  * Backend
    * Basic Authentication.
    * H2 database
    * REST Endpoints
 
 ### Users
 Available users for this application as bellow.
 User Name  | Password | Role
 ---        | ---      | ---
 admin      | admin    | ADMIN
 user       | password | USER  
 ## How to run:
  Clone this repository into your local machine.
  
 ## Build the project
 ```
 mvn clean install 
 ```
 ## Run the application
 You can either deploy the war file into Tomcat server, otherwise you can run the application using your favourite IDE.

## Accessing application.
```
 curl --user user:password http://localhost:8080/api/v1/store/books
 
 curl -u admin:admin -H "Content-Type: application/json"  -XPOST -d '{"title": "Book1:book1ee","isbn": "10000s1","price": 10.5,"author": {"id": 1,"firstName": "Anand","lastName": "Padigala"        },"genre": {"id": 1,"description": "history"}}' http://localhost:8080/api/v1/store/books
 
``` 
   
     
  
