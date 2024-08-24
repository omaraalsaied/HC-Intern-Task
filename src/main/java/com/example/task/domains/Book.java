package com.example.task.domains;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name = "books")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String isbn;

    @ManyToOne
    @JsonIgnoreProperties("books")
    @JoinColumn(name = "author_id",  nullable = false)
    private Author author;

    @OneToMany(mappedBy = "book")
    @JsonIgnoreProperties("book")
    private List<BorrowHistory> borrowHistories;


}
