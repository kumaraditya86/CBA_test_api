package cba.example.bookdemo.service;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ResponseBody;

public interface BookService {
	
	public ResponseBody saveBook(Book bookDetail);
	
	

}
