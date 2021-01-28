  
FROM adoptopenjdk/openjdk11

WORKDIR /opt/album-list

COPY /target/album-list-api.jar album-list-api.jar

SHELL ["/bin/sh", "-c"]

EXPOSE 5005
EXPOSE 8080

CMD java -jar album-list-api.jar 