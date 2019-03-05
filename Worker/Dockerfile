FROM gradle:4.10.2
USER root
WORKDIR /app

COPY ./ ./
CMD ["./gradlew", "clean", "bootRun"]