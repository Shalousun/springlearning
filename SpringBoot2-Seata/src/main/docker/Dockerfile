# Start with a base image containing Java runtime. The default timezone is Shanghai.
FROM registry.cn-shanghai.aliyuncs.com/shalousun/alpine-oraclejdk8-perf4j:1.0.2

# Add Maintainer Info
MAINTAINER shalousun

# Make port 8080 available to the world outside this container
EXPOSE 8080

# Add a volume pointing to /tmp
VOLUME /tmp

# Add the application's jar to the container
ADD springboot2-seata.jar springboot2-seata.jar

RUN sh -c 'touch /springboot2-seata.jar'
# Set jvm
ENV JAVA_OPTS="-server -Xmx512m -Xms512m -Djava.awt.headless=true"
# Spring boot profiles
ENV PROFILES="default"
# Run the jar file
ENTRYPOINT [ "sh", "-c", "java $JAVA_OPTS -Djava.security.egd=file:/dev/./urandom -jar -Dspring.profiles.active=$PROFILES /springboot2-seata.jar" ]