package com.library;

import java.util.ArrayList;
import java.util.List;

public class LibraryService {
    private List<Book> books = new ArrayList<>();
    private List<Member> members = new ArrayList<>();

    public void addBook(String title, String author) {
        books.add(new Book(title, author));
    }

    public Member registerMember(String memberId, String name) {
        Member member = new Member(memberId, name);
        members.add(member);
        return member; // Return the member object
    }

    public Book getBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equals(title)) {
                return book;
            }
        }
        return null;
    }

    public Member getMember(String memberId) {
        for (Member member : members) {
            if (member.getMemberId().equals(memberId)) {
                return member;
            }
        }
        return null;
    }

    public boolean borrowBook(String title) {
        Book book = getBook(title);
        if (book != null && book.isAvailable()) {
            book.borrowBook();
            return true;
        }
        return false; // Return false if book not available
    }

    public boolean returnBook(String title) {
        Book book = getBook(title);
        if (book != null) {
            book.returnBook();
            return true; // Return true if the book was successfully returned
        }
        return false; // Return false if the book was not found
    }

    public int getTotalBooks() {
        return books.size(); // Return the total number of books
    }

    public int getTotalMembers() {
        return members.size(); // Return the total number of members
    }

    public boolean isBookAvailable(String title) {
        Book book = getBook(title);
        return book != null && book.isAvailable();
    }

    public boolean removeBook(String title) {
        return books.removeIf(book -> book.getTitle().equals(title));
    }

    public void unregisterMember(String memberId) {
        members.removeIf(member -> member.getMemberId().equals(memberId));
    }
}
