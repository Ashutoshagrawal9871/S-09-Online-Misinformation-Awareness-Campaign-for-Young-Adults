FROM maven:3.9.9-eclipse-temurin-17 AS build
WORKDIR /workspace
COPY pom.xml .
COPY src src
COPY docs docs
COPY report report
COPY presentation presentation
COPY assets assets
COPY tests tests
COPY CHANGELOG.md PROJECT_PLAN.md ./
RUN mvn -B -DskipTests package

FROM eclipse-temurin:17-jre
WORKDIR /app
COPY --from=build /workspace/target/*.jar /app/app.jar
EXPOSE 8080
ENV JAVA_TOOL_OPTIONS="-XX:MaxRAMPercentage=70.0 -XX:+UseSerialGC -Xss512k"
ENTRYPOINT ["sh", "-c", "java -jar /app/app.jar"]
