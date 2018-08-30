package ru.sergey_gusarov.hw14.rest.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sergey_gusarov.hw14.domain.books.Author;
import ru.sergey_gusarov.hw14.domain.books.Book;
import ru.sergey_gusarov.hw14.exception.NotFoundException;
import ru.sergey_gusarov.hw14.service.books.AuthorService;
import ru.sergey_gusarov.hw14.service.books.BookService;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;
    private final BookService bookService;

    @Value("${error.message}")
    private String errorMessage;

    @Autowired
    public AuthorController(AuthorService authorService, BookService bookService) {
        this.authorService = authorService;
        this.bookService = bookService;
    }

    @RequestMapping("/authors")
    public String listAuthorPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authorsList";
    }

    @RequestMapping("/newAuthor")
    public String newAuthorPage(@ModelAttribute Author author, Model model) {
        model.toString();
        authorService.save(author);
        return "authorEdit";
    }

    @RequestMapping("/newAuthorForBook")
    public String newAuthorFromBookPage(@ModelAttribute Book book, Model model) {
        model.toString();
        model.asMap().get("author");
        book = bookService.findById(book.getId()).get();
        Author author = new Author();
        authorService.save(author);
        book.getAuthors().add(author);
        bookService.save(book);
        model.addAttribute(author);
        return "redirect:/newAuthor?id=" + author.getId();
    }

    @RequestMapping("/author")
    public String authorPage(@RequestParam("id") String id, Model model) {
        Author author = authorService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        authorService.save(author);
        return "authorEdit";
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public String editAuthor(@ModelAttribute Author author, Model model) {
        if (author.getName().isEmpty()) {
            model.addAttribute("errorMessage", errorMessage);
            return "redirect:/author?id=" + author.getId();
        } else
            authorService.save(author);
        return "redirect:/authors";
    }

    @RequestMapping(value = "/deleteAuthor")
    public String deleteAuthor(@ModelAttribute Author author) {
        authorService.deleteById(author.getId());
        return "redirect:/authors";
    }
}
