FROM openjdk:8-alpine
MAINTAINER Henrique
VOLUME /tmp
ADD infra/init.sh /opt/init.sh
RUN chmod +x /opt/init.sh

RUN adduser -D -u 1000 route
ADD app/target/route.jar /opt/route.jar
USER route
CMD java -jar -Dspring.profiles.active=$PROFILE /opt/route.jar