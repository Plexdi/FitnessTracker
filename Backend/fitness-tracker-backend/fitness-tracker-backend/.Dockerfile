FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Maven Wrapper files
COPY mvnw .
COPY .mvn .mvn

# Fix line endings (optional but recommended)
RUN apt-get update && apt-get install -y dos2unix && dos2unix mvnw

# Set execute permissions
RUN chmod +x mvnw && ls -l mvnw

# Copy the rest of the project
COPY . .

# Build the project
RUN ./mvnw clean package -DskipTests

# Copy the built JAR file to the container
COPY target/*.jar app.jar

CMD ["java", "-jar", "app.jar"]
