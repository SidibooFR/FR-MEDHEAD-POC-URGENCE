FROM openjdk:latest
VOLUME /tmp
COPY hospital/target/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]