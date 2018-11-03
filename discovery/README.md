# DISCOVERY


#### Creating Docker image

* mvn -f app/pom.xml clean package
```
docker build -t discovery:master --file=infra/Dockerfile .
```

