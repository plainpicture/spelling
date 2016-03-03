#!/bin/sh

/bin/ls data/*.dic | /usr/bin/sort | /usr/bin/xargs /usr/bin/java -jar target/spelling-0.0.1-jar-with-dependencies.jar

