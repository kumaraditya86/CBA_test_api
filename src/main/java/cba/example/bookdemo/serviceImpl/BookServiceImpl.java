package cba.example.bookdemo.serviceImpl;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cba.example.bookdemo.config.ConstantMessage;
import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.repository.DBOperationRepository;
import cba.example.bookdemo.response.ResponseBody;
import cba.example.bookdemo.service.BookService;

@Service
public class BookServiceImpl implements BookService {
	
	@Autowired DBOperationRepository dbService;
	//@Autowired ConstantMessage response;

	@Override
	public ResponseBody saveBook(Book bookDetail) {
		// TODO Auto-generated method stub
		
		try {
			
			dbService.save(bookDetail);
			
		}catch(ConstraintViolationException ex) {
			return new ResponseBody(ConstantMessage.EXCEPTION_CODE, ex.getMessage());
		}catch(Exception ex) {
			return new ResponseBody(ConstantMessage.EXCEPTION_CODE, ex.getMessage());
		}
		return new ResponseBody(ConstantMessage.SUCCESS_CODE, ConstantMessage.INSERT_SUCCESS);
	}

}
