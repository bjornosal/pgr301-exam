#!/bin/bash
git ls-files -o
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker build . --tag geiger-api --build-arg JAR_FILE=./target/GeigerApi.jar
docker tag geiger-api bjornosal/geiger-api:latest
docker push bjornosal/geiger-api