Todo App
____________
Demonstrates the capabilities of REST api implemented using Jersey, Maven, mongodb, elasticsearch and twilio. 

Pre-requisites:
---------------

Please set following environment variables :

*  TWILIO_ACCOUNT_SID=
*  TWILIO_AUTH_TOKEN=
*  TWILIO_TO=
*  TWILIO_FROM=
*  SEARCHLY_URI=
*  MONGO_HOST=
*  MONGO_PORT=

Example: 
$ export MONGO_HOST="localhost"

REST API
--------------
Sample JSON of todo app:
{ "title" : "Cleaning house" "body" : "Laundry etc..", "done" : false }

REST URI for the following todo app use cases:


1. Get all todo items ( GET - http://localhost:8080/todo/items )

2. Get todo item by id ( GET - /todo/items/{id} )

3. Delete todo item ( DELETE - /todo/items/{id} )

4. Create todo item ( POST - /todo/items/)

5. Update item(title, body, done)  ( PUT - /todo/items/{id} )

6. search for item ( GET - /todo/items?q=“cleaning” )
