package com.springproject.SpringProject.services;

import com.springproject.SpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.repository.MemberRepository;
import com.springproject.SpringProject.models.Member;

@Service
public class memberService {
	@Autowired
	private MemberRepository memberRepository;

	public List<Member> getMembers() {
		return memberRepository.findAll();
	}

	public Member addMember(Member member) {
		try {
			return memberRepository.save(member);
		} catch (DataIntegrityViolationException e) {
			throw new RuntimeException("Add member error");
		}
	}

	public boolean checkMemberExists(String username) {
		return memberRepository.existsByUsername(username);
	}

	public Member getMemberByUsername(String username) {
		return memberRepository.findByUsername(username);
	}

	// Implement checkLogin if needed, or use Spring Security

	public Member updateMember(Member member) {
		return memberRepository.save(member);
	}

	public Member updateMemberProfile(int id, String username, String email, String password, String address) {
		return memberRepository.findById(id).map(m -> {
			m.setUsername(username);
			m.setEmail(email);
			m.setPassword(password);
			m.setAddress(address);
			return memberRepository.save(m);
		}).orElse(null);
	}
}
