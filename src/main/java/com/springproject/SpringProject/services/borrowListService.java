package com.springproject.SpringProject.services;

import com.springproject.SpringProject.dao.borrowListDao;
import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Genre;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class borrowListService {
    @Autowired
    private borrowListDao borrowListDao;

    public BorrowList addBorrowList(BorrowList borrowList) {
        return borrowListDao.addBorrowList(borrowList);
    }

    public List<BorrowList> getBorrowLists() {
        return this.borrowListDao.getBorrowLists();
    }

    public void updateBorrowList(BorrowList borrowList) {
        borrowListDao.updateBorrowList(borrowList);
    }

    public void deleteBorrowList(BorrowList borrowList) {
        borrowListDao.deleteBorrowList(borrowList);
    }


}
