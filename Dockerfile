# Ubuntu 베이스 이미지 선택
FROM ubuntu:latest

# 작업 디렉터리 설정
WORKDIR /app

# 필수 패키지 설치 및 OpenJDK 설치
RUN apt-get update && apt-get install -y \
    openjdk-21-jdk \
    curl \
    git \
    unzip
    #findutils

# 호스트의 Gradle wrapper와 소스 코드를 이미지로 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# 실행 권한 부여 및 줄 끝 변환
RUN chmod +x ./gradlew
RUN sed -i 's/\r$//' ./gradlew

# 빌드 실행
RUN ./gradlew build -x test

# 애플리케이션 실행 준비
CMD ["java", "-jar", "build/libs/live-tino-0.0.1-SNAPSHOT.jar"]


## 1. 베이스 이미지 설정
#FROM openjdk:21
#
## 2. 작업 디렉토리 생성
#WORKDIR /app
#
## 3. 빌드된 JAR 파일을 Docker 이미지에 복사
#COPY build/libs/live-tino-0.0.1-SNAPSHOT.jar /app/live-tino-0.0.1-SNAPSHOT.jar
#
## 4. 애플리케이션 실행
#CMD ["java", "-jar", "/app/live-tino-0.0.1-SNAPSHOT.jar"]
#
## 5. 외부에서 접속할 포트 설정
#EXPOSE 8080
