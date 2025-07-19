package com.springproject.SpringProject.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;

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
import com.springproject.SpringProject.services.genreService;
import com.springproject.SpringProject.services.bookService;
import com.springproject.SpringProject.services.memberService;

@Controller
@RequestMapping("/admin")
public class AdminController {

	private final memberService memberService;
	private final genreService genreService;
	private final bookService bookService;

	@Autowired
	public AdminController(memberService memberService, genreService genreService, bookService bookService) {
		this.memberService = memberService;
		this.genreService = genreService;
		this.bookService = bookService;
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
		System.out.println(genre_name);
		
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
		System.out.println(genreId);
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
		mView.addObject("customers", members);
		return mView;
	}
	
	
	@GetMapping("profileDisplay")
	public String profileDisplay(Model model) {
		String displayusername,displaypassword,displayemail,displayaddress;
		try
		{
			Class.forName("com.mysql.cj.jdbc.Driver");
			String dbUrl = System.getenv("DB_URL");
			String dbUser = System.getenv("DB_USER");
			String dbPassword = System.getenv("DB_PASSWORD");
			Connection con = DriverManager.getConnection(dbUrl, dbUser, dbPassword);
			PreparedStatement stmt = con.prepareStatement("select * from users where username = ?"+";");
			
			String username = SecurityContextHolder.getContext().getAuthentication().getName();
			stmt.setString(1, username);
			
			ResultSet rst = stmt.executeQuery();
			
			if(rst.next())
			{
			int userid = rst.getInt(1);
			displayusername = rst.getString(2);
			displayemail = rst.getString(3);
			displaypassword = rst.getString(4);
			displayaddress = rst.getString(5);
			model.addAttribute("userid",userid);
			model.addAttribute("username",displayusername);
			model.addAttribute("email",displayemail);
			model.addAttribute("password",displaypassword);
			model.addAttribute("address",displayaddress);
			}
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		System.out.println("Hello");
		return "updateProfile";
	}
	
	@RequestMapping(value = "updateuser",method=RequestMethod.POST)
	public String updateUserProfile(@RequestParam("userid") int userid,@RequestParam("username") String username, @RequestParam("email") String email, @RequestParam("password") String password, @RequestParam("address") String address) 
	
	{
		try
		{
			Class.forName("com.mysql.jdbc.Driver");
			Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/ecommjava","root","");
			
			PreparedStatement pst = con.prepareStatement("update users set username= ?,email = ?,password= ?, address= ? where uid = ?;");
			pst.setString(1, username);
			pst.setString(2, email);
			pst.setString(3, password);
			pst.setString(4, address);
			pst.setInt(5, userid);
			int i = pst.executeUpdate();	
			
			Authentication newAuthentication = new UsernamePasswordAuthenticationToken(
					username,
					password,
					SecurityContextHolder.getContext().getAuthentication().getAuthorities());

			SecurityContextHolder.getContext().setAuthentication(newAuthentication);
		}
		catch(Exception e)
		{
			System.out.println("Exception:"+e);
		}
		return "redirect:index";
	}

}
