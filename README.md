# library

Implementation of the library task.
Requirements can be found the requirements.docx

Project contains four classes:
- Library - contains all required methods
- BookInfo - represents general iformation about the book: title, author, year
- Book - represents the particular copy of a book
- LendInfo - class used to pass information about available and lent books  
Class diagram: Library has many BookInfo, BookInfo  has many Book

Tests are implemented in a LibraryTest class.  
They can be executed by running TestRunner class.
