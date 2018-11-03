#!/bin/bash

function runProject() {
	APP=/opt/route.jar

  exec $(type -p java) \
    -jar $APP \
}

runProject