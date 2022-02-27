package cba.example.bookdemo.controller;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ApiResponse;
import cba.example.bookdemo.serviceImpl.BookServiceImpl;

@Controller
@RequestMapping("/book")
public class BookdemoController {

	@Autowired BookServiceImpl bookService;
	
	@PostMapping("/add")
	@ResponseBody
	public ApiResponse insertNewBook(@RequestBody Book bookDetail) {
	
		return bookService.saveBook(bookDetail);
	}
	
	@DeleteMapping("/remove/{id}")
	@ResponseBody
	public ApiResponse removeBook(@PathVariable Integer id) {
		
		return bookService.deleteBook(id);
	}
	
	@PutMapping("/update/{id}")
	@ResponseBody
	public ApiResponse updateBook(@PathVariable Integer id, @RequestParam(required = false) String title,  @RequestParam(required = false) String author, @RequestParam(required = false) String isbn13,  @RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd")Date publicationDate ) {
		
		return bookService.updateBook(id, title, author, isbn13, publicationDate);
	}
	
	@GetMapping("/search")
	@ResponseBody
	public List<Book> searchBooks(@RequestParam(required = false) String title,  @RequestParam(required = false) String author, @RequestParam(required = false) String isbn13){
		return bookService.searchBook(title, author, isbn13);
	}
}
