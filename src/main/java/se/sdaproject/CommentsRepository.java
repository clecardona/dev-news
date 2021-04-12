package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CommentsRepository extends JpaRepository<Comments,Long> {


    List<Comments> findByAuthorName(String authorName);
   // List<Comments> findByArticleId(Long articleId);

/*


    @Query("SELECT u FROM User u WHERE .status = articleId") // return all comments on article given by articleId.
    List<Comments> findByRelatedArticle();*/


}
