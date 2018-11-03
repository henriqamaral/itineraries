# ITINERARIES MICRO SERVICES

# Tecnologies

* Spring Boot 2
* Spring Cloud 2
* Spring Data MongoDB
* Swagger
* Spring Eureka
* Spring Zuul



## Services

#### Route service
Service responsible to create and return the routes

Method	| Path	| Description	
------------- | ------------------------- | ------------- |
GET	| /routes?cityName={cityName}	| Return the routes by origin city
POST | /routes/	| Create a new route

##### The project has a README how to run the tests and build the DOCKERFILE


#### Itinerary service
Service responsible to calculate the itineraries

Method	| Path	| Description	
------------- | ------------------------- | ------------- |
GET	| /calculateShortestTime?fromCity={fromCity}&destinyCity={destinyCity}	| Return Shortest Way in Time
GET	| /calculateShortestConnections?fromCity={fromCity}&destinyCity={destinyCity}	| Return Shortest Way in Number of Connections

##### The project has a README how to run the tests and build the DOCKERFILE

### API PROXY
Using Spring Cloud Zuul to expose the microservices
Example of Configuration:
```yml
zuul:
  itinerary:
    path: /itinerary/**
    serviceId: itinerary
  route:
    path: /route/**
    serviceId: route
```

##### The project has a README how to run the tests and build the DOCKERFILE

### Service Discovery
Using Spring Cloud Eureka to connect the microservices

##### The project has a README how to run the tests and build the DOCKERFILE

## To run you need the follow tecnologies installed
- Java 8
- Maven 3.3 or higher
- Docker and Docker Compose

## Building the projects
* Each project has a README, how to build
Example:
```
mvn -f zuul/app/pom.xml clean package
mvn -f discovery/app/pom.xml clean package
mvn -f itinerary/app/pom.xml clean package
mvn -f route/app/pom.xml clean package

docker build -t zuul:master --file=zuul/infra/Dockerfile .
docker build -t discovery:master --file=discovery/infra/Dockerfile .
docker build -t itinerary:master --file=itinerary/infra/Dockerfile .
docker build -t route:master --file=route/infra/Dockerfile .
```
* Check the README's for details

## Running
- After building all the images we are prepared to run

```
docker-compose -f itinerary/infra/docker-compose.yml up -d
```
- After the services started we can create some routes to test
Example:
```
curl -X POST "http://localhost:8088/route/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Sao Paulo\",\"destiny\" : \"Macapa\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"11:00\"}"
curl -X POST "http://localhost:8088/route/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Macapa",\"destiny\" : \"Rio de Janeiro\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"11:00\"}"
curl -X POST "http://localhost:8088/route/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Rio de Janeiro",\"destiny\" : \"Salvador\",\"departureTime\" : \"12:00\",\"arrivalTime\" : \"16:00\"}"
curl -X POST "http://localhost:8088/route/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Sao Paulo",\"destiny\" : \"Salvador\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"19:00\"}"
```

## Acessing Swagger Documentation
- Here how we can access the api documentation:
```
http://localhost:8088/route/swagger-ui.html
http://localhost:8088/itinerary/swagger-ui.html
```

## Scale the projects
- We can scale the *routes* and *itineraries* API's
Example:
```
docker-compose -f itinerary/infra/docker-compose.yml scale route=3
docker-compose -f itinerary/infra/docker-compose.yml scale itinerary=2
```