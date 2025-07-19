package com.springproject.SpringProject.dao;

import java.util.List;

import javax.persistence.NoResultException;
import javax.sound.midi.Soundbank;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.springproject.SpringProject.models.Member;


@Repository
public class memberDao {
	@Autowired
	private SessionFactory sessionFactory;
	
	public void setSessionFactory(SessionFactory sf) {
		this.sessionFactory = sf;
	}
   @Transactional
	public List<Member> getAllMembers() {
		Session session = this.sessionFactory.getCurrentSession();
		List<Member>  memberList = session.createQuery("from MEMBER").list();
		return memberList;
	}
	
	@Transactional
	public Member saveMember(Member member) {
		this.sessionFactory.getCurrentSession().saveOrUpdate(member);
		System.out.println("Member added" + member.getId());
		return member;
	}
	
//    public User checkLogin() {
//    	this.sessionFactory.getCurrentSession().
//    }
	@Transactional
	public Member getMember(String username,String password) {
		Query query = sessionFactory.getCurrentSession().createQuery("from MEMBER where username = :username");
		query.setParameter("username",username);
		
		try {
			Member member = (Member) query.getSingleResult();
			System.out.println(member.getPassword());
			if(password.equals(member.getPassword())) {
				return member;
			}else {		
				return new Member();
			}
		}catch(Exception e){
			System.out.println(e.getMessage());
			Member member = new Member();
			return member;
		}
		
	}

	@Transactional
	public boolean userExists(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from MEMBER where username = :username");
		query.setParameter("username",username);
		return !query.getResultList().isEmpty();
	}

	@Transactional
	public Member getUserByUsername(String username) {
			Query<Member> query = sessionFactory.getCurrentSession().createQuery("from Member where username = :username", Member.class);
			query.setParameter("username", username);
			
			try {
				return query.getSingleResult();
			} catch (Exception e) {
				System.out.println(e.getMessage());
				return null; 
			}
		}

	public boolean memberExists(String username) {
		Query query = sessionFactory.getCurrentSession().createQuery("from MEMBER where username = :username");
		query.setParameter("username", username);
		return !query.getResultList().isEmpty();
	}

	public Member getMemberByUsername(String username) {
		Query<Member> query = sessionFactory.getCurrentSession().createQuery("from Member where username = :username", Member.class);
		query.setParameter("username", username);
		try {
			return query.getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			return null;
		}
	}
}