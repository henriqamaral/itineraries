# ROUTE

#### Running project
```
docker-compose -f infra/docker-compose.yml up -d
```
 * mvn -f app/pom.xml spring-boot:run

#### Running tests

* mvn -f app/pom.xml clean test

#### Creating Docker image

* mvn -f app/pom.xml clean package
```
docker build -t route:master --file=infra/Dockerfile .
```
	
#### Running Docker Image with docker compose

```
docker-compose -f infra/docker-compose-dev.yml up -d
```


#### Added initial data