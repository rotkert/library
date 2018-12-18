package kaminski.library;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

public class Library {

	private final List<BookInfo> bookInfos;

	public Library() {
		this.bookInfos = new ArrayList<>();
	}
	
	public Book addBook(String title, String author, String year) {
		if (title == null || author == null || year == null) {
			System.out.println("Required value missing");
			return null;
		}
		
		BookInfo bookInfo = this.findBookInfos(title, author, year).stream().findFirst().orElse(null);
		
		if (bookInfo == null) {
			bookInfo = new BookInfo(title, author, year);
			bookInfos.add(bookInfo);
		}
	
		Book book = bookInfo.addBook();
		return book;
	}
	
	public Book removeBook(String id) {
		Book book = this.findBookById(id);
		
		if (book != null && book.isAvailable()) {
			BookInfo bookInfo = book.getBookInfo();
			bookInfo.removeBook(book);
			if (bookInfo.getBooks().isEmpty()) {
				bookInfos.remove(bookInfo);
			}
			return book;
		} else {
			return null;
		}
	}
	
	public List<LendInfo> listAllBookInfos() {
		List<LendInfo> lendInfos = bookInfos.stream()
				.map(bi -> {
					long available =  bi.getBooks().stream().filter(Book::isAvailable).count();
					long lent = bi.getBooks().size() - available;
					return new LendInfo(bi, available, lent);
				})
				.collect(Collectors.toList());
		
		lendInfos.forEach(System.out::println);
		return lendInfos;
	}
	
	public List<Book> findBooks(String title, String author, String year) {
		List<BookInfo> bookInfos = this.findBookInfos(title, author, year);
		List<Book> books = bookInfos.stream()
				.map(BookInfo::getBooks)
				.flatMap(Collection::stream)
				.collect(Collectors.toList());
		return books;
	}
	
	public Book lendBook(String id, String lender) {
		Book book = this.findBookById(id);
		
		if (book != null && book.isAvailable()) {
			book.setAvailable(false);
			book.setLender(lender);
			return book;
		} else {
			return null;
		}
		
	}
	
	public Book seeBookDetails(String id) {
		Book book = this.findBookById(id);
		
		if (book != null) {
			System.out.println(book);
		}
		
		return book;
	}
	
	private List<BookInfo> findBookInfos(String title, String author, String year) {
		List<BookInfo> matchedBookInfos = bookInfos.stream()
			.filter(bi -> title == null || title.isEmpty() || title.equals(bi.getTitle()))
			.filter(bi -> author == null || author.isEmpty() || author.equals(bi.getAuthor()))
			.filter(bi -> year == null || year.isEmpty() || year.equals(bi.getYear()))
			.collect(Collectors.toList());
		return matchedBookInfos;
	}
	
	private Book findBookById(String id) {
		Book matchedBook = bookInfos.stream()
			.map(BookInfo::getBooks)
			.flatMap(Collection::stream)
			.filter(b -> b.getId().equals(id))
			.findAny()
			.orElse(null);
		return matchedBook;
	}
}
