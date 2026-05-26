FROM eclipse-temurin:25

WORKDIR /root

COPY ./target/*.jar /root/productservice.jar

CMD ["java", "-Dspring.profiles.active=docker", "-jar", "productservice.jar"]