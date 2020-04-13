#模板，后续根据需要进行修改
FROM java:8
VOLUME /tmp
ADD ../target/zl_synchronize-0.0.1-SNAPSHOT.jar app.jar
RUN bash -c 'touch /app.jar'
EXPOSE 8080
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]