FROM java:8
COPY target/*.jar app.jar
CMD ["java", "-Dspring.profiles.active=k8s", "-jar", "app.jar"]
