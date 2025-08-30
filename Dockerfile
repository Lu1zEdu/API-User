FROM gradle:jdk21-alpine AS builder

WORKDIR /app
RUN adduser -h /home/rm557462 -s /bin/bash -D rm557462
RUN chown -R rm557462:rm557462 /app
USER rm557462
RUN chmod +x ./gradlew
COPY src ./src
RUN ./gradlew build --no-daemon

FROM eclipse-temurin:21-jre-jammy

ENV JAR_FILE=user-0.0.1-SNAPSHOT.jar
WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]