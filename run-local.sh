#!/bin/bash

mkdir -p logs

echo "kill instances"
/usr/bin/pkill -9 -f eureka-server
/usr/bin/pkill -9 -f fraction-service

echo "build"
$M2/mvn package > logs/build.log

echo "eureka-server 8761"
java -Dspring.profiles.active=local -jar eureka-server/target/eureka-server-*.jar > logs/eureka-server.log &
sleep 5

echo "fraction-service 8001"
java -Dspring.profiles.active=local -jar fraction-service/target/fraction-service-*.jar > logs/fraction-service.log &
sleep 5

echo "math-service 8101"
java -Dspring.profiles.active=local -jar math-service/target/math-service-*.jar > logs/math-service.log &
sleep 5

echo "zuul-server 8765"
java -Dspring.profiles.active=local -jar zuul-server/target/zuul-server-*.jar > logs/zuul-server.log &
sleep 5