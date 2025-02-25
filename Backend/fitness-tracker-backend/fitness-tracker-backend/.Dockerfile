# Use a base image with Maven and Java pre-installed
FROM maven:3.8.6-openjdk-18 AS builder

# Set the working directory
WORKDIR /app

# Copy the Maven Wrapper and project files
COPY .mvn/ .mvn
COPY mvnw .
COPY pom.xml .
COPY src src

# Make the mvnw script executable
RUN chmod +x mvnw

# Run Maven build (skipping tests)
RUN ./mvnw clean package -DskipTests

# Final stage with JRE
FROM openjdk:18-jdk-alpine
COPY --from=builder /app/target/*.jar /app.jar
ENTRYPOINT ["java","-jar","/app.jar"]