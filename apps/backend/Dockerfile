FROM bellsoft/liberica-runtime-container:jdk-21-musl AS builder
WORKDIR /build

COPY apps/backend .
RUN ./gradlew bootJar


FROM bellsoft/liberica-runtime-container:jdk-21-musl 
WORKDIR /app

COPY --from=builder /build/build/libs/*.jar server.jar
ENTRYPOINT ["java", "-jar", "/app/server.jar"]
