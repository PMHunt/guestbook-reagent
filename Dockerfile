FROM openjdk:8-alpine

COPY target/uberjar/guestbook-reagent.jar /guestbook-reagent/app.jar

EXPOSE 3000

CMD ["java", "-jar", "/guestbook-reagent/app.jar"]
