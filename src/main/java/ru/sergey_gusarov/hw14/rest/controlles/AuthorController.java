package ru.sergey_gusarov.hw14.rest.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sergey_gusarov.hw14.domain.books.Author;
import ru.sergey_gusarov.hw14.exception.NotFoundException;
import ru.sergey_gusarov.hw14.service.books.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @RequestMapping("/authors")
    public String listAuthorPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "authorsList";
    }

    @RequestMapping("/newAuthor")
    public String newAuthorPage(@ModelAttribute Author author) {
        authorService.save(author);
        return "authorEdit";
    }

    @RequestMapping("/author")
    public String authorPage(@RequestParam("id") String id, Model model) {
        Author author = authorService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "authorEdit";
    }

    @RequestMapping(value = "/author", method = RequestMethod.POST)
    public String editAuthor(@ModelAttribute Author author) {
        authorService.save(author);
        String id = author.getId();
        return "redirect:/authors";
    }

    @RequestMapping(value = "/deleteAuthor")
    public String deleteAuthor(@ModelAttribute Author author) {
        authorService.deleteById(author.getId());
        return "redirect:/authors";
    }
}
