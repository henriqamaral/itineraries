FROM openjdk:8-alpine
MAINTAINER Henrique
VOLUME /tmp

RUN adduser -D -u 1000 zuul
ADD app/target/zuul.jar /opt/zuul.jar
USER zuul
CMD java -jar -Dspring.profiles.active=$PROFILE /opt/zuul.jar