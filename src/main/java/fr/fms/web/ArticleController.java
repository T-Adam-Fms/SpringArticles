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
        // Fetch the current page with 5 articles per page
        Page<Article> pageArticles = articleRepository.findAll(PageRequest.of(page, 5));

        // Add articles for the current page to the model
        model.addAttribute("listArticle", pageArticles.getContent());

        // Create an array of page numbers (0 to totalPages - 1)
        int totalPages = pageArticles.getTotalPages();
        int[] pagesArray = new int[totalPages];
        for (int i = 1; i < totalPages; i++) {
            pagesArray[i] = i;
        }
        model.addAttribute("pages", pagesArray);

        // Add the current page index to the model
        model.addAttribute("currentPage", page);

        // Return the template name
        return "articles";
    }
}
