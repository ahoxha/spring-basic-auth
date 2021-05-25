FROM adoptopenjdk:11-jre
LABEL author="Armend Hoxha" maintainer="Armend Hoxha"
RUN mkdir /opt/app
WORKDIR /opt/app
COPY /target/spring-basic-auth-0.0.1-SNAPSHOT.jar .
EXPOSE 8080
ENTRYPOINT ["java", "-jar","spring-basic-auth-0.0.1-SNAPSHOT.jar"]
