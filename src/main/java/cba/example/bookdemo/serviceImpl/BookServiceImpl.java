package cba.example.bookdemo.serviceImpl;

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
	
	@Autowired DBOperationRepository dbService;
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

	
}
