package cba.example.bookdemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cba.example.bookdemo.entity.Book;

@Repository
public interface DBOperationRepository extends JpaRepository<Book, Integer> {
	
	public void deleteById(Integer id);
	public Optional<Book> findById(Integer id);

}
