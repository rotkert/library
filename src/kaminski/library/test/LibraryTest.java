package kaminski.library.test;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import kaminski.library.Book;
import kaminski.library.LendInfo;
import kaminski.library.Library;

public class LibraryTest {

	@Test
	public void addBookTest() {
		Library library = new Library();
		Book book = library.addBook("BookA", "AuthorA", "2000");
		assertNotNull(book.getId());
		assertTrue(book.isAvailable());
		assertNotNull(book.getBookInfo().getBooks());
		assertEquals(library.listAllBookInfos().size(), 1);
	}
	
	@Test
	public void addTwoSameBooksTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		Book book2 = library.addBook("BookA", "AuthorA", "2000");
		
		assertEquals(book1.getBookInfo(), book2.getBookInfo());
		assertEquals(book1.getBookInfo().getBooks().size(), 2);
		assertEquals(library.listAllBookInfos().size(), 1);
	}
	
	@Test 
	public void addSameBookDifferentYearTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		Book book2 = library.addBook("BookA", "AuthorA", "2001");
		
		assertNotEquals(book1.getBookInfo(), book2.getBookInfo());
		assertEquals(book1.getBookInfo().getBooks().size(), 1);
		assertEquals(book2.getBookInfo().getBooks().size(), 1);
		assertEquals(library.listAllBookInfos().size(), 2);
	}
	
	@Test 
	public void removeBookOtherExistsTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		Book book2 = library.addBook("BookA", "AuthorA", "2000");
		library.removeBook(book1.getId());
		
		assertNotNull(book2.getBookInfo().getBooks());
		assertEquals(book2.getBookInfo().getBooks().size(), 1);
		assertEquals(library.listAllBookInfos().size(), 1);
	}

	@Test
	public void removeTheLastTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		Book book2 = library.addBook("BookA", "AuthorA", "2000");
		library.removeBook(book1.getId());
		library.removeBook(book2.getId());
		
		assertEquals(library.listAllBookInfos().size(), 0);
	}
	
	@Test
	public void findBooksTest() {
		Library library = new Library();
		library.addBook("BookA", "AuthorA", "2000");
		library.addBook("BookA", "AuthorA", "2001");
		library.addBook("BookB", "AuthorB", "2000");
		List<Book> matchedBooks = library.findBooks(null, null, "2000");
		
		assertNotNull(matchedBooks);
		assertEquals(matchedBooks.size(), 2);
		
		List<Book> matchedBooks2 = library.findBooks("BookA", null, "2000");
		
		assertNotNull(matchedBooks2);
		assertEquals(matchedBooks2.size(), 1);
	}
	
	@Test
	public void lendBookTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		library.addBook("BookA", "AuthorA", "2000");
		book1 = library.lendBook(book1.getId(), "LenderName");
		List<LendInfo> allBooks = library.listAllBookInfos();
		
		assertFalse(book1.isAvailable());
		assertNotNull(book1.getLender());
		assertEquals(allBooks.get(0).getAvailable(), 1);
		assertEquals(allBooks.get(0).getLent(), 1);
	}
	
	@Test
	public void lendBookNotAvailableTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		book1 = library.lendBook(book1.getId(), "LenderName");
		book1 = library.lendBook(book1.getId(), "LenderName");
		
		assertNull(book1);
	}
	
	@Test
	public void removeBookNotAvailableTest() {
		Library library = new Library();
		Book book1 = library.addBook("BookA", "AuthorA", "2000");
		book1 = library.lendBook(book1.getId(), "LenderName");
		book1 = library.removeBook(book1.getId());
		
		assertNull(book1);
	}
}
