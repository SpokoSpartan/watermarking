FROM adoptopenjdk/openjdk11
COPY ./target/watermark-api-0.0.1-SNAPSHOT.jar /usr/source/api.jar
WORKDIR /usr/source/
EXPOSE 8080
CMD java -Xms256m -Xmx256m -Xss256k -jar api.jar
