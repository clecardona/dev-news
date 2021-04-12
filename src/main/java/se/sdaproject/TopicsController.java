package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
public class TopicsController {

    //fields
    TopicsRepository topicsRepository;
    ArticlesRepository articlesRepository;

    //constructor


    public TopicsController(TopicsRepository topicsRepository, ArticlesRepository articlesRepository) {
        this.topicsRepository = topicsRepository;
        this.articlesRepository = articlesRepository;
    }

    //methods GET

    /*
    return all topics
     */
    @GetMapping("/topics")
    public List<Topics> listAllTopics() {
        return topicsRepository.findAll();
    }


    //methods POST
    @PostMapping("/topics") //create a new topic.//todo-not adding if topic already exists

    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {
        topicsRepository.save(topic);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }


    @PostMapping("/articles/{articleId}/topics")
    /*todo-associate the topic with the article given by articleId.
       If topic does not already exist, it is created.*/


    public ResponseEntity<Topics> associateTopic(@PathVariable Long articleId,@Valid @RequestBody Topics topic) {

        Articles article = articlesRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);

        topicsRepository.save(topic);
        topic.getArticleList().add(article);


        return ResponseEntity.status(HttpStatus.CREATED).body(topic);

    }


}
