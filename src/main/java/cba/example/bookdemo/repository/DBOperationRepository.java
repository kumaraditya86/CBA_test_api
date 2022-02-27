package cba.example.bookdemo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cba.example.bookdemo.entity.Book;

@Repository
public interface DBOperationRepository extends JpaRepository<Book, Integer> {
	
	public void deleteById(Integer id);
	public Optional<Book> findById(Integer id);
	public List<Book> findByTitle(String title);
	public List<Book> findByAuthor(String author);
	public List<Book> findByIsbn13(String isbn13);

}
