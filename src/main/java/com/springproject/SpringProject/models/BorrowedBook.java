package com.springproject.SpringProject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name="BORROWED_BOOK")
public class BorrowedBook {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="borrow_list_id")
    private BorrowList borrowList;

    @ManyToOne
    @JoinTable(name="book_id")
    private Book book;


    public BorrowedBook() {
        book = null;
    }
    public BorrowedBook(BorrowList borrowList, Book book) {
        this.borrowList=borrowList;
        this.book = book;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public BorrowList getBorrowList() {
        return borrowList;
    }

    public void setBorrowList(BorrowList borrowList) {
        this.borrowList = borrowList;
    }
    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }
}
