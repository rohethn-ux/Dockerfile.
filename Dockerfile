FROM eclipse-temurin:17-jdk-jammy
WORKDIR /app
COPY . .
RUN javac Main.java
CMD ["java", "Main"]