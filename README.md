# Algorithm Review - MSA 기반 알고리즘 문제 리뷰 사이트

알고리즘 문제 풀이 후 리뷰를 작성해보세요!

<br>

## 화면 설명

**메인 화면**|**방명록 화면**
:-----:|:-----:
<img width="1280" alt="web1" src="https://user-images.githubusercontent.com/89020004/209526227-9af8cf67-153b-4993-8bf7-0e53df2a3d76.png">|<img width="1280" alt="web2" src="https://user-images.githubusercontent.com/89020004/209526234-13b6de76-70df-484f-aba8-29d1d4263f5c.png">
**문제 등록 화면**|**문제 상세 화면**
<img width="1280" alt="web3" src="https://user-images.githubusercontent.com/89020004/209526239-5f03eba0-000c-4645-abf8-7a52524d270c.png">|<img width="1280" alt="web4" src="https://user-images.githubusercontent.com/89020004/209526252-7f78f330-6f8b-4394-ab5c-031bf38b8993.png">
**리뷰 추가한 화면**|**리뷰 수정 화면**
<img width="1280" alt="web5" src="https://user-images.githubusercontent.com/89020004/209526264-89258413-7e54-4555-9391-cbaaf42daae8.png">|<img width="1280" alt="web6" src="https://user-images.githubusercontent.com/89020004/209526271-259ceaa7-ce08-4a5b-8988-271eba0f4ece.png">

## 시스템 아키텍처
![image](https://user-images.githubusercontent.com/89020004/208301686-e0e8151d-6f45-4669-a121-e312fb6d8d30.png)

## 사용 기술 목록

    - frontend
        - React.js
            - Styled Component
            - Ant Design
            - Bootstrap
            - Toast UI Editor
    - backend
        - Spring Boot
            - Spring Data JPA
            - QueryDSL
        - Spring Cloud
            - Spring Cloud Eureka Server
            - Spring Cloud Api gateway
            - Spring Cloud Config Server
        - MySQL
    - DevOps
        - NginX
        - Zipkin
        - Prometheus
        - Grafana
        - AlertManager (Slack)
        - Kafka
        - Zookeeper
        - Docker
        - Jib
    - etc
        - Postman
        - Git
        - Github

<br>

```
<접속 가능한 포트들>

- http://localhost → 웹페이지
- http://localhost:8761 → 스프링 유레카 디스커버리 서버
- http://localhost:9411 → 집킨 (분산 추적)
- http://localhost:3000 → 그라파나 (모니터링 시각화)
- http://localhost:9090 → 프로메테우스 (모니터링)
```

## 시나리오 설명

### 시나리오 1
-   docker stats를 통해 메모리 점유율 체크
-   메모리 사용률 20% 이하 컨테이너 -> 자원 할당
-   메모리 사용률 80% 이상 컨테이너 -> 자원 회수

### 시나리오 2
-   docker ps -a 를 통해 컨테이너 상태 확인
-   frontend 컨테이너가 아닌 컨테이너가 exited일 경우 restart

### 시나리오 3
-   Api gateway의 로그를 확인
-   통신 중 500번 대의 코드 확인시 exited된 컨테이너가 있는지 더 짧은 주기로 확인
-   exited 된 frontend가 아닌 컨테이너가 있다면 restart

<br>

| 조욱희 | 이정권 | 김승진 |
| --- | --- | --- |
| 프론트엔드, 시나리오1 | 프론트엔드, 시나리오2 | 백엔드, 데브옵스, 시나리오3 |
