FROM java:8

VOLUME /lbs-server
ENV LANG en_US.UTF-8
ENV LANGUAGE en_US:en
ENV LC_ALL en_US.UTF-8
EXPOSE 1111
ADD *.jar lbsServer-1.0.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom -Dfile.encoding=utf-8","-jar","/lbsServer-1.0.jar"]