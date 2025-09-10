//package org.example.o_lim.entity;
//
//import jakarta.persistence.*;
//import lombok.*;
//import org.example.o_lim.entity.base.BaseTimeEntity;
//
//@Entity // JPA 엔티티임을 선언 → DB 테이블과 매핑됨
//@Table(
//        name = "projects", // 실제 DB 테이블명 매핑
//        indexes = {
//                @Index(name = "idx_project_owner", columnList = "owner_id") // owner_id에 인덱스 생성 → 조회 성능 향상
//        }
//)
//@NoArgsConstructor(access = AccessLevel.PROTECTED) // JPA 프록시 생성을 위한 기본 생성자 (protected 권장)
//@AllArgsConstructor // 모든 필드를 인자로 받는 생성자 자동 생성
//@Getter // getter 메서드 자동 생성
//@Setter // setter 메서드 자동 생성
//public class Project extends BaseTimeEntity { // BaseTimeEntity → created_at, updated_at 상속
//
//    @Id // 기본 키(PK) 선언
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    // AUTO_INCREMENT 전략 사용 (MySQL, MariaDB와 매핑됨)
//    @Column(name = "id", nullable = false, updatable = false)
//    // DB 컬럼명 지정, not null, 업데이트 불가 (PK는 수정되면 안 됨)
//    private Long id;  // 프로젝트 고유 식별자
//
//    @ManyToOne(fetch = FetchType.LAZY, optional = false)
//    // 다대일 관계 (여러 프로젝트 → 한 명의 유저), 지연 로딩 방식
//    @JoinColumn(
//            name = "owner_id", // 실제 DB 컬럼명
//            nullable = false, // 반드시 존재해야 함
//            updatable = false, // 프로젝트 생성 후 변경 불가
//            foreignKey = @ForeignKey(name = "fk_project_owner") // FK 제약조건 이름 지정
//    )
//    private User owner;  // 프로젝트 소유자 (users 테이블과 조인됨)
//
//    @Column(name = "title", nullable = false, length = 150)
//    // title 컬럼, null 불가, 최대 길이 150자
//    private String title;  // 프로젝트 제목
//
//    @Column(name = "description", nullable = false, length = 255)
//    // description 컬럼, null 불가, 최대 길이 255자
//    private String description;  // 프로젝트 설명
//}
