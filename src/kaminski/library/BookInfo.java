package kaminski.library;

import java.util.ArrayList;
import java.util.List;

public class BookInfo {
	
	private final String title;
	
	private final String author;
	
	private final String year;
	
	private final List<Book> books;

	public BookInfo(String title, String author, String year) {
		this.title = title;
		this.author = author;
		this.year = year;
		this.books = new ArrayList<Book>();
	}
	
	@Override
	public String toString() {
		return String.join("\t", title, author, year);
	}

	public String getTitle() {
		return title;
	}

	public String getAuthor() {
		return author;
	}

	public String getYear() {
		return year;
	}
	
	public List<Book> getBooks() {
		return books;
	}

	public Book addBook() {
		Book book = new Book(this);
		books.add(book);
		return book;
	}
	
	public void removeBook(Book book) {
		books.remove(book);
	}
	
}
