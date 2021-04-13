# Dev News

## Introduction
This assignment is all about building the backend API for a developer news site where users can create articles, comment them and post their reactions (likes, dislikes). It doesn't require a graphical user interface so it is enough to be able to make requests and get plain json text responses via curl/Postman. 

## Learning Objectives
* Understand the basic structure of a Spring application.
* Practice building, testing and consuming rest APIs.
* Learn about data modelling for real world applications.
* Learn how to interact with a relational database using an ORM tool implementing Spring JPA (Hibernate).

## Assignment

### Exercise 1 : Articles
Article is the core entity in our project. It represents a news article with a unique **id**, **title**, **body** (article text content) and the 
**authorName**.

**Create an Article model and implement an API with the following endpoints:**

| HTTP Method | HTTP Path | Action | Status|
| ------------|-----------|--------|--------|
| `GET`    | `/articles`      | return all articles. | **OK** |
| `GET`    | `/articles/{id}` | return a specific article based on the provided id.|**OK** |
| `POST`   | `/articles`      | create a new article.|**OK** |
| `PUT`    | `/articles/{id}` | update the given article.|**OK** |
| `DELETE` | `/articles/{id}` | delete the given article.|**OK** |


### Exercise 2 : Comments
We want our visitors to be able to comment the different articles with a unique **id**, **body**, **authorName** (for the comment), and **article**
on which the comment was posted. Each article can have zero or more comments. 

**Create a Comment model and implement an API with the following endpoints :**

| HTTP Method | HTTP Path | Action | Status |
| ------------|-----------|--------|--------|
| `GET`    | `/articles/{articleId}/comments`    | return all comments on article given by `articleId`. |**OK** |
| `GET`    | `/comments?authorName={authorName}` | return all comments made by author given by `authorName`. |**OK** |
| `POST`   | `/articles/{articleId}/comments`    | create a new comment on article given by `articleId`. |**OK** |
| `PUT`    | `/comments/{id}`                    | update the given comment. |**OK** |
| `DELETE` | `/comments/{id}`                    | delete the given comment. |**OK** |


### Exercise 3 : Topics
We want to categorize our articles by topics. Each topic can be applied to zero or many articles and each article can have zero or many topics.

**Create a Topic model and implement an API with following endpoint :**


| HTTP Method | HTTP Path | Action | Status |
| ------------|-----------|--------|--------|
| `GET`    | `/topics` | return all topics. |**OK** |
| `GET`    | `/articles/{articleId}/topics` | return all topics associated with article given by `articleId`. |**OK** |
| `POST`   | `/articles/{articleId}/topics` | associate the topic with the article given by `articleId`. If topic does not already exist, it is created. |**OK , but duplicates to be fixed** |
| `POST`   | `/topics` | create a new topic. |**OK** |
| `PUT`    | `/topics/{id}` | update the given topic. |**OK** |
| `DELETE` | `/topics/{id}` | delete the given topic. |**TODO** |
| `DELETE` | `/articles/{articleId}/topics/{topicId}` | delete the association of a topic for the given article. The topic & article themselves remain. |**OK** |
| `GET`    | `/topics/{topicId}/articles` | return all articles associated with the topic given by `topicId`. |**OK** |

### Exercise 4 (Bonus) : Reactions

To make our application more interactive we might want to add the ability to add article and comment reactions (likes, dislikes, ...).
Go ahead and implement reactions in your application. You're free to choose how the model should look like so try to draw it out beforehand and think of what kind of relationship will the reactions have to the articles and comments respectively.
