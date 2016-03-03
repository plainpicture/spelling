#!/bin/sh

exec /usr/bin/java -jar target/spelling-0.0.1-jar-with-dependencies.jar `ls data/*.dic | sort`

