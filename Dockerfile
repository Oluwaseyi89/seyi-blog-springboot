# Use an official OpenJDK 17 image as the base
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory
WORKDIR /app

# Copy Gradle wrapper and build files
COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle

# Copy source code
COPY src ./src
COPY .env ./
COPY src/main/resources/application.properties ./src/main/resources/application.properties

# Grant execute permission to gradlew
RUN chmod +x gradlew

# Build the application
RUN ./gradlew build --no-daemon

# Use a smaller JRE base image for running the app
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the built jar from the build stage
COPY --from=0 /app/build/libs/*.jar app.jar
COPY .env ./

# Expose port 8080
EXPOSE 8080

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
