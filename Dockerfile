# build stage build the jar with all our resources
FROM openjdk:16-jdk as build

VOLUME /tmp
WORKDIR /
ADD . .

RUN ./gradlew --debug clean test build
RUN cp ./build/libs/springboozle2-0.0.1-SNAPSHOT.jar /app.jar

# package stage
FROM openjdk:16-jdk-alpine
WORKDIR /
# copy only the built jar and nothing else
COPY --from=build /app.jar /

ENV VERSION=$VERSION
ENV JAVA_OPTS=-Dspring.profiles.active=production

EXPOSE 8080

ENTRYPOINT ["sh","-c","java -jar -Dspring.profiles.active=production /app.jar"]
