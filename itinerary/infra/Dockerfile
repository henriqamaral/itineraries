FROM openjdk:8-alpine
MAINTAINER Henrique
VOLUME /tmp

RUN adduser -D -u 1000 itinerary
ADD app/target/itinerary.jar /opt/itinerary.jar
USER itinerary
CMD java -jar -Dspring.profiles.active=$PROFILE /opt/itinerary.jar