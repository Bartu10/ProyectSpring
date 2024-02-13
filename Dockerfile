FROM eclipse-temurin:17
EXPOSE 8080
COPY build/libs/*.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar","-Djava.net.preferIPv4Stack=true","-Djava.net.preferIPv6Addresses=false"]
