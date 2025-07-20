package com.springproject.SpringProject.repository;

import com.springproject.SpringProject.models.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    // Add custom queries if needed
}
