FROM maven:3.8-openjdk-17 AS build
RUN mkdir -p /workspace
WORKDIR /workspace
COPY pom.xml /workspace
COPY src /workspace/src
RUN mvn -f pom.xml clean package

FROM openjdk:17.0.2
COPY --from=build /workspace/target/FinalProject*.jar FinalProject*.jar
ENTRYPOINT ["java","-jar","FinalProject*.jar"]