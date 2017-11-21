Homework 2th
============

#### **목표 : Spring mvc 의 Flow 에 맞춰 Log 찍기**
이미 만들어진 게시판 기능을 사용해보고, 해당기능이 어느곳에서 동작하고 어느곳에서 애러를 뱉어 내는지 확인할 수 있도록 Log 를 남긴다.

## 방법
1. Local PC에서 App 을 동작시킨다.
2. 아래 주어진 조건이 동작할 수 있도록 기능을 사용한다.
3. 코드가 수행됐는지 확인할 수 있도록 Log 를 남긴다.
4. Log가 제대로 찍혔는지 확인할 수 있도록 App 이 수행된 이후부터 모든 내용을 log.txt 파일에 복사한다.
5. 소스코드와 log.txt 파일을 모두 commit -> push 하도록 한다.
6. 로그모듈은 logback 을 사용하며, lombok 의 @slf4j 를 활용한다.

## 로그 작성 조건 1: 게시물을 업데이트 해보기.

1. title이 "강원대 체육대회 11월 31일 입니다." 게시물을 올린다.
2. 올라간 위 게시물의 제목을 변경하는 로그를 작성한다. ("강원대 체육 대회 안합니다."로 변경 해보자.)
3. 아래와 예시와 같이 로그가 출력 되는지 확인한다. (아래는 로그의 예입니다. 형식은 자유롭게 하셔도 됩니다.)

- INFO : Kyeongsik Jason Kim is updating board...title: 강원대 체육 대회 11월 31일 대운동장 입니다. created: 2017-11-21 13:44:25.731
- INFO : Updated result : title: (일정변경) 강원대 체육 대회 안합니다. updated: Tue Nov 21 13:45:01 KST 2017

## 로그 작성 조건 2: 유효하지 않은 토큰으로 로그인 실패 해보기.
1. 유효하지 않은 토큰에 대해서 로그인이 실패하는 로그를 작성해본다.
2. 로그인 토큰을 유효하지 않은 String 으로 제출한다. 
3. 아래와 예시와 같이 로그가 출력 되는지 확인한다. (참고: 2번째, 3번째 줄의 로그는 이미 작성되어 있음. 즉 1번째 4번째 로그가 출력 되도록 작성할 것)

- INFO : Authenticating login token: EAACE(작성해야할 로그)
- INFO : DEV!! : EAACE (이미 작성되어 있는 로그)
- ERROR: Facebook Login fail [EAACE]:(생략)...web.client.HttpClientErrorException: 400 Bad Request (이미 작성되어 있는 로그)
- INFO : Authentication fail: token is invalid: EAACE (작성해야할 로그)

## 제출

## 주의

## 예제






