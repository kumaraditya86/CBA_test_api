package cba.example.bookdemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import cba.example.bookdemo.entity.Book;

@Repository
public interface DBOperationRepository extends JpaRepository<Book, Integer> {

}
