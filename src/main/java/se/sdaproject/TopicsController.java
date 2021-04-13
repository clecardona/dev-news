package se.sdaproject;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import se.sdaproject.api.exception.ResourceNotFoundException;

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

    /**
     * return all topics
     *
     * @return all topics
     */
    @GetMapping("/topics")
    public List<Topics> listAllTopics() {
        return topicsRepository.findAll();
    }


/**
     * todo - return all topics associated with article given by articleId.
     * @return all topics
     */

    @GetMapping("/articles/{articleId}/topics")
    public List<Topics> getTopicsFromArticleId( @PathVariable long articleId) {
        Articles article = articlesRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new); // get the article

        return article.getTopics();
    }



/**
     * todo - return all articles associated with the topic given by topicId.
     * @return all articles
     */

    @GetMapping("/topics/{topicId}/articles")
    public List<Articles> getArticlesFromTopicId( @PathVariable long topicId) {
        Topics topic = topicsRepository
                .findById(topicId)
                .orElseThrow(ResourceNotFoundException::new); // get the topic

        return topic.getArticles();
    }




    //methods POST

    /**
     * create a new topic. duplicate names not allowed.
     *
     * @param topic
     * @return process result
     */
    @PostMapping("/topics")
    public ResponseEntity<Topics> createTopic(@RequestBody Topics topic) {

        List<Topics> topicList = topicsRepository.findAll();
        boolean topicExists =false;

        for (Topics t:topicList) {
            if (t.getName().equals(topic.getName())) {
                topicExists = true;
                break;
            }
        }

        if (!topicExists){
            topicsRepository.save(topic);// topic does not exists , create it.
        }

        return ResponseEntity.status(HttpStatus.CREATED).body(topic);
    }

    /**
     * associate the topic with the article given by articleId.
     * If topic does not already exist, it is created.
     *
     * @param articleId the article related
     * @param topic topic to associate
     * @return result of process
     */

    @PostMapping("/articles/{articleId}/topics")

    public ResponseEntity<Topics> associateTopic(@PathVariable Long articleId, @Valid @RequestBody Topics topic) {

        Articles article = articlesRepository
                .findById(articleId)
                .orElseThrow(ResourceNotFoundException::new);
        //add topic to the list of topic
        List<Topics> currentTopics=article.getTopics();
        currentTopics.add(topic);
        article.setTopics(currentTopics);

        //save topic
        articlesRepository.save(article);
        return ResponseEntity.status(HttpStatus.CREATED).body(topic);

    }


    //methods PUT

/**
     * update the given topic.
     *
     * @param id  the given id
     * @param upTopic the updated  comment
     * @return action processed
     */

    @PutMapping("/topics/{id}")
    public ResponseEntity<Topics> updateTopicById(@PathVariable Long id, @Valid @RequestBody Topics upTopic) {
        Topics topic = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);

        //persist the related articles
        upTopic.setArticles(topic.getArticles());
        upTopic.setId(id);//
        topicsRepository.save(upTopic);
        return ResponseEntity.ok(upTopic);
    }


    //methods DELETE


/**
     * delete the given topic.
     *
     * @param id the given id
     * @return action processed
     */

//todo - don't delete the article when topic is suppressed

    @DeleteMapping("/topics/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public ResponseEntity<Topics> deleteTopicById(@PathVariable Long id) {
        Topics topic = topicsRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        topicsRepository.delete(topic);

        return ResponseEntity.ok(topic);
    }


}
