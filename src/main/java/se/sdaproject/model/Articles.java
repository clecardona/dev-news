package se.sdaproject.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.List;


@Entity
public class Articles {


    // todo - make associated tables visible


    // fields : id, title, body , authorName.
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    private String title;
    private String body;
    private String authorName;


    @OneToMany(mappedBy = "relatedArticle", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comments> comments;

    @ManyToMany(cascade = CascadeType.ALL) //  owning side
    @JsonIgnore
    private List<Topics> topics;



    // constructors
    public Articles() {

    }

    public Articles(long id, String title, String body, String authorName, List<Comments> comments, List<Topics> topics) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.authorName = authorName;

        this.comments = comments;
        this.topics = topics;
    }

    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public String getAuthorName() {
        return authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public List<Comments> getComments() {
        return comments;
    }

    public void setComments(List<Comments> comments) {
        this.comments = comments;
    }

    public List<Topics> getTopics() {
        return topics;
    }

    public void setTopics(List<Topics> topics) {
        this.topics = topics;
    }
}
