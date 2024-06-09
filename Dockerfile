# Stage 1: Build the application
FROM eclipse-temurin:21-jdk as build

RUN apt-get update && apt-get install -y maven

# Set the working directory
WORKDIR /app

# Copy the Maven project files
COPY pom.xml .
COPY src src

# Package the application
RUN mvn clean package -DskipTests

# Stage 2: Run the application
FROM eclipse-temurin:21-jre

# Set the working directory
WORKDIR /app

# Copy the built application from the build stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
