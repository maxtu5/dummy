FROM maven:3.8.3-openjdk-17

WORKDIR /app
COPY pom.xml ./
COPY src/ ./src/
RUN mvn package -DskipTests

ENTRYPOINT ["java","-jar","/app/target/dummy-0.0.7-SNAPSHOT.jar"]