#!/bin/bash -e

./gradlew build

java -jar build/libs/zopa-1.0.jar $1 $2