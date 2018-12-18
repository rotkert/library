package kaminski.library;

public class LendInfo {

	private final BookInfo bookInfo;
	
	private final Long available;
	
	private final Long lent;

	public LendInfo(BookInfo bookInfo, long available, long lent) {
		this.bookInfo = bookInfo;
		this.available = available;
		this.lent = lent;
	}

	public BookInfo getBookInfo() {
		return bookInfo;
	}

	public long getAvailable() {
		return available;
	}

	public long getLent() {
		return lent;
	}
	
	@Override
	public String toString() {
		return String.join("\t", bookInfo.toString(), available.toString(), lent.toString());
	}
}
