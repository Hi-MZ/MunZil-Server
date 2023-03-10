FROM openjdk:17.0.1-jdk-slim
COPY ./build/libs/*.jar app.jar
ARG JDBC_URL
ARG DB_USER
ARG DB_PWD
ARG JWT_SECRET_KEY
ARG REDIS_HOST
ARG REDIS_PORT
ARG REDIS_PASSWORD
ARG AWS_ACCESS_KEY
ARG AWS_SECRET_KEY
ARG S3_BUCKET
ARG AWS_REGION

ENV JDBC_URL=${JDBC_URL}
ENV DB_USER=${DB_USER}
ENV DB_PWD=${DB_PWD}
ENV JWT_SECRET_KEY=${JWT_SECRET_KEY}
ENV REDIS_HOST=${REDIS_HOST}
ENV REDIS_PORT=${REDIS_PORT}
ENV REDIS_PASSWORD=${REDIS_PASSWORD}
ENV AWS_ACCESS_KEY=${AWS_ACCESS_KEY}
ENV AWS_SECRET_KEY=${AWS_SECRET_KEY}
ENV S3_BUCKET=${S3_BUCKET}
ENV AWS_REGION=${AWS_REGION}

ENV TZ=Asia/Seoul

ENTRYPOINT ["java","-jar","-Dspring.datasource.url=${JDBC_URL}","-Dspring.datasource.username=${DB_USER}", "-Dspring.datasource.password=${DB_PWD}", "-Dspring.jwt.secret-key=${JWT_SECRET_KEY}", "-Dspring.redis.host=${REDIS_HOST}", "-Dspring.redis.port=${REDIS_PORT}", "-Dspring.redis.password=${REDIS_PASSWORD}", "-Dspring.cloud.aws.credentials.accessKey=${AWS_ACCESS_KEY}", "-Dspring.cloud.aws.credentials.secretKey=${AWS_SECRET_KEY}", "-Dspring.cloud.aws.s3.bucket=${S3_BUCKET}", "-Dspring.cloud.aws.region.static=${AWS_REGION}",  "/app.jar"]
