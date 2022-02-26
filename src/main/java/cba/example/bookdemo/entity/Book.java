package cba.example.bookdemo.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="book_detail")
@Getter @Setter @NoArgsConstructor

public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@Column(name="title")
	private String title;

	@Column(name="isbn13")
	private String isbn13;
	
	@Column(name="author")
	private String author;
	
	@Column(name="publication_date")
	private Date publicationDate;

}
