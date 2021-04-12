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

    /**
     * return all comments
     *
     * @return action performed
     */
    @GetMapping("/comments")
    public List<Comments> listAllComments() {
        return commentsRepository.findAll();
    }


    /**
     * return comment on a given Id.
     *
     * @param id given id
     * @return the comment
     */
    @GetMapping("/comments/{id}")

    public Comments getCommentById(@PathVariable Long id) {
        return commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
    }


    /**
     * return all comments on article given by articleId.
     *
     * @param articleId given article id.
     * @return the comments requested
     */
    @GetMapping("/articles/{articleId}/comments")
    public List<Comments> getCommentsByID(@PathVariable Long articleId) {

        Articles article = articlesRepository.findById(articleId).orElseThrow(ResourceNotFoundException::new);
        return article.getComments();

    }

    /**
     * return all comments made by author given by authorName.
     *
     * @param authorName the requested authorName
     * @return the requested comments
     */
    @GetMapping(value = "/comments", params = {"authorName"})
    public List<Comments> getCommentsByAuthorName(@RequestParam String authorName) {
        return commentsRepository.findByAuthorName(authorName);

    }

    //methods POST

    /**
     * create a new comment on article given by articleId.
     *
     * @param articleId the article to comment
     * @param comment   the comment
     * @return action processed
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


    /**
     * update the given comment.
     *
     * @param id        the given id
     * @param upComment the updated  comment
     * @return action processed
     */
    @PutMapping("/comments/{id}")
    public ResponseEntity<Comments> updateCommentById(@PathVariable Long id, @Valid @RequestBody Comments upComment) {
        Comments comment = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        upComment.setRelatedArticle(comment.getRelatedArticle());//persist the related article
        upComment.setId(id);//
        commentsRepository.save(upComment);
        return ResponseEntity.ok(upComment);
    }


    //methods DELETE

    /**
     * delete the given comment.
     *
     * @param id the given id
     * @return action processed
     */
    @DeleteMapping("/comments/{id}")
    public ResponseEntity<Comments> deleteCommentById(@PathVariable Long id) {
        Comments comment = commentsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        commentsRepository.delete(comment);

        return ResponseEntity.ok(comment);
    }


}
