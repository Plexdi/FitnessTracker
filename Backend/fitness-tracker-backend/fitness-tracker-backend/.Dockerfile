FROM openjdk:17-jdk-slim

WORKDIR /app

# Copy Maven wrapper files
COPY mvnw .
COPY .mvn .mvn

# Set execute permissions for Maven wrapper
RUN chmod +x mvnw

# Copy the entire project into the container
COPY . .

# Build the project and create the WAR file
RUN ./mvnw clean package -DskipTests

# Copy the built WAR file to the container
COPY target/*.war app.war

CMD ["java", "-jar", "app.war"]
