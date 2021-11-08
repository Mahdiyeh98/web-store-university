FROM openjdk:8
EXPOSE 80
COPY . /app
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app/target/bookstore.jar"]