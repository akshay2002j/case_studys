package com.example.user_sign_up.repo;

import com.example.user_sign_up.entity.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BookRepository extends JpaRepository<Book, String> {
}
