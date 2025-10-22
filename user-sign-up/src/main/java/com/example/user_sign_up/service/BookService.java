package com.example.user_sign_up.service;

import com.example.user_sign_up.dto.BookDto;
import com.example.user_sign_up.entity.Book;
import com.example.user_sign_up.repo.BookRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

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

    public List<BookDto> getBooks(int page, int size, String sortBy, String sortDirection) {
        Sort.Direction direction = Sort.Direction.fromString(sortDirection);
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sortBy));
        List<Book> books = bookRepository.findAll(pageable).getContent();
        return books.stream().map(
                book -> {
                    BookDto bookDto = new BookDto();
                    BeanUtils.copyProperties(book, bookDto);
                    return bookDto;
                }
        ).toList();
    }
}
