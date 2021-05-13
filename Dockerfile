FROM java:8
MAINTAINER jinglv
ENV TZ "Asia/Shanghai"
ENV LANG C.UTF-8
VOLUME /tmp
ADD remodeling-0.0.1-SNAPSHOT.jar remodeling.jar
ENTRYPOINT ["java","-javaagent:/usr/local/jacocoagent.jar=includes=*,output=tcpserver,append=true,address=*,port=6301,destfile=/usr/local/jacoco.exec","-Djava.security.egd=file:/dev/./urandom","-jar","remodeling.jar"]
EXPOSE 8089