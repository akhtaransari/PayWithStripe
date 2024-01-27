# Use an official OpenJDK runtime as a parent image
FROM openjdk:17-jdk

# Define an argument for the JAR file
ARG JAR_FILE=target/*.jar

# Copy the JAR file from the build context to the container
COPY ${JAR_FILE} app.jar

# Expose port 8080 for the application
EXPOSE 8080

# Set the entry point for the container to run the JAR file
ENTRYPOINT ["java", "-jar", "app.jar"]