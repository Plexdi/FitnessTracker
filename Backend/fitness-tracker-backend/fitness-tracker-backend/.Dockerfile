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