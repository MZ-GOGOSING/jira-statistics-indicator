# jira-statistics-indicator

## 개발환경 구성

### Build
- OpenJDK 11 필요

```
./gradlew clean build
```

### [Docker](https://www.docker.com/get-started) Install

### MariaDB Install

* docker-compose 실행

```
docker-compose up -d
```

### MariaDB Create Database

```
docker exec -it mariadb10.6 bash
mysql --protocol=tcp -hlocalhost -P3306 -uroot -proot
mysql> CREATE DATABASE statistics DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;
```

Local MariaDB 접속 정보

* host / port: `localhost:4306`
* username: `root`
* password: `root`

### Data Migration (flyway)

최초로 마이그레이션을 실행할 때는 전체 데이터베이스를 초기화 할 수 있습니다.

```
./flyway-migration.sh
```

이후부터는 `flywayMigrate` 만 실행하여 테스트 데이터는 남겨두고 계속 DB 스키마만 변경할 수 있습니다.

```
./gradlew flywayMigrate
```

특정 도메인에 대해 스키마 변경을 진행하기 위해서는 모메인 모듈 명을 표기하여 실행합니다.

```
./gradlew statistics-jpa:flywayMigrate
```

### IntegrationTest
사용하는 IDE가 IntelliJ일 경우 테스트를 수행하기 전, 아래의 설정으로 변경하여 주세요.
- Preferences >  Build, Execution, Deployment > Build Tools > Gradle
  - Gradle projects
    - Build and run using : Gradle 에서 IntelliJ IDEA 으로
    - Run tests using : Gradle 에서 IntelliJ IDEA 으로
```
./gradlew webapp-config:integrationTest
```

### Run the Application

```
webapp-config/src/main/java/kr/co/mz/jira/ApplicationInitializr.java
```

### Available Profiles
- 로컬 : local
- 개발 : dev
- 스테이징 : stage
- 운영 : prod

### Environment Variables
해당 어플리케이션은 build 결과물인 jar 를 통한, 로컬 독립 실행을 목적으로 개발되었습니다.
따라서, 어플리케이션 실행 시 아래와 같은 Environment Variables 를 지정해 주어야 어플리케이션 기동이 가능합니다.
- 아래의 설정을 만족하는 Environment Variables 입력 필요
  - jira-api/src/main/resources/application-jira-api.yml

```
jira.client.credential.username=YOUR_JIRA_ID;jira.client.credential.password=YOUR_JIRA_PASSWORD;jira.client.credential.jiraUrl=JIRA_URI;
```

### API Documentation

API 명세는 어플리케이션 실행 후, 아래의 경로를 통하여 확인할 수 있습니다.

```
http://localhost/swagger-ui/index.html
```