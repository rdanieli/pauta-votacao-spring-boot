FROM openjdk:8u171-alpine3.7

WORKDIR /usr/src/app

COPY . ./
COPY build.gradle ./
COPY gradle gradle
COPY gradlew ./

RUN chmod 777 gradlew

RUN ./gradlew clean build

CMD java ${JAVA_OPTS} -jar build/libs/*.jar