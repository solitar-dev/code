FROM bellsoft/liberica-native-image-kit-container:jdk-25-nik-25-musl AS builder
WORKDIR /build

COPY apps/backend .
RUN ./gradlew nativeCompile


FROM bellsoft/liberica-runtime-container:jdk-24-musl
WORKDIR /app

COPY --from=builder /build/build/native/nativeCompile/solitar server
ENTRYPOINT ["/app/server"]
