<h1  align="center">📷 봄내 사진예술연구회 온라인 사진전 Backend-Server 🎟</h1>

<p align="center"> 사진동아리 온라인 사진전을 위한 서버 </p>



&nbsp;

## 목차

* [프로젝트에 대하여](#프로젝트에-대하여)
  * [왜 이프로젝트를 기획했는가?](#왜-이-프로젝트를-기획했었는가)
* [기술 스택](#기술-스택)
*  [Getting Started](#getting-started)
* [Details](#details)
  * [Dependencies](#Dependencies)
  * [Description&Architecture](#architecture)
  * [Demo](#demo)
* [결과](#결과)
* [Contributers](#Contributers)
* [문의](#문의)&nbsp;

&nbsp;&nbsp;

## 프로젝트에 대하여


### 왜 이 프로젝트를 기획했었는가?

최근 **코로나19** 때문에 많은 대학교 동아리들이 정상적인 동아리 활동을 진행할 수 없었습니다. 제가 다니고 있었던 사진동아리도 역시 똑같이 정상적인 동아리 활동을 할 수 없었습니다.
저희 사진동아리는 주기적으로 매년마다 학교 전시장을 빌려 전시회를 진행했으나, 이런 전시장도 물론이거니와 비대면으로 변화해버린 학교 상황에 정상적인 전시활동을 하기에는 부적합했습니다. 
그래서 코로나라는 이 상황과 학교 내부에서 전시를 할 수 없는 상황에서 가장 최선책인 프로젝트인  온라인 전시회를 기획하였고, 저는 그 온라인 전시회에서 서버를 담당하게 되었습니다. 


&nbsp;

### 기술 스택

- Java 8
- Spring Boot 2.3.3.RELEASE
- Spring Security
- Spring JPA
- Maria DB (RDS)
- AWS EC2
- AWS S3, CloudFront
- Gradle
- JWT
- NGINX

 &nbsp;

## Getting Started

 &nbsp;

## Details

### Dependencies

```
plugins {
	id 'org.springframework.boot' version '2.3.3.RELEASE'
	id 'io.spring.dependency-management' version '1.0.10.RELEASE'
	id 'java'
}

group = 'com.ventulus95'
version = '0.0.1-SNAPSHOT'
sourceCompatibility = '1.8'

configurations {
	compileOnly {
		extendsFrom annotationProcessor
	}
}

repositories {
	mavenCentral()
}

dependencies {
	implementation 'org.springframework.boot:spring-boot-starter-data-jpa'
	implementation 'org.springframework.boot:spring-boot-starter-security'
	implementation 'org.springframework.boot:spring-boot-starter-web'
	compileOnly 'org.projectlombok:lombok'
	developmentOnly 'org.springframework.boot:spring-boot-devtools'
	runtimeOnly 'com.h2database:h2'
	annotationProcessor 'org.projectlombok:lombok'
	testImplementation('org.springframework.boot:spring-boot-starter-test') {
		exclude group: 'org.junit.vintage', module: 'junit-vintage-engine'
	}
	testImplementation 'org.springframework.security:spring-security-test'
	compile group: 'org.springframework.boot', name: 'spring-boot-starter-oauth2-client', version: '2.2.5.RELEASE'
	compile group: 'io.jsonwebtoken', name: 'jjwt', version: '0.9.1'
	compile 'org.springframework.boot:spring-boot-starter-validation'
	implementation group: 'org.springframework.cloud', name: 'spring-cloud-starter-aws', version: '2.2.1.RELEASE'
	compile("org.mariadb.jdbc:mariadb-java-client")
	compile group: 'com.drewnoakes', name: 'metadata-extractor', version: '2.11.0'
}

test {
	useJUnitPlatform()
}

```

 &nbsp;

### Description & Architecture

![봄내사진예술연구회온라인전시회](https://user-images.githubusercontent.com/17822723/100208791-74e33780-2f4c-11eb-9961-ea7e872df788.png)

**Spring Boot**가 백엔드 API서버를 담당하고 있습니다. React를 통해 프론트엔드를 담당하고 있습니다. 이 둘간의 서버 교환을 통해서 전시회 서버가 운용되고 있습니다. 

내부적으로 두개의 관점으로 서버가 운용가능한데, 1. 관리자 입장과, 2. 유저입장에서 접근이 가능합니다. 1. 관리자 입장에서는 내부 전시용 사진들을 올립니다. 사진 파일들을 사이즈가 크고 EC2 내부 Stroage에 직접 저장하는 것보다 AWS S3를 활용하여, S3에 정적 이미지 파일들을 올리고 이 이미지 파일들을 AWS CloudFront에 Caching하여 실제 처음 접속하는 경우를 제외한 속도를 높이기 위해 Cache 서버를 적용하였습니다. 

2, 외부유저들은 대부분 핸드폰으로 접속할 가능성이 크고, Frontend를 React를 통해서 접속하기 때문에 로그인을 JWT를 통한 Token 방식의 로그인 방식을 채용했습니다. **Spring Security**와 JWT을 활용한 서버 인증과  Oauth2.0을 통한 내부 로그인시 Google과 카카오톡을 활용한 소셜로그인 체계를 구축하였습니다.  Spring Security 활용한 JWT를 기반으로 하는  Oauth2.0 로그인은 다음 사이트를 참고하여 개발하였습니다. 

https://www.callicoder.com/spring-boot-security-oauth2-social-login-part-1/

외부유저들은 방명록, 사진관람과 같은 활동을 할 수 있습니다.  

제가 직접 프론트엔드의 배포작업도 진행하였고, 직접 CI툴을 활용한  React 빌드 과정을 간소화했습니다. EC2 프리티어를 활용한 React build과정은 시간이 너무 오래 걸리는 작업이기때문에, **Github Actions** 을 활용해서 git commit후  build 작업을 처리후 AWS S3에 그 Build 파일을 올라가게 됩니다. EC2에 직접  AWS CLI를 통해  S3 와 Sync 작업을 통해 Build파일을 일치화합니다. 그 이후에 NGINX를 통해 배포합니다. 

&nbsp;

### Demo

<br>



## 결과

12/1월 이후 안정적 서비스가 가능하였는지 적도록 하겠습니다. 



## Contributers

- 개발
  - BE/DEPLOY: 이창섭 ([ventulus95](https://github.com/ventulus95)) 
  - FE: 김인권([timevoltex](https://github.com/timevoltex))



## 문의

- 코로나 때문에 전시회를 열지 못하는 사진 전시회가 필요한 대학 사진 동아리 혹은 소규모 사진 동호회분들은 연락주시면 친절하게 답변 드리겠습니다.
- 문의 이메일: ventulus95@gmail.com