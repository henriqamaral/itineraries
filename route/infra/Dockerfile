FROM openjdk:8-alpine
MAINTAINER Henrique
VOLUME /tmp

RUN adduser -D -u 1000 route
ADD app/target/route.jar /opt/route.jar
USER route
CMD java -jar -Dspring.profiles.active=$PROFILE /opt/route.jar