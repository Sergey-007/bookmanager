package net.proselyte.bookmanager.controller;

import net.proselyte.bookmanager.model.Book;
import net.proselyte.bookmanager.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@Controller
public class BookController {
    private BookService bookService;

    @Autowired(required = true)
    @Qualifier(value = "bookService")
    public void setBookService(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping(value = "/book")
    public String listBooks(Model model){
        model.addAttribute("book", new Book());
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "book";
    }
    @RequestMapping(value = "/book/add", method = RequestMethod.GET)
    public String addBook(@ModelAttribute("book") Book book){
        if (book.getId() == 0){
            this.bookService.addBook(book);
        }else{
            this.bookService.updateBook(book);
        }
        return "redirect:/book";
    }
    @RequestMapping("/remove/{id}")
    public String removeBook(@PathVariable("id") int id){
        this.bookService.removeBook(id);
        return "redirect:/book";
    }
    @RequestMapping("/edit/{id}")
    public String editBook(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookService.getBookById(id));
        model.addAttribute("listBooks", this.bookService.listBooks());
        return "/book";
    }
    @RequestMapping("/bookdata/{id}")
    public String bookData(@PathVariable("id") int id, Model model){
        model.addAttribute("book", this.bookService.getBookById(id));
        return "/bookdata";
    }
}