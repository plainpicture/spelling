#!/bin/sh

exec /usr/bin/java -Xms512m -Xmx1024m -jar target/spelling-0.0.1-jar-with-dependencies.jar `ls data/*.dic | sort`

