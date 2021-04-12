package se.sdaproject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/articles")
@RestController
public class ArticlesController {

    //fields
    ArticlesRepository articlesRepository;

    //constructor
    @Autowired
    public ArticlesController(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    //methods GET
    @GetMapping
    public List<Articles> listAllArticles() {
        return articlesRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Articles> getArticleById(@PathVariable Long id) {

        Articles article = articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        return ResponseEntity.ok(article);
    }


    //methods POST
    @PostMapping
    public ResponseEntity<Articles> createArticle(@RequestBody Articles article) {
        articlesRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(article);

    }


    //methods PUT
    @PutMapping ("/{id}")
    public ResponseEntity<Articles> updateArticleById(@PathVariable Long id, @RequestBody Articles updatedArticle ){
        articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Articles article = articlesRepository.save(updatedArticle);
        return ResponseEntity.ok(article);
    }


    //methods DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Articles> deleteArticleById(@PathVariable Long id){
        Articles article= articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        articlesRepository.delete(article);

        return ResponseEntity.ok(article);
    }

}
