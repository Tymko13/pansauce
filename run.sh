#!/bin/bash
./mvnw spring-boot:run &
cd front
npm install
npm start
