FROM openjdk:17
ARG JAR_FILE=target/prestabanco-tingeso.jar
COPY ${JAR_FILE} prestabanco-tingeso.jar
ENTRYPOINT ["java","-jar","/prestabanco-tingeso.jar"]