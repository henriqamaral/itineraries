# INTINERARY


#### Running tests

* mvn -f app/pom.xml clean test
* mvn -f app/pom.xml org.pitest:pitest-maven:mutationCoverage -U -DtimestampedReports=false -Dthreads=4

#### Creating Docker image

* mvn -f app/pom.xml clean package
```
docker build -t itinerary:master --file=infra/Dockerfile .
```

