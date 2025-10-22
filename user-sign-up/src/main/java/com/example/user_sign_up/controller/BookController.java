package com.example.user_sign_up.controller;

import com.example.user_sign_up.dto.BookDto;
import com.example.user_sign_up.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController()
@RequestMapping("/api/book")
public class BookController {

    @Autowired
    public BookService bookService;

    @PostMapping("/")
    public ResponseEntity<?> registerBook(@RequestBody BookDto bookDto) {
        return new ResponseEntity<>(bookService.registerBook(bookDto), HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<?> getAllBooks(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size,
            @RequestParam(defaultValue = "id") String sortBy,
            @RequestParam(defaultValue = "asc") String sortDirection
    ){
        return new ResponseEntity<>(bookService.getBooks(page,size,sortBy,sortDirection), HttpStatus.OK);
    }
}
