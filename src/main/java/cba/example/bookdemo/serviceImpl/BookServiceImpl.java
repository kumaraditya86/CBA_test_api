package cba.example.bookdemo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cba.example.bookdemo.config.ConstantMessage;
import cba.example.bookdemo.controller.BookdemoController;
import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.repository.DBOperationRepository;
import cba.example.bookdemo.response.ApiResponse;
import cba.example.bookdemo.service.BookService;
import cba.example.bookdemo.service.Producer;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BookServiceImpl implements BookService {
	
	@Autowired private DBOperationRepository dbService;
	@Autowired private Producer producer;

	@Override
	public ApiResponse saveBook(Book bookDetail) {
		// TODO Auto-generated method stub
		Book book = null;
		ApiResponse response = null;
		try {
			
			book = dbService.save(bookDetail);
			
		}catch(ConstraintViolationException ex) {
			response = new ApiResponse(ConstantMessage.EXCEPTION_CODE, ConstantMessage.EXCEPTION_MSG);
		}catch(DataIntegrityViolationException ex) {
			response = new ApiResponse(ConstantMessage.EXCEPTION_CODE, ConstantMessage.EXCEPTION_MSG);
		}catch(Exception ex) {
			response = new ApiResponse(ConstantMessage.EXCEPTION_CODE, ex.getClass().toString());
		}
		
		if(book != null) {
			response = new ApiResponse(ConstantMessage.SUCCESS_CODE, ConstantMessage.INSERT_SUCCESS);
		}else {
			response = new ApiResponse(ConstantMessage.FAIL_CODE, ConstantMessage.INSERT_FAIL);
		}
		
		producer.publishToTopic("Book detail saved for book -"+book.getTitle());
		return response;
	}
	
	@Override
	public ApiResponse deleteBook(Integer id) {
		// TODO Auto-generated method stub
		
		
		 if(dbService.findById(id).isPresent()) {
			 dbService.deleteById(id);
			 producer.publishToTopic("Book recrd deleted for book id-"+id);
			 return new ApiResponse(ConstantMessage.SUCCESS_CODE, ConstantMessage.DELETE_SUCCESS);
		 }else {
			 return new ApiResponse(ConstantMessage.SUCCESS_CODE, ConstantMessage.DELETE_FAIL);
		 }
		 
		
	}

	@Override
	public ApiResponse updateBook(Integer id, String title, String author, String isbn13, Date publicationDate) {
		// TODO Auto-generated method stub
		Optional<Book> bookDetail = dbService.findById(id); 
		if(bookDetail.isPresent()) {
			Book updatedDetail = bookDetail.get();
			if(title != null) {
				updatedDetail.setTitle(title);
			}else if(author != null) {
				updatedDetail.setAuthor(author);
			}else if(isbn13 != null) {
				updatedDetail.setIsbn13(isbn13);;
			}else if(publicationDate != null) {
				updatedDetail.setPublicationDate(publicationDate);
			}
			
			Book book = dbService.save(updatedDetail);
			if(book != null) {
				producer.publishToTopic("Book detail updated for book -"+book.getTitle());
				return new ApiResponse(ConstantMessage.SUCCESS_CODE,ConstantMessage.UPDATE_SUCCESS);
			}
		}else {
			return new ApiResponse(ConstantMessage.SUCCESS_CODE,ConstantMessage.UPDATE_FAIL);
		}
		return new ApiResponse(ConstantMessage.SUCCESS_CODE,ConstantMessage.UPDATE_SUCCESS);
	}

	@Override
	public List<Book> searchBook(String title, String author, String isbn13) {
		// TODO Auto-generated method stub
		List<Book> bookList = null;
		if(title != null) {
			bookList = dbService.findByTitle(title);
		}else if(author != null) {
			bookList = dbService.findByAuthor(author);
		}else if(isbn13 != null) {
			bookList = dbService.findByIsbn13(isbn13);;
		}
		producer.publishToTopic("Book detail listed");
		return bookList;
	}

	
	
}
