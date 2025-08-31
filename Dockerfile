FROM eclipse-temurin:21-jdk-jammy AS builder

WORKDIR /app
COPY build.gradle settings.gradle ./
COPY gradlew ./gradlew
COPY gradle ./gradle

RUN chmod +x ./gradlew
COPY src ./src
RUN ./gradlew build --no-daemon -x test

FROM eclipse-temurin:21-jre-jammy

WORKDIR /app
COPY --from=builder /app/build/libs/*.jar app.jar
EXPOSE 8080
RUN addgroup --system appgroup && adduser --system --ingroup appgroup appuser
USER appuser
ENTRYPOINT ["java", "-jar", "app.jar"]
