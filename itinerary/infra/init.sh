#! /bin/bash -e


function runProjects() {
  echo Running dependency projects

  docker-compose -f ../../cities/infra/docker-compose.yml up -d;
  docker rm route || true;
  docker rm discovery || true;
  docker run -d -p 8081:8081 --name route route:master;
  docker run -d -p 8761:8761 --name discovery discovery:master;
}

runProjects