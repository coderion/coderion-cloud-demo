#!/bin/bash

mkdir -p logs

echo "kill instances"
/usr/bin/pkill -9 -f turbine-server
/usr/bin/pkill -9 -f hystrix-dashboard-app
/usr/bin/pkill -9 -f zuul-server
/usr/bin/pkill -9 -f eureka-server
/usr/bin/pkill -9 -f config-server
/usr/bin/pkill -9 -f math-service
/usr/bin/pkill -9 -f fraction-service

echo "build"
$M2/mvn package > logs/build.log

echo "config-server 8888"
java -Dspring.profiles.active=local -jar config-server/target/config-server-*.jar >> logs/config-server.log &
sleep 5

echo "eureka-server 8761"
java -Dspring.profiles.active=local -jar eureka-server/target/eureka-server-*.jar >> logs/eureka-server.log &
sleep 5

echo "fraction-service 8001"
java -Dspring.profiles.active=local -jar fraction-service/target/fraction-service-*.jar >> logs/fraction-service.log &
sleep 5

echo "math-service 8101"
java -Dspring.profiles.active=local -jar math-service/target/math-service-*.jar >> logs/math-service.log &
sleep 5

echo "zuul-server 8765"
java -Dspring.profiles.active=local -jar zuul-server/target/zuul-server-*.jar >> logs/zuul-server.log &
sleep 5

echo "hystrix-dashboard-app 7979"
java -Dspring.profiles.active=local -jar hystrix-dashboard-app/target/hystrix-dashboard-app-*.jar >> logs/hystrix-dashboard-app.log &
sleep 5

echo "turbine-server 8989"
java -Dspring.profiles.active=local -jar turbine-server/target/turbine-server-*.jar >> logs/turbine-server.log &
sleep 5