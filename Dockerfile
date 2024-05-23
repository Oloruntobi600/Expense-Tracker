FROM maven:3.8.5-openjdk-17 AS build

WORKDIR /usr/src/Expense-Tracker

COPY pom.xml .

COPY src ./src

RUN mvn clean package -DskipTests

FROM openjdk:17-jdk-slim

WORKDIR /Expense-Tracker

COPY --from=build /usr/src/Expense-Tracker/target/Expense-Tracker-0.0.1-SNAPSHOT.jar Expense-Tracker.jar

ENTRYPOINT ["java","-jar","Expense-Tracker.jar"]