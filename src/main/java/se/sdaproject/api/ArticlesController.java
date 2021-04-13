package se.sdaproject.api;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.Articles;
import se.sdaproject.ArticlesRepository;
import se.sdaproject.api.exception.ResourceNotFoundException;
import se.sdaproject.service.ArticlesService;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticlesController {

    //fields
    ArticlesRepository articlesRepository;
    ArticlesService articlesService;

    public ArticlesController(ArticlesRepository articlesRepository, ArticlesService articlesService) {
        this.articlesRepository = articlesRepository;
        this.articlesService = articlesService;
    }

    //constructor
    @Autowired


    //methods GET

    /**
     * returns all articles.
     * @return all articles
     */
    @GetMapping
    public List<Articles> listAllArticles() {
        return articlesRepository.findAll();
    }

    /**
     * return a specific article based on the provided id
     * @param id provided id
     * @return action performed
     */
    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable Long id) {

        Articles article = articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }


    //methods POST

    /**
     * create a new article.
     * @param article article to be posted
     * @return action performed
     */
    @PostMapping
    public ResponseEntity<Articles> createArticle(@RequestBody Articles article) {
        articlesRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);

    }


    //methods PUT

    /**
     * update the given article.
     * @param id given ID
     * @param updatedArticle new article
     * @return action performed
     */
    @PutMapping ("/{id}")
    public ResponseEntity<Articles> updateArticleById(@PathVariable Long id, @RequestBody Articles updatedArticle ){
        Articles article = articlesService.updateArticle(id,updatedArticle);
        /*articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Articles article = articlesRepository.save(updatedArticle);*/
        return ResponseEntity.ok(article);
    }


    //methods DELETE

    /**
     * delete the given article
     * @param id given id
     * @return action performed
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Articles> deleteArticleById(@PathVariable Long id){
        Articles article= articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articlesRepository.delete(article);

        return ResponseEntity.ok(article);
    }

}
