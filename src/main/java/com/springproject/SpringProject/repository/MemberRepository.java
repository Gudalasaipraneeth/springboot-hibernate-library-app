package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Find member by username
    Member findByUsername(String username);
    
    // Check if member exists by username
    boolean existsByUsername(String username);
}
