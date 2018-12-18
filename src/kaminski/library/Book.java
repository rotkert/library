package kaminski.library;

import java.util.UUID;

public class Book {

	private final String id; 
	
	private final BookInfo bookInfo;
	
	private Boolean available;
	
	private String lender;

	public Book(BookInfo bookInfo) {
		this.id = UUID.randomUUID().toString();
		this.bookInfo = bookInfo;
		this.available = true;
		this.lender = null;
	}
	
	@Override
	public String toString() {
		return String.join("\t", id, bookInfo.toString(), available.toString(), lender);
	}

	public String getId() {
		return id;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public boolean isAvailable() {
		return available;
	}

	public String getLender() {
		return lender;
	}

	public void setAvailable(boolean available) {
		this.available = available;
	}

	public void setLender(String lender) {
		this.lender = lender;
	}
	
}
