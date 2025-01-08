
package fr.fms.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;

import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    @GetMapping("/index")
    public String index(Model model) {
    	List<Article> articles = articleRepository.findAll();
        model.addAttribute("listArticle", articleRepository.findAll());
        return "articles"; // Ensure the template name is correct
    }

}


