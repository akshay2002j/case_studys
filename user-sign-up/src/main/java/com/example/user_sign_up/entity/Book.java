package com.example.user_sign_up.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    String bookId;
    String bookName;
    String bookAuthor;
    Long bookPrice;
    String bookDescription;
}
