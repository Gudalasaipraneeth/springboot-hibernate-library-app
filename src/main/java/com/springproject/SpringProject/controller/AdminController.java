package com.springproject.SpringProject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.springproject.SpringProject.models.Genre;
import com.springproject.SpringProject.models.Book;
import com.springproject.SpringProject.models.Member;
import com.springproject.SpringProject.models.BorrowList;
import com.springproject.SpringProject.dto.MemberDTO;
import com.springproject.SpringProject.dto.BookDTO;
import com.springproject.SpringProject.util.DTOConverter;
import com.springproject.SpringProject.services.genreService;
import com.springproject.SpringProject.services.bookService;
import com.springproject.SpringProject.services.memberService;
import com.springproject.SpringProject.services.borrowListService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private static final Logger logger = LoggerFactory.getLogger(AdminController.class);

	private final memberService memberService;
	private final genreService genreService;
	private final bookService bookService;
	private final borrowListService borrowListService;

	@Autowired
	public AdminController(memberService memberService, genreService genreService, bookService bookService, borrowListService borrowListService) {
		this.memberService = memberService;
		this.genreService = genreService;
		this.bookService = bookService;
		this.borrowListService = borrowListService;
	}
	
	@GetMapping("/index")
	public String index(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		model.addAttribute("username", username);
		return "index";			
	}
	
	@GetMapping("login")
	public ModelAndView adminlogin(@RequestParam(required = false) String error) {
		ModelAndView mv = new ModelAndView("adminlogin");
		if ("true".equals(error)) {
			mv.addObject("msg", "Invalid username or password. Please try again.");
		}
		return mv;
	}
	
	@GetMapping( value={"/","Dashboard"})
	public ModelAndView adminHome(Model model) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		ModelAndView mv = new ModelAndView("adminHome");
		mv.addObject("admin", authentication.getName());
		return mv;
	}
	
	@GetMapping("genres")
	public ModelAndView getGenres() {
		ModelAndView mView = new ModelAndView("genres");
		List<Genre> genres = this.genreService.getGenres();
		mView.addObject("genres", genres);
		return mView;
	}
	
	@PostMapping("/genres")
	public String addGenre(@RequestParam("genrename") String genre_name)
	{
		logger.info("Adding new genre: {}", genre_name);
		
		Genre genre =  this.genreService.addGenre(genre_name);
		if(genre.getName().equals(genre_name)) {
			return "redirect:genres";
		}else {
			return "redirect:genres";
		}
	}
	
	@GetMapping("genres/delete")
	public String removeGenreDb(@RequestParam("id") int id)
	{	
			this.genreService.deleteGenre(id);
			return "redirect:/admin/genres";
	}
	
	@GetMapping("genres/update")
	public String updateGenre(@RequestParam("genreid") int id, @RequestParam("genrename") String genrename)
	{
		Genre genre = this.genreService.updateGenre(id, genrename);
		return "redirect:/admin/genres";
	}

	
//	 --------------------------Remaining --------------------
	@GetMapping("books")
	public ModelAndView getBooks() {
		ModelAndView mView = new ModelAndView("books");

		List<Book> books = this.bookService.getBooks();
		
		if (books.isEmpty()) {
			mView.addObject("msg", "No books are available");
		} else {
			mView.addObject("books", books);
		}
		return mView;
	}
	
	@GetMapping("books/add")
	public ModelAndView addBook() {
		ModelAndView mView = new ModelAndView("booksAdd");
		List<Genre> genres = this.genreService.getGenres();
		mView.addObject("genres",genres);
		return mView;
	}

	@RequestMapping(value = "books/add",method=RequestMethod.POST)
	public String addBook(@RequestParam("name") String name,@RequestParam("genreid") int genreId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("bookImage") String bookImage) {
		logger.info("Adding new book: {} with genre ID: {}", name, genreId);
		Genre genre = this.genreService.getGenre(genreId);
		Book book = new Book();
		book.setId(genreId);
		book.setName(name);
		book.setGenre(genre);
		book.setDescription(description);
		book.setPrice(price);
		book.setImage(bookImage);
		book.setWeight(weight);
		book.setQuantity(quantity);
		this.bookService.addBook(book);
		return "redirect:/admin/books";
	}

	@GetMapping("books/update/{id}")
	public ModelAndView updatebook(@PathVariable("id") int id) {
		
		ModelAndView mView = new ModelAndView("booksUpdate");
		Book book = this.bookService.getBook(id);
		List<Genre> genres = this.genreService.getGenres();

		mView.addObject("genres",genres);
		mView.addObject("book", book);
		return mView;
	}
	
	@RequestMapping(value = "books/update/{id}",method=RequestMethod.POST)
	public String updateBook(@PathVariable("id") int id ,@RequestParam("name") String name,@RequestParam("genreid") int genreId ,@RequestParam("price") int price,@RequestParam("weight") int weight, @RequestParam("quantity")int quantity,@RequestParam("description") String description,@RequestParam("bookImage") String bookImage)
	{

//		this.bookService.updateBook();
		return "redirect:/admin/books";
	}
	
	@GetMapping("books/delete")
	public String removeBook(@RequestParam("id") int id)
	{
		this.bookService.deleteBook(id);
		return "redirect:/admin/books";
	}
	
	@PostMapping("books")
	public String postbook() {
		return "redirect:/admin/genres";
	}
	
	@GetMapping("customers")
	public ModelAndView getCustomerDetail() {
		ModelAndView mView = new ModelAndView("displayCustomers");
		List<Member> members = this.memberService.getMembers();
		List<MemberDTO> memberDTOs = DTOConverter.convertToMemberDTOList(members);
		mView.addObject("customers", memberDTOs);
		return mView;
	}

	@GetMapping("borrowlist")
	public ModelAndView getBorrowList() {
		ModelAndView mView = new ModelAndView("borrowList");
		List<BorrowList> borrowLists = this.borrowListService.getBorrowLists();
		mView.addObject("borrowLists", borrowLists);
		return mView;
	}

	@GetMapping("borrowlist/delete")
	public String deleteBorrowList(@RequestParam("id") int id) {
		BorrowList borrowList = this.borrowListService.getBorrowList(id);
		if (borrowList != null) {
			this.borrowListService.deleteBorrowList(borrowList);
			logger.info("Deleted borrow list with ID: {}", id);
		}
		return "redirect:/admin/borrowlist";
	}
	
	
	@GetMapping("profileDisplay")
	public String profileDisplay(Model model) {
		String username = SecurityContextHolder.getContext().getAuthentication().getName();
		Member member = memberService.getMemberByUsername(username);
		if (member != null) {
			model.addAttribute("userid", member.getId());
			model.addAttribute("username", member.getUsername());
			model.addAttribute("email", member.getEmail());
			model.addAttribute("password", member.getPassword());
			model.addAttribute("address", member.getAddress());
		}
		return "updateProfile";
	}
	
	@RequestMapping(value = "updateuser", method = RequestMethod.POST)
	public String updateUserProfile(@RequestParam("userid") int userid, @RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) {
		try {
			Member updated = memberService.updateMemberProfile(userid, username, email, password, address);
			if (updated != null) {
				Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
						username,
						password,
						SecurityContextHolder.getContext().getAuthentication().getAuthorities());
				SecurityContextHolder.getContext().setAuthentication(newAuthentication);
			}
		} catch (Exception e) {
			logger.error("Error updating user profile for ID {}: {}", userid, e.getMessage(), e);
		}
		return "redirect:/admin/profileDisplay";
	}

}
