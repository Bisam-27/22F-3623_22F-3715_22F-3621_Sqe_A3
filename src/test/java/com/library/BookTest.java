package com.library;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import static org.testng.Assert.*;

public class BookTest {
    @DataProvider(name = "bookData")
    public Object[][] bookData() {
        return new Object[][] {
            { "The Great Gatsby", "F. Scott Fitzgerald" },
            { "1984", "George Orwell" }
        };
    }

    @Test(dataProvider = "bookData")
    public void testBookCreation(String title, String author) {
        Book book = new Book(title, author);
        assertEquals(book.getTitle(), title);
        assertEquals(book.getAuthor(), author);
        assertTrue(book.isAvailable());
    }

    @Test
    public void testBorrowBook() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        book.borrowBook();
        assertFalse(book.isAvailable());
    }

    @Test
    public void testReturnBook() {
        Book book = new Book("The Great Gatsby", "F. Scott Fitzgerald");
        book.borrowBook();
        book.returnBook();
        assertTrue(book.isAvailable());
    }
}
