package com.springproject.SpringProject.dao;

import java.util.List;

import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Genre;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class borrowListDao {
    @Autowired
    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sf) {
        this.sessionFactory = sf;
    }

    @Transactional
    public BorrowList addBorrowList(BorrowList borrowList) {
        this.sessionFactory.getCurrentSession().save(borrowList);
        return borrowList;
    }

    @Transactional
    public List<BorrowList> getBorrowLists() {
        return this.sessionFactory.getCurrentSession().createQuery("from BORROW_LIST").list();
    }

//    @Transactional
//    public List<Cart> getCartsByCustomerID(Integer customer_id) {
//        String hql = "from CART where CART.customer_id = :customer_id";
//        return this.sessionFactory.getCurrentSession()
//                .createQuery(hql, Cart.class)
//                .setParameter("customer_id", customer_id)
//                .list();
//    }

    @Transactional
    public void updateBorrowList(BorrowList borrowList) {
        this.sessionFactory.getCurrentSession().update(borrowList);
    }

    @Transactional
    public void deleteBorrowList(BorrowList borrowList) {
        this.sessionFactory.getCurrentSession().delete(borrowList);
    }
}
