FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
RUN mvn clean package -DskipTests

FROM openjdk:17.0.1-jdk-slim
WORKDIR /app
COPY --from=build /app/target/eCommerce-0.0.1-SNAPSHOT.war ./eCommerce.war
EXPOSE 80
ENTRYPOINT ["java", "-jar", "eCommerce.war"]