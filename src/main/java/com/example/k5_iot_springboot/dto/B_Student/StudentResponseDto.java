package com.example.k5_iot_springboot.dto.B_Student;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

@Getter // 필수
@Builder
@AllArgsConstructor
public class StudentResponseDto {
    private Long id;
    private String name;
}
