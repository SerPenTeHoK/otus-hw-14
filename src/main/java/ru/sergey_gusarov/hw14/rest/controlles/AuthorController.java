package ru.sergey_gusarov.hw14.rest.controlles;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.sergey_gusarov.hw14.domain.books.Author;
import ru.sergey_gusarov.hw14.exception.NotFoundException;
import ru.sergey_gusarov.hw14.repository.author.AuthorRepository;
import ru.sergey_gusarov.hw14.service.books.AuthorService;

import java.util.List;

@Controller
public class AuthorController {
    private final AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/")
    public String listPage(Model model) {
        List<Author> authors = authorService.findAll();
        model.addAttribute("authors", authors);
        return "list";
    }

    @GetMapping("/edit")
    public String editPage(@RequestParam("id") String id, Model model) {
        Author author = authorService.getById(id).orElseThrow(NotFoundException::new);
        model.addAttribute("author", author);
        return "edit";
    }
}
