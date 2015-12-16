# Oublie Junit, embarque avec Spock

https://github.com/tgirard12/montpellierjug-spock

## Spock Framework 

https://github.com/spockframework/spock

http://spockframework.github.io/spock/docs/1.0/index.html

- Framework de test pour les applications Java et Groovy
- Tests en Groovy
- Plugin Maven et Gradle
- Intégration Spring
- Version actuelle : 1.0 
- 22 février 2009 => Premier commit


## Démo app

- Application Spring Boot en Java
- Base de données PostgreSQL 9.4 Docker
- Jooq pour l'accès aux données
- Tests unitaires et d'intégrations avec Spock

````
  +-----------+  1           n  +----------+ 
  |  SPEAKER  | <-------------- |   TALK   | 
  +-----------+                 +----------+   
````

````
  +--------------+              +-------------+              +------------+ 
  |  CONTROLLER  | <----------> |   Speaker   | <----------> |  Speaker   | 
  |    ws json   |              |   SERVICE   |              |    DAO     |
  +--------------+              +-------------+              +------------+  
                                       ˄
                                       |
                                       ˅
                                +-------------+
                                |   Twitter   |
                                |   SERVICE   |
                                +-------------+                
````


## Compilation, tests et lancement de l'application

````
docker run --name postgres -p 5432:5432 -e POSTGRES_PASSWORD=postgres -v postgres:9.4

# Executer db_create.sql
# Executer db_import.sql

# Si modification de la base de données :
./gradlew jooqGenerate

./gradlew bootRun
````