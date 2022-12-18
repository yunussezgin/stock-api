FROM openjdk:11
ADD target/stock-api-0.0.1-SNAPSHOT.jar stock-api-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/stock-api-0.0.1-SNAPSHOT.jar"]
EXPOSE 8085