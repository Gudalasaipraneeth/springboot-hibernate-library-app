package com.springproject.SpringProject.controller;

import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.models.Member;
import com.springproject.SpringProject.dto.MemberDTO;
import com.springproject.SpringProject.dto.BookDTO;
import com.springproject.SpringProject.util.DTOConverter;
import com.springproject.SpringProject.services.borrowListService;
import com.springproject.SpringProject.services.memberService;
import com.springproject.SpringProject.services.bookService;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MemberController{

	private static final Logger logger = LoggerFactory.getLogger(MemberController.class);

	private final memberService memberService;
	private final bookService bookService;

	@Autowired
	public MemberController(memberService memberService, bookService bookService) {
		this.memberService = memberService;
		this.bookService = bookService;
	}

	@GetMapping("/register")
	public String registerUser()
	{
		return "register";
	}

	@GetMapping("/buy")
	public String buy()
	{
		return "buy";
	}

	@GetMapping("/login")
	public ModelAndView userlogin(@RequestParam(required = false) String error) {
		ModelAndView mv = new ModelAndView("userLogin");
		if ("true".equals(error)) {
			mv.addObject("msg", "Please enter correct email and password");
		}
		return mv;
	}
	
	@GetMapping("/")
	public ModelAndView indexPage()
	{
		ModelAndView mView  = new ModelAndView("index");	
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		mView.addObject("username", username);
		List<Book> books = this.bookService.getBooks();

		if (books.isEmpty()) {
			mView.addObject("msg", "No books are available");
		} else {
			mView.addObject("books", books);
		}
		return mView;
	}
	
	@GetMapping("/member/books")
	public ModelAndView getBooks() {

		ModelAndView mView = new ModelAndView("ubook");

		List<Book> books = this.bookService.getBooks();

		if(books.isEmpty()) {
			mView.addObject("msg","No books are available");
		}else {
			mView.addObject("books",books);
		}

		return mView;
	}
	
	@RequestMapping(value = "newuserregister", method = RequestMethod.POST)
	public ModelAndView newUseRegister(@ModelAttribute Member member)
	{
		// Check if username already exists in database
		boolean exists = this.memberService.checkMemberExists(member.getUsername());

		if(!exists) {
			logger.debug("User email: {}", member.getEmail());
			member.setRole("ROLE_NORMAL");
			this.memberService.addMember(member);

			logger.info("New user created: {}", member.getUsername());
			ModelAndView mView = new ModelAndView("userLogin");
			return mView;
		} else {
			logger.warn("User registration failed - username already taken: {}", member.getUsername());
			ModelAndView mView = new ModelAndView("register");
			mView.addObject("msg", member.getUsername() + " is taken. Please choose a different username.");
			return mView;
		}
	}

	@GetMapping("/profileDisplay")
	public String profileDisplay(Model model, HttpServletRequest request) {
		
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = memberService.getMemberByUsername(username);
	
		if (member != null) {
			model.addAttribute("userid", member.getId());
			model.addAttribute("username", member.getUsername());
			model.addAttribute("email", member.getEmail());
			model.addAttribute("password", member.getPassword()); 
			model.addAttribute("address", member.getAddress());
		} else {
			model.addAttribute("msg", "User not found");
		} 

		return "updateProfile";
	}
	

	   //for Learning purpose of model
	@GetMapping("test")
	public String test()
	{
		logger.debug("Test page accessed");
		return "test";
	}		// for learning purpose of model and view ( how data is pass to view)
		
		@GetMapping("/test2")
		public ModelAndView Test2()
		{
			logger.debug("Test2 page accessed");
			//create modelandview object
			ModelAndView mv=new ModelAndView();
			mv.addObject("name","jay gajera 17");
			mv.addObject("id",40);
			mv.setViewName("test2");
			
			List<Integer> list=new ArrayList<Integer>();
			list.add(10);
			list.add(25);
			mv.addObject("marks",list);
			return mv;
			
			
		}


//	@GetMapping("carts")
//	public ModelAndView  getCartDetail()
//	{
//		ModelAndView mv= new ModelAndView();
//		List<Cart>carts = cartService.getCarts();
//	}
	  
}