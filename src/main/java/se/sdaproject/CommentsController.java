package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import javax.validation.Valid;

@RestController
public class CommentsController {

    //fields
    ArticlesRepository articlesRepository;
    CommentsRepository commentsRepository;


    //constructor

    public CommentsController(ArticlesRepository articlesRepository, CommentsRepository commentsRepository) {
        this.articlesRepository = articlesRepository;
        this.commentsRepository = commentsRepository;
    }


    //methods GET

    /*
    return all comments
     */
    @GetMapping("/comments")
    public List<Comments> listAllComments() {
        return commentsRepository.findAll();
    }


    @GetMapping("/comments/{id}")

    public Comments getCommentById(@PathVariable Long id){
        return commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }



  @GetMapping("/articles/{articleId}/comments")

    public List<Comments> getCommentsByID(@PathVariable Long articleId){

      Articles article = articlesRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
       return article.getComments();

    }


    @GetMapping(value = "/comments", params = {"authorName"})

    public List<Comments> getCommentsByAuthorName(@RequestParam String authorName){
        return commentsRepository.findByAuthorName(authorName);

    }

    //methods POST

    /*
     * post a new comment related to a specific aricleId
     */
    @PostMapping("/articles/{articleId}/comments")
    public ResponseEntity<Comments> createComment(@PathVariable Long articleId, @RequestBody Comments comment) {
        Articles article = articlesRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        comment.setRelatedArticle(article);
        commentsRepository.save(comment);

        return ResponseEntity.status(HttpStatus.CREATED).body(comment);

    }

    //methods PUT

    /*
    update the given comment.
     */
    @PutMapping ("/comments/{id}")
    public ResponseEntity<Comments> updateCommentById(@PathVariable Long id, @Valid @RequestBody Comments upComment){
        Comments comment = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        upComment.setRelatedArticle(comment.getRelatedArticle());//persist the related article
        upComment.setId(id);//
        commentsRepository.save(upComment);
        return ResponseEntity.ok(upComment);
    }


    //methods DELETE

    /*
    delete the given comment.
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> deleteCommentById(@PathVariable Long id){
        Comments comment= commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentsRepository.delete(comment);

        return ResponseEntity.ok(comment);
    }


}
