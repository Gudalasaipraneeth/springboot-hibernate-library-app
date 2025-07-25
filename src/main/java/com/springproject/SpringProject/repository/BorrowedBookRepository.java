package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.BorrowedBook;
import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowedBookRepository extends JpaRepository<BorrowedBook, Integer> {
    // Find borrowed books by borrow list
    List<BorrowedBook> findByBorrowList(BorrowList borrowList);
    
    // Find borrowed books by book
    List<BorrowedBook> findByBook(Book book);
    
    // Find borrowed books by borrow list ID
    List<BorrowedBook> findByBorrowListId(int borrowListId);
}
