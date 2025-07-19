package com.springproject.SpringProject.models;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Entity(name="BORROW_LIST")
public class BorrowList {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @ManyToOne
    @JoinColumn(name="member_id")
    private Member member;

//    @ManyToMany
//    @JoinTable(
//            joinColumns = @JoinColumn(name = "cart_id"),
//            inverseJoinColumns = @JoinColumn(name = "book_id")
//    )
//    private List<Book> books;


    public BorrowList() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public Member getMember() {
        return member;
    }

    public void setMember(Member member) {
        this.member = member;
    }

//    public List<Book> getBooks() {
//        return books;
//    }

//    public List<Book> getBooksByUser(int customer_id ) {
//        List<Book> userBooks = new ArrayList<Book>();
//        for (Book book : books) {
//            if (book.getCustomer().getId() == customer_id) {
//                userBooks.add(book);
//            }
//        }
//        return userBooks;
//    }

//    public void setBooks(List<Book> books) {
//        this.books = books;
//    }

//    public void addBook(Book book) {
//        books.add(book);
//    }
//
//    public void removeBook(Book book) {
//        books.remove(book);
//    }
}
