package cba.example.bookdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ApiResponse;
import cba.example.bookdemo.serviceImpl.BookServiceImpl;

@Controller
@RequestMapping("/book")
public class BookdemoController {

	@Autowired BookServiceImpl bookService;
	
	@PostMapping("/add")
	public @ResponseBody ApiResponse insertNewBook(@RequestBody Book bookDetail) {
	
		return bookService.saveBook(bookDetail);
	}
	
	@DeleteMapping("/remove/{id}")
	public ApiResponse removeBook(@PathVariable Integer id) {
		
		return null;
	}
}
