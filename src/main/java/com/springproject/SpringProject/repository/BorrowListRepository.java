package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BorrowListRepository extends JpaRepository<BorrowList, Integer> {
    // Find borrow lists by member
    List<BorrowList> findByMember(Member member);
    
    // Find borrow lists by member ID
    List<BorrowList> findByMemberId(int memberId);
}
