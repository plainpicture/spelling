#!/bin/sh

/usr/bin/java ${JAVA_OPTS} -jar target/spelling-0.0.1-jar-with-dependencies.jar `ls data/*.dic | sort`

