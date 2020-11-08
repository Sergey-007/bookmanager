package net.proselyte.bookmanager.dao;

import net.proselyte.bookmanager.model.Book;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.util.List;

public class BookDaoImpl implements BookDao {

    private static final Logger logger = LoggerFactory.getLogger(BookDaoImpl.class);

    private final DataSource dataSource;
    private final SessionFactory sessionFactory;

    public BookDaoImpl(DataSource dataSource, SessionFactory sessionFactory) {
        this.dataSource = dataSource;
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional
    public void addBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(book);
        logger.info("Book successfully saved. Book details:" + book);
    }

    @Override
    @Transactional
    public void updateBook(Book book) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(book);
        logger.info("Book successfully update. Book details:" + book);
    }

    @Override
    @Transactional
    public void removeBook(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        if (book != null) {
            session.delete(book);
        }
        logger.info("Book successfully remove. Book details:" + book);
    }

    @Override
    @Transactional(readOnly = true)
    public Book getBookById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        Book book = (Book) session.load(Book.class, new Integer(id));
        logger.info("Book successfully load. Book details:" + book);
        return book;
    }

    @Override
    @SuppressWarnings("unchecked")
    @Transactional(readOnly = true)
    public List<Book> listBooks() {
        Session session = this.sessionFactory.getCurrentSession();
        List<Book> booklist = session.createQuery("from Book").list();
        for (Book book : booklist) {
            logger.info("Booklist: " + book);
        }
        return booklist;
    }
}
