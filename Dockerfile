# Linux with OpenJDK JRE
FROM openjdk:11.0.9.1

# Copy artifact
COPY build/libs/*.jar /app/app.jar

# Configure workdir
WORKDIR /app

# Start app
CMD java -jar /app/app.jar

# Expose port 8080
EXPOSE 8080
