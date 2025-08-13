package com.example.k5_iot_springboot.controller;

import com.example.k5_iot_springboot.dto.C_Book.BookCreateRequestDto;
import com.example.k5_iot_springboot.dto.C_Book.BookResponseDto;
import com.example.k5_iot_springboot.dto.C_Book.BookUpdateRequestDto;
import com.example.k5_iot_springboot.dto.ResponseDto;
import com.example.k5_iot_springboot.entity.C_Category;
import com.example.k5_iot_springboot.service.C_BookService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
@RequiredArgsConstructor
public class C_BookController {
    private final C_BookService bookService;

    // 1. 기본 CRUD
    // 1) CREATE - 책 생성
    @PostMapping
    public ResponseEntity<ResponseDto<BookResponseDto>> createBook(
            @RequestBody BookCreateRequestDto dto
    ) {
        ResponseDto<BookResponseDto> result = bookService.createBook(dto);
        return ResponseEntity.ok(result);
//        return ResponseEntity.created(location).body(result);
    }

    // 2) READ - 전체 책 조회
    @GetMapping
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getAllBooks() {
        ResponseDto<List<BookResponseDto>> result = bookService.getAllBooks();
        return ResponseEntity.ok(result);
    }

    // 3) READ - 단건 책 조회 (특정 ID)
    @GetMapping("/{id}")
    public ResponseEntity<ResponseDto<BookResponseDto>> getBookById(@PathVariable Long id) {
        ResponseDto<BookResponseDto> result = bookService.getBookById(id);
        return ResponseEntity.ok(result);
    }

    // 4) UPDATE - 책 수정
    @PutMapping("/{id}")
    public ResponseEntity<ResponseDto<BookResponseDto>> updateBook(
            @PathVariable Long id,
            @RequestBody BookUpdateRequestDto dto
    ) {
        ResponseDto<BookResponseDto> result = bookService.updateBook(id, dto);
        return ResponseEntity.ok(result);
    }

    // 5) DELETE - 책 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<ResponseDto<Void>> deleteBook(@PathVariable Long id) {
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }

    // 2. 검색 & 필터링 (@RequestParam)
    // : GET 메서드

    // 1) 제목에 특정 단어가 포함된 책 조회
    @GetMapping("/search/title")
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBooksByTitleContaining(
            @RequestParam String keyword
            // 경로값에 ? 이후의 데이터를 키-값 쌍으로 추출되는 값 (?키=값)
            // >> 항상 문자열로 반환 (숫자형은 int, long으로 자동 변환)

            // cf) 숫자로 변환할 수 없는 데이터 전달 시 400 Bad Request 발생
    ) {
        ResponseDto<List<BookResponseDto>> books = bookService.getBookByTitleContaining(keyword);
        return ResponseEntity
                .status(books.getMessage().equals("SUCCESS") ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(books);
    }

    // 2) 카테고리별 책 조회
    @GetMapping("/category/{category}") // "/category/ESSAY"
    public ResponseEntity<ResponseDto<List<BookResponseDto>>> getBookByCategory(
            @PathVariable C_Category category
            ) {
        ResponseDto<List<BookResponseDto>> books = bookService.getBookByCategory(category);
        return ResponseEntity
                .status(books.getMessage().equals("SUCCESS") ? HttpStatus.OK : HttpStatus.BAD_REQUEST)
                .body(books);
    }






}