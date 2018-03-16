package bsmanagement.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;


/**
 * 
 * @author JOAO GOMES
 *
 */
@Entity
public class Book {
	
	
	private int id;
	private String name;
	private Author author;
	
	
	/**
	 * 
	 */
	protected Book() {
	}
	/**
	 * @param id
	 * @param name
	 * @param author
	 */
	public Book(String name, Author author) {
		this.name = name;
		this.author = author;
	}
	
	/**
	 * @return the author
	 */
	@ManyToOne
    @JoinColumn(name = "author_id")
	public Author getAuthor() {
		return author;
	}
	/**
	 * @param author the author to set
	 */
	public void setAuthor(Author author) {
		this.author = author;
	}
	/**
	 * @return the id
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	public int getId() {
		return id;
	}
	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}
	
	
	@Override
	public String toString() {
		return "Book [id= " + id + ", name= " + name + ", author= " + author + "]";
	}
	
	
}
