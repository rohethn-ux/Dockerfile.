FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
RUN javac -cp mysql-connector.jar Main.java
CMD ["java", "-cp", ".:mysql-connector.jar", "Main"]
