@echo off
start cmd /k ".\mvnw spring-boot:run"
start cmd /k "cd front && npm install && npm start"
