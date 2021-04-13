package se.sdaproject.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se.sdaproject.model.Articles;

@Repository
public interface ArticlesRepository extends JpaRepository<Articles,Long>{


}
