#!/bin/bash

mkdir -p logs

echo export encryption key '"changeme"'
export ENCRYPT_KEY=changeme

echo "kill instances"
/usr/bin/pkill -9 -f turbine-server
/usr/bin/pkill -9 -f hystrix-dashboard-app
/usr/bin/pkill -9 -f zuul-server
/usr/bin/pkill -9 -f eureka-server
/usr/bin/pkill -9 -f pl.coderion.config-server
/usr/bin/pkill -9 -f math-service
/usr/bin/pkill -9 -f fraction-service

echo "build"
$M2/mvn package > logs/build.log

echo "config-server 8888"
java -Dspring.profiles.active=local -jar pl.coderion.config-server/target/pl.coderion.config-server-*.jar > logs/pl.coderion.config-server.log &
sleep 5

echo "eureka-server 8761"
java -Dspring.profiles.active=local -jar eureka-server/target/eureka-server-*.jar > logs/eureka-server.log &
sleep 5

echo "common-service 9200"
java -Dspring.profiles.active=local -jar common-service/target/eureka-server-*.jar > logs/common-service.log &
sleep 5

echo "fraction-service-1"
java -Dspring.profiles.active=local -DPORT=8001 -Dmyinstanceid=1 -jar fraction-service/target/fraction-service-*.jar > logs/fraction-service-1.log &
sleep 5

echo "fraction-service-2"
java -Dspring.profiles.active=local -DPORT=8002 -Dmyinstanceid=2 -jar fraction-service/target/fraction-service-*.jar > logs/fraction-service-2.log &
sleep 5

echo "math-service 8101"
java -Dspring.profiles.active=local -jar math-service/target/math-service-*.jar > logs/math-service.log &
sleep 5

echo "zuul-server 8765"
java -Dspring.profiles.active=local -jar zuul-server/target/zuul-server-*.jar > logs/zuul-server.log &
sleep 5

echo "auth-service 9100"
java -Dspring.profiles.active=local -jar auth-service/target/auth-service-*.jar > logs/auth-service.log &
sleep 5