package se.sdaproject;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Entity
public class Topics {


    // fields : id, name, articleList
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;


    @ManyToMany
    private Set<Articles> articleList;

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

    public Set<Articles> getArticleList() {
        return articleList;
    }

    public void setArticleList(Set<Articles> articleList) {
        this.articleList = articleList;
    }
}
