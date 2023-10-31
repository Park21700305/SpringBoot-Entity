# 🌱 SpringBoot Entity 연습
### Entity: 데이터베이스 테이블을 자바 클래스로 매핑한 것! 스프링 부트와 JPA를 사용하면 다양한 관계 매핑을 쉽게 구현할 수 있음

# 📚 Entity 관계 연습 using @ManyToOne & @OneToMany
### @ManyToOne: 여러 엔티티가 같은 엔티티를 참조할 때 사용됨 e.g 여러 개의 Book 엔티티가 하나의 User 엔티티를 참조하는 관계에서 Book 측에 @ManyToOne을 사용함

### @OneToMany: 하나의 엔티티가 여러 개의 엔티티를 참조할 때 사용됨 위의 예에서 User 측에 @OneToMany를 사용하여 여러 User를 참조할 수 있게 함

### 🛠 수정사항1: 순환참조 해결
순환참조는 두 엔티티가 서로를 참조하는 문제를 말함. 이러한 문제는 직렬화나 무한 루프 등의 문제를 발생시킬 수 있기 때문에 해결이 필요함 본 연습에서는 이러한 순환참조 문제를 @JsonBackReference와 @JsonManagedReference를 통해 해결하였음

<p align="center">
  <img src="https://github.com/Park21700305/SpringBoot-Entity/assets/93187535/ea0ecc80-8799-413d-99ce-ad0e5b143f86" alt="스크린샷1" width="299">
  <img src="https://github.com/Park21700305/SpringBoot-Entity/assets/93187535/eb25de27-3519-4a83-8dfc-e48e18082fe8" alt="스크린샷2" width="932">
</p>
