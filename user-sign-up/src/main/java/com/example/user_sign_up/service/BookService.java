package com.example.user_sign_up.service;

import com.example.user_sign_up.dto.BookDto;
import com.example.user_sign_up.entity.Book;
import com.example.user_sign_up.repo.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;



    public BookDto registerBook(BookDto bookDto) {
        Book book = new Book();
        BeanUtils.copyProperties(bookDto, book);
        bookRepository.save(book);
        return bookDto;
    }

    public BookDto getBookById(String id) {
        Book book = bookRepository.findById(id).orElseThrow(
                ()-> new RuntimeException("Book Not Found")
        );

        BookDto bookDto = new BookDto();
        BeanUtils.copyProperties(book, bookDto);
        return bookDto;
    }

    public Map<String, Object>  getBooks(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        Page<Book> pageBooks = bookRepository.findAll(pageable);

        List<BookDto> books = pageBooks.getContent()
                .stream()
                .map(book -> {
                    BookDto dto = new BookDto();
                    BeanUtils.copyProperties(book, dto, "bookId");
                    return dto;
                })
                .toList();
        Map<String, Object> response = new HashMap<>();
        response.put("data", books);
        response.put("totalRecords", pageBooks.getTotalElements());
        response.put("totalPages", pageBooks.getTotalPages());
        response.put("currentPage", page);
        return response;
    }
}
