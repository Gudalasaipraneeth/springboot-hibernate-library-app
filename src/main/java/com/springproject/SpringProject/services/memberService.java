package com.springproject.SpringProject.services;

import com.springproject.SpringProject.models.*;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.springproject.SpringProject.dao.memberDao;
import com.springproject.SpringProject.models.Member;

@Service
public class memberService {
	@Autowired
	private memberDao memberDao;
	
	public List<Member> getMembers(){
		return this.memberDao.getAllMembers();
	}
	
	public Member addMember(Member member) {
		try {
			return this.memberDao.saveMember(member);
		} catch (DataIntegrityViolationException e) {
			// handle unique constraint violation, e.g., by throwing a custom exception
			throw new RuntimeException("Add member error");
		}
	}
	
	public Member checkLogin(String username,String password) {
		return this.memberDao.getMember(username, password);
	}

	public boolean checkMemberExists(String username) {
		return this.memberDao.memberExists(username);
	}

	public Member getMemberByUsername(String username) {
			return memberDao.getMemberByUsername(username);
		}
}
