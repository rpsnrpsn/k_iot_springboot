//package com.example.k5_iot_springboot.domain; // ← 엔티티가 속할 패키지 (프로젝트 구조에 맞게 변경)
//
//// === import 구간 ===
//import jakarta.persistence.*;                   // JPA에서 제공하는 엔티티/매핑 애너테이션들
//import lombok.*;                                // Lombok으로 보일러플레이트 코드 제거
//import org.hibernate.annotations.CreationTimestamp; // INSERT 시 자동으로 생성시간 입력
//import org.hibernate.annotations.UpdateTimestamp;   // UPDATE 시 자동으로 수정시간 갱신
//
//import java.time.LocalDateTime;                 // MySQL DATETIME(6) ↔ Java LocalDateTime 매핑
//import java.util.ArrayList;                     // List 초기값 제공용
//import java.util.List;                          // 1:N 관계 필드 타입
//
//// === 엔티티 클래스 시작 ===
//@Entity                                         // 이 클래스가 JPA 엔티티임을 표시 (DB 테이블과 매핑됨)
//@Table(name = "projects")                       // 실제 매핑될 테이블 이름 지정
//@Getter                                         // 모든 필드에 대한 getter 자동 생성
//@Setter                                         // 모든 필드에 대한 setter 자동 생성
//@NoArgsConstructor                               // 기본 생성자 자동 생성 (JPA 프록시용 필수)
//@AllArgsConstructor                              // 모든 필드를 받는 생성자 생성
//@Builder                                        // 빌더 패턴 지원 (가독성↑)
//@ToString(exclude = {"tasks", "tags"})          // toString()에서 tasks, tags 제외 (무한루프 방지)
//public class Project {                          // projects 테이블과 매핑되는 엔티티 선언
//
//    @Id                                         // PK 지정
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    // MySQL AUTO_INCREMENT 전략 사용 (DB에서 값 자동 증가)
//    private Long id;                             // projects.id → BIGINT PK
//
//    @ManyToOne(fetch = FetchType.LAZY)          // 다:일 관계 (여러 프로젝트가 한 유저(owner)에 속함), 지연 로딩
//    @JoinColumn(                                // FK 컬럼 지정
//            name = "owner_id",                      // FK 컬럼명: projects.owner_id
//            nullable = false,                       // NOT NULL 제약조건
//            foreignKey = @ForeignKey(name = "fk_project_owner")
//            // FK 제약조건 이름 (DDL과 동일하게 설정)
//    )
//    private User owner;                          // 프로젝트 소유자 (users 테이블 참조)
//
//    @Column(nullable = false, length = 150)      // NOT NULL, VARCHAR(150)
//    private String title;                        // 프로젝트 제목
//
//    @Column(nullable = false, length = 255)      // NOT NULL, VARCHAR(255)
//    private String description;                  // 프로젝트 설명
//
//    @CreationTimestamp                           // INSERT 시 현재 시간이 자동 입력됨
//    @Column(name = "created_at", nullable = false, updatable = false)
//    // updatable=false → UPDATE 시 값이 변경되지 않음
//    private LocalDateTime createdAt;             // 생성일시 (DATETIME(6))
//
//    @UpdateTimestamp                             // UPDATE 시 현재 시간으로 자동 갱신됨
//    @Column(name = "updated_at", nullable = false) // NOT NULL
//    private LocalDateTime updatedAt;             // 수정일시 (DATETIME(6))
//
//    @OneToMany(                                  // 1:N 관계 매핑 (Project ↔ Task)
//            mappedBy = "project",                    // Task 엔티티의 project 필드가 FK 주인
//            cascade = CascadeType.ALL,               // 부모(Project) 저장/삭제 시 자식(Task)도 함께 처리
//            orphanRemoval = true                     // 부모 컬렉션에서 제거된 자식은 DB에서도 삭제
//    )
//    @Builder.Default                             // 빌더 사용 시에도 null 대신 빈 리스트 보장
//    private List<Task> tasks = new ArrayList<>();// 프로젝트가 가진 여러 개의 Task 목록
//
//    @OneToMany(                                  // 1:N 관계 매핑 (Project ↔ Tag)
//            mappedBy = "project",                    // Tag 엔티티의 project 필드가 FK 주인
//            cascade = CascadeType.ALL,               // 저장/삭제 전파
//            orphanRemoval = true                     // 고아 객체 삭제 (컬렉션에서 제거되면 DB도 삭제)
//    )
//    @Builder.Default                             // 빌더 사용 시에도 null 대신 빈 리스트 보장
//    private List<Tag> tags = new ArrayList<>();  // 프로젝트가 가진 여러 개의 Tag 목록
//
//}                                                // 엔티티 클래스 끝
