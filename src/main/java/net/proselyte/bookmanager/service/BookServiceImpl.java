package net.proselyte.bookmanager.service;

import net.proselyte.bookmanager.dao.BookDao;
import net.proselyte.bookmanager.model.Book;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class BookServiceImpl implements BookService{

    private BookDao bookDao;

    public void setBookDao(BookDao bookDao) {
        this.bookDao = bookDao;
    }

    @Override
    public void addBook(Book book) {
        this.bookDao.addBook(book);
    }

    @Override
    public void updateBook(Book book) {
        this.bookDao.updateBook(book);
    }

    @Override
    public void removeBook(int id) {
        this.bookDao.removeBook(id);
    }

    @Override
    public Book getBookById(int id) {
        return this.bookDao.getBookById(id);
    }

    @Override
    public List<Book> listBooks() {
        return this.bookDao.listBooks();
    }
}
