FROM gradle:8.7-jdk21 AS build
COPY --chown=gradle:gradle . /home/gradle/src
WORKDIR /home/gradle/src
RUN ./gradlew clean && ./gradlew build --no-daemon

#specifying the base image
FROM gradle:8.7-jdk21-alpine

#copy the jar
#ADD build/libs/*SNAPSHOT.jar app.jar

COPY --from=build /home/gradle/src/build/libs/*SNAPSHOT.jar app.jar

#command to execute when running a container
ENTRYPOINT ["sh", "-c", "java -jar app.jar"]
