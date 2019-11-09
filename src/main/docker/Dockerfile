FROM java:8

VOLUME /lbs-server
EXPOSE 1111
ADD *.jar lbsServer-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/lbsServer-1.0.jar"]