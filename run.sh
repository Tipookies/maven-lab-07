#!/bin/bash

echo "Build project..."
mvn clean package

echo "Run project..."
mvn exec:java