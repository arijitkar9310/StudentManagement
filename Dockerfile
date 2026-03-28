# Stage 1: Build
FROM maven:3.9.6-eclipse-temurin-21 AS build
COPY . .
# We add the extra flag here to ensure Maven handles the preview features
RUN mvn clean package -DskipTests

# Stage 2: Run
FROM eclipse-temurin:21-jre-jammy
COPY --from=build /target/StudentManagement-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
# Crucial: The runtime also needs the enable-preview flag
ENTRYPOINT ["java", "--enable-preview", "-jar", "app.jar"]