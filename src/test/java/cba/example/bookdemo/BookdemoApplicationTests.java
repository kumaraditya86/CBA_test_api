package cba.example.bookdemo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.atLeastOnce;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import cba.example.bookdemo.config.ConstantMessage;
import cba.example.bookdemo.controller.BookdemoController;
import cba.example.bookdemo.entity.Book;
import cba.example.bookdemo.repository.DBOperationRepository;
import cba.example.bookdemo.response.ApiResponse;
import cba.example.bookdemo.service.Producer;
import cba.example.bookdemo.serviceImpl.BookServiceImpl;
import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
class BookdemoApplicationTests {
	
	@Autowired
	private BookServiceImpl bookService;
	
	@Autowired Producer producer;
	
	@MockBean
	private DBOperationRepository repository;
	
	
	@Test
	public void searchBooksTest() {
		String title = "Test Book4";
		when(repository.findByTitle(title)).thenReturn(Stream.of(new Book("Test Book 01", "9870123456789", "Test Author", new Date())).collect(Collectors.toList()));
		assertEquals(1,bookService.searchBook(title,null,null).size());
		producer.publishToTopic("Test for search book completes.");
	}

	@Test
	public void saveBookTest() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date = format.parse("2022-02-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book = new Book("Test book10","9870000000000","Test writer",date);
		ApiResponse response = new ApiResponse(ConstantMessage.SUCCESS_CODE, ConstantMessage.INSERT_SUCCESS);
		when(repository.save(book)).thenReturn(book);
		assertEquals(response.getResponseCode(), bookService.saveBook(book).getResponseCode());
		producer.publishToTopic("Test for save book completes.");
	}
	
	@Test
	public void deleteBookTest() {
		repository.deleteById(4);
		verify(repository, atLeastOnce()).deleteById(4);
		producer.publishToTopic("Test for delete book completes.");
	}
	
	@Test
	public void updateBookTest() {
		SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
		Date date=null;
		try {
			 date = format.parse("2022-02-22");
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Book book = new Book("Test book10","9870000000000","Test writer",date);
		
		when(repository.findById(4)).thenReturn(Optional.of(book));
		book.setTitle("Test Book9");
		when(repository.save(book)).thenReturn(book);
		assertEquals(0,bookService.updateBook(4, "Test Book9", null, null, null).getResponseCode());
		producer.publishToTopic("Test for update book completes.");
	}
}
