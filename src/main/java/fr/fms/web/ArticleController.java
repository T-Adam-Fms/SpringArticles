package fr.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

@Controller
public class ArticleController {

    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/index")
    public String index(Model model, 
                        @RequestParam(name = "page", defaultValue = "0") int page) {
        // Fetch paginated articles, 5 items per page
        Page<Article> pageArticles = articleRepository.findAll(PageRequest.of(page, 5));
        model.addAttribute("listArticle", pageArticles.getContent());

        // Add total pages to the model
        model.addAttribute("pages", new int[pageArticles.getTotalPages()]);

        // Add the current page to the model
        model.addAttribute("currentPage", page);

        // Return the view name (template name)
        return "articles";
    }
}
