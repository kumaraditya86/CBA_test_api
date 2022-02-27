package cba.example.bookdemo.service;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ApiResponse;

public interface BookService {
	
	public ApiResponse saveBook(Book bookDetail);
	public ApiResponse deleteBook(Integer id);
	

}
