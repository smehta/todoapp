Todo App
---------------
Demonstrates the capabilities of REST api implemented using Jersey, Maven, mongodb, elasticsearch and twilio. 

Pre-requisites:
---------------

Please set following environment variables :

*  TWILIO_ACCOUNT_SID
*  TWILIO_AUTH_TOKEN
*  TWILIO_TO
*  TWILIO_FROM
*  SEARCHLY_URI
*  MONGO_HOST
*  MONGO_PORT

Example: 

     $ export MONGO_HOST=localhost


To get the code:
-------------------
Clone the repository:

    $ git clone git://github.com/smehta/todoapp.git

To run the application:
-------------------	
From the command line with Maven:

    $ cd todoapp
    $ mvn clean package
    $ java -jar target/dependency/webapp-runner.jar target/*.war

REST API Details :
--------------
REST URI for the following todo app use cases:


1. Get all todo items ( GET - http://localhost:8080/todo/items )

2. Get todo item by id ( GET - /todo/items/{id} )

3. Delete todo item ( DELETE - /todo/items/{id} )

4. Create todo item ( POST - /todo/items/)

5. Update item(title, body, done)  ( PUT - /todo/items/{id} )

6. search for item ( GET - /todo/items?q=“cleaning” )


Sample JSON

     { "title" : "Cleaning house" "body" : "Laundry etc..", "done" : false }

