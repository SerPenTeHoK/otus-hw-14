package ru.sergey_gusarov.hw14.rest.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sergey_gusarov.hw14.domain.books.Book;
import ru.sergey_gusarov.hw14.exception.NotFoundException;
import ru.sergey_gusarov.hw14.service.books.BookService;

import java.util.List;

@Controller
public class BookController {
    private final BookService bookService;

    @Autowired
    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @RequestMapping("/books")
    public String listBookPage(Model model) {
        List<Book> books = bookService.findAll();
        model.addAttribute("books", books);
        return "booksList";
    }

    @RequestMapping("/newBook")
    public String newBookPage(@ModelAttribute Book book) {
        bookService.save(book);
        return "bookEdit";
    }

    @RequestMapping("/book")
    public String bookPage(@RequestParam("id") String id, Model model) {
        Book book = bookService.findById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("book", book);
        return "bookEdit";
    }

    @RequestMapping(value = "/book", method = RequestMethod.POST)
    public String editBook(@ModelAttribute Book book) {
        bookService.save(book);
        String id = book.getId();
        return "redirect:/books";
    }

    @RequestMapping(value = "/deleteBook")
    public String deleteBook(@ModelAttribute Book book) {
        bookService.deleteById(book.getId());
        return "redirect:/books";
    }
}
