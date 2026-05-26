FROM eclipse-temurin:25

WORKDIR /root

COPY ./target/*.jar /root/discoveryservice.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "discoveryservice.jar"]
