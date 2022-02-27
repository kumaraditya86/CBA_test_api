package cba.example.bookdemo.service;

import java.util.Date;
import java.util.List;

import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.response.ApiResponse;

public interface BookService {
	
	public ApiResponse saveBook(Book bookDetail);
	public ApiResponse deleteBook(Integer id);
	public ApiResponse updateBook(Integer id, String title, String author, String isbn13, Date publicationDate);
	public List<Book> searchBook(String title, String author, String isbn13);

}
