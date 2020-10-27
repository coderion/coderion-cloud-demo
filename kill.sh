#!/bin/bash

/usr/bin/pkill -9 -f turbine-server
/usr/bin/pkill -9 -f hystrix-dashboard-app
/usr/bin/pkill -9 -f zuul-server
/usr/bin/pkill -9 -f eureka-server
/usr/bin/pkill -9 -f math-service
/usr/bin/pkill -9 -f fraction-service
