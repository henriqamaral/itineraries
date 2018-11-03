# ROUTE

#### Running tests

* mvn -f app/pom.xml clean test
* mvn -f app/pom.xml org.pitest:pitest-maven:mutationCoverage -U -DtimestampedReports=false -Dthreads=4

#### Creating Docker image

  * mvn -f app/pom.xml clean package
```
docker build -t route:master --file=infra/Dockerfile .
```
	


#### Added initial data

```
curl -X POST "http://localhost:8081/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Sao Paulo\",\"destiny\" : \"Macapa\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"11:00\"}"
curl -X POST "http://localhost:8081/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Macapa",\"destiny\" : \"Rio de Janeiro\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"11:00\"}"
curl -X POST "http://localhost:8081/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Rio de Janeiro",\"destiny\" : \"Salvador\",\"departureTime\" : \"12:00\",\"arrivalTime\" : \"16:00\"}"
curl -X POST "http://localhost:8081/routes" -H "accept: */*" -H "Content-Type: application/json" -d "{ \"from\" : \"Sao Paulo",\"destiny\" : \"Salvador\",\"departureTime\" : \"9:00\",\"arrivalTime\" : \"19:00\"}"
```