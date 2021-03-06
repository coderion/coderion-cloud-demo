#!/bin/bash

bash run-local.sh

echo "hystrix-dashboard-app 7979"
java -Dspring.profiles.active=local -jar hystrix-dashboard-app/target/hystrix-dashboard-app-*.jar > logs/hystrix-dashboard-app.log &
sleep 5

echo "turbine-server 8989"
java -Dspring.profiles.active=local -jar turbine-server/target/turbine-server-*.jar > logs/turbine-server.log &
sleep 5