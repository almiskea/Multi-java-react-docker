FROM gradle:4.10.2
EXPOSE 8080
USER root
WORKDIR /app

COPY ./ ./
CMD ["./gradlew", "clean", "bootRun"]

