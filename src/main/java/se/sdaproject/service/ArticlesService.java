package se.sdaproject.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import se.sdaproject.model.Articles;
import se.sdaproject.repository.ArticlesRepository;
import se.sdaproject.api.exception.ResourceNotFoundException;

@Service
public class ArticlesService {

    ArticlesRepository articlesRepository;

    @Autowired
    public ArticlesService(ArticlesRepository articlesRepository) {
        this.articlesRepository = articlesRepository;
    }


    public Articles updateArticle(Long id,Articles updatedArticle){

        articlesRepository.findById(id).orElseThrow(ResourceNotFoundException::new);
        updatedArticle.setId(id);
        Articles article = articlesRepository.save(updatedArticle);

        return article;

    }







}
