package se.sdaproject;

import javax.persistence.*;

import java.util.List;


@Entity
public class Topics {


    // fields : id, name, articles
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @ManyToMany (mappedBy = "topics",cascade = CascadeType.ALL)
    private List<Articles> articles;

    //constructor



    //getters and setters

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Articles> getArticles() {
        return articles;
    }

    public void setArticles(List<Articles> articles) {
        this.articles = articles;
    }
}
