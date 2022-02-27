package cba.example.bookdemo.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import cba.example.bookdemo.config.ConstantMessage;
import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.repository.DBOperationRepository;
import cba.example.bookdemo.response.ApiResponse;
import cba.example.bookdemo.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired private DBOperationRepository dbService;
	//@Autowired ConstantMessage response;

	@Override
	public ApiResponse saveBook(Book bookDetail) {
		// TODO Auto-generated method stub
		
		try {
			
			dbService.save(bookDetail);
			
		}catch(ConstraintViolationException ex) {
			return new ApiResponse(ConstantMessage.EXCEPTION_CODE, ConstantMessage.EXCEPTION_MSG);
		}catch(DataIntegrityViolationException ex) {
			return new ApiResponse(ConstantMessage.EXCEPTION_CODE, ConstantMessage.EXCEPTION_MSG);
		}catch(Exception ex) {
			return new ApiResponse(ConstantMessage.EXCEPTION_CODE, ex.getClass().toString());
		}
		return new ApiResponse(ConstantMessage.SUCCESS_CODE, ConstantMessage.INSERT_SUCCESS);
	}
	
	@Override
	public ApiResponse deleteBook(Integer id) {
		// TODO Auto-generated method stub
		
		
		 if(dbService.findById(id).isPresent()) {
			 dbService.deleteById(id);
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
		
		return bookList;
	}

	
}
