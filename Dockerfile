FROM java:8

ADD build/libs/checkout-1.0.0.jar app.jar
ENTRYPOINT ["java", "-Djava.security.edg=file:/dev/./urandom", "-jar", "app.jar"]
CMD ["--spring.profiles.active=dev"]
EXPOSE 8080