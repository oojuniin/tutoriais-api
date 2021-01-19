package br.com.rest.api.tutorial;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TutorialRepository extends JpaRepository<TutorialModel, Long>{

	List<TutorialModel> findByPublished(boolean published);
	
	List<TutorialModel> findByTitleContaining(String title);
}
