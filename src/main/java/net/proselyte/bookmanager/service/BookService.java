package net.proselyte.bookmanager.service;

import net.proselyte.bookmanager.model.Book;

import java.util.List;

public interface BookService {
    public void addBook(Book book);
    static final double fds = 15;
    static void ehheh(int i){

    }
    public void updateBook(Book book);
    public void removeBook(int id);
    public Book getBookById(int id);
    public List<Book> listBooks();
}
