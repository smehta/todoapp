A sample todo app implemented using Jersey, Maven and mongodb.

Pre-requisites:
---------------

Please set following environment variables

export TWILIO_ACCOUNT_SID=
export TWILIO_AUTH_TOKEN=
export TWILIO_TO=
export TWILIO_FROM=
export SEARCHLY_URI=
export MONGO_HOST=
export MONGO_PORT=


Sample JSON of todo app:
{ "title" : "Cleaning house" "body" : "Laundry etc..", "done" : false }

REST URI for the following todo app use cases:


1. Get all todo items ( GET - http://localhost:8080/todo/items )

2. Get todo item by id ( GET - /todo/items/{id} )

3. Delete todo item ( DELETE - /todo/items/{id} )

4. Create todo item ( POST - /todo/items/)

5. Update item(title, body, done)  ( PUT - /todo/items/{id} )

6. search for item ( GET - /todo/items?q=“cleaning” )
