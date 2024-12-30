# Use a multi-stage build to first build the application, then create the runtime image
# Stage 1: Build the JAR file using Maven

# Base image for building the app
FROM maven:3.9.9-openjdk-17-slim AS build

# Set working directory for build stage
WORKDIR /app

# Copy pom.xml and src directory for Maven build
COPY pom.xml /app/
COPY src /app/src/

# Run Maven to build the JAR file
RUN mvn clean package -DskipTests

# Stage 2: Create the runtime image

# Base image for runtime (using openjdk as before)
FROM openjdk:17-jdk-slim

# Set working directory for the final image
WORKDIR /app

# Copy the built JAR file from the build stage to the final image
COPY --from=build /app/target/book-stream-0.0.1-SNAPSHOT.jar app.jar

# Copy the 'uploads' directory with the images into the container
COPY uploads /app/uploads

# Ensure write permissions for the uploads directory
RUN chmod -R 777 /app/uploads

# Expose the port the app runs on
EXPOSE 8082

# Set environment variables for database configuration
ENV DB_HOST=host.docker.internal
ENV DB_PORT=3306
ENV DB_NAME=book_stream
ENV DB_USER=root
ENV DB_PASSWORD=

# Command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
