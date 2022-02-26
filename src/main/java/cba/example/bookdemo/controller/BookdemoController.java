package cba.example.bookdemo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ResponseBody;
import cba.example.bookdemo.serviceImpl.BookServiceImpl;

@Controller
@RequestMapping("/book")
public class BookdemoController {

	@Autowired BookServiceImpl bookService;
	
	@PostMapping("/add")
	public ResponseBody insertNewBook(@RequestBody Book bookDetail) {
	
		return bookService.saveBook(bookDetail);
	}
}
