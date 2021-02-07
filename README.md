# spring-graphql-demo
In this small project, I'm using Spring Boot, GraphQL and Unit/Integration tests.

- localhost:8080/h2, to access H2 InMemory DB
  - UserName: sa
  - Password: blank
  
- GraphiQL
  - Console accessible via http://localhost:8080/graphiql
  - Schema - location.graphqls and via the GraphiQL console at http://localhost:8080/graphql/schema.json
  
What about the service and controller? We don't need these to implement a GraphQL API. If you still want to have a REST API available, you can add those files to the application as well.

# Postman:

- To access the GraphQL:
  - http://localhost:8080/graphql/schema.json
  - From postman --> http://localhost:8080/graphql --> post --> Header/Body --> Header --> Content-Type --> application/json
    {
    "query":"{findAllLocations{ name id address } }"
    }
  - graphiql
    {
      findAllLocations {
        name
      }
    }
    
    mutation {
      newLocation (
        name: "Los Angeles",
        address: "California"
        ) {
          id
          name
          address
        }
    }
  