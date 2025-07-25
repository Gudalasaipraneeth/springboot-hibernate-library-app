package com.springproject.SpringProject.services;

import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.repository.BorrowListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class borrowListService {
    @Autowired
    private BorrowListRepository borrowListRepository;

    public BorrowList addBorrowList(BorrowList borrowList) {
        return borrowListRepository.save(borrowList);
    }

    public List<BorrowList> getBorrowLists() {
        return borrowListRepository.findAll();
    }

    public void updateBorrowList(BorrowList borrowList) {
        borrowListRepository.save(borrowList);
    }

    public void deleteBorrowList(BorrowList borrowList) {
        borrowListRepository.delete(borrowList);
    }

    public BorrowList getBorrowList(int id) {
        return borrowListRepository.findById(id).orElse(null);
    }
}
