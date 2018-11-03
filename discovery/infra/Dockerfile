FROM openjdk:8-alpine
MAINTAINER Henrique
VOLUME /tmp

RUN adduser -D -u 1000 discovery
ADD app/target/discovery.jar /opt/discovery.jar
USER discovery
CMD java -jar -Dspring.profiles.active=$PROFILE /opt/discovery.jar