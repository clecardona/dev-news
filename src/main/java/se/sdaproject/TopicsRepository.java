package se.sdaproject;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TopicsRepository extends JpaRepository<Topics,Long>{

    List<Topics> findByName(String Name);
    List<Topics> existsByName(String Name);
}
