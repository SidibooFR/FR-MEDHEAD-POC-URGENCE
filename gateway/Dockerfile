FROM openjdk:latest
VOLUME /tmp
COPY gateway/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]
