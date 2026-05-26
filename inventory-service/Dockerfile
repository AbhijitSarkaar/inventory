FROM eclipse-temurin:25

WORKDIR /root

COPY ./target/*.jar /root/inventoryservice.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "inventoryservice.jar"]