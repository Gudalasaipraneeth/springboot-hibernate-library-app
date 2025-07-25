package com.springproject.SpringProject.services;

import com.springproject.SpringProject.models.BorrowedBook;
import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.repository.BorrowedBookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class borrowedBookService {
    @Autowired
    private BorrowedBookRepository borrowedBookRepository;

    public BorrowedBook addBorrowedBook(BorrowedBook borrowedBook) {
        return borrowedBookRepository.save(borrowedBook);
    }

    public List<BorrowedBook> getBorrowedBooks() {
        return borrowedBookRepository.findAll();
    }

    public List<BorrowedBook> getBorrowedBooksByBorrowList(BorrowList borrowList) {
        return borrowedBookRepository.findByBorrowList(borrowList);
    }

    public List<BorrowedBook> getBorrowedBooksByBook(Book book) {
        return borrowedBookRepository.findByBook(book);
    }

    public void updateBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBookRepository.save(borrowedBook);
    }

    public void deleteBorrowedBook(BorrowedBook borrowedBook) {
        borrowedBookRepository.delete(borrowedBook);
    }

    public BorrowedBook getBorrowedBook(int id) {
        return borrowedBookRepository.findById(id).orElse(null);
    }
}
