package com.springproject.SpringProject.dao;

import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.BorrowedBook;
import com.springproject.SpringProject.models.Book;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Repository
public class borrowedBookDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public BorrowedBook addBorrowedBook(BorrowedBook borrowedBook) {
        this.sessionFactory.getCurrentSession().save(borrowedBook);
        return borrowedBook;
    }

    @Transactional
    public List<BorrowedBook> getBorrowedBooks() {
        return this.sessionFactory.getCurrentSession().createQuery("from BORROWED_BOOK ").list();
    }

    @Transactional
    public List<Book> getBookByBorrowListID(Integer borrowList_id) {
        String sql = "SELECT book_id FROM borrowed_book WHERE borrow_list_id = :borrow_list_id";
        List<Integer> bookIds = this.sessionFactory.getCurrentSession()
                .createNativeQuery(sql)
                .setParameter("borrow_list_id", borrowList_id)
                .list();

        sql = "SELECT * FROM book WHERE id IN (:book_ids)";
        return this.sessionFactory.getCurrentSession()
                .createNativeQuery(sql, Book.class)
                .setParameterList("book_ids", bookIds)
                .list();
    }

    @Transactional
    public void updateBorrowedBook(BorrowedBook borrowedBook) {
        this.sessionFactory.getCurrentSession().update(borrowedBook);
    }

    @Transactional
    public void deleteBorrowedBook(BorrowedBook borrowedBook) {
        this.sessionFactory.getCurrentSession().delete(borrowedBook);
    }
}
