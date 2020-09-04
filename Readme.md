# Challenge
Small product review Api
### Installing
```
gradle bootJar
docker build --build-arg JAR_FILE=build/libs/*.jar -t addidas-challenge .
docker run -p 80:8080 --name challenge -d addidas-challenge
```
