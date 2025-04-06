# Movie Management App (Spring Boot)

This is a Spring Boot project to manage movies with authentication and role-based access.

### Default Admin User

{

  "username": "admin",

  "password": "password123"

}

### Features

Login with admin or user roles

Signup endpoint to add new users (user or admin)

Admins can add movies from external API

Uses OMDB API: https://www.omdbapi.com/

You need to get your own API key and set it in application.properties:

### OMDB Properties


omdb.api.key=putYourOwnAPIKeyHere

omdb.api.url=http://www.omdbapi.com/

### H2 database used

On each restart, it initializes:

Roles (USER, ADMIN)

Default admin 
