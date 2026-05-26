
FROM eclipse-temurin:25

WORKDIR /root

COPY ./target/*.jar /root/userservice.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "userservice.jar"]
