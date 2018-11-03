# ZUUL


#### Creating Docker image

* mvn -f app/pom.xml clean package
```
docker build -t zuul:master --file=infra/Dockerfile .
```

