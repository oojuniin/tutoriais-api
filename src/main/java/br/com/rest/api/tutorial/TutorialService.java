package br.com.rest.api.tutorial;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class TutorialService {

	@Autowired
	private TutorialRepository tutorialRepository;

	@Transactional(readOnly = true)
	public List<TutorialModel> getAll(String title) {
		List<TutorialModel> tutorials = new ArrayList<TutorialModel>();

		if (title == null) {
			tutorialRepository.findAll().forEach(tutorials::add);
		} else {
			tutorialRepository.findByTitleContaining(title).forEach(tutorials::add);
		}

		return tutorials;
	}
	
	@Transactional
	public List<TutorialModel> findByPublished() {
		List<TutorialModel> tutorials = tutorialRepository.findByPublished(true);
		
		return tutorials;
	}

	@Transactional(readOnly = true)
	public Optional<TutorialModel> getById(Long id) {
		Optional<TutorialModel> tutorialData = tutorialRepository.findById(id);

		return tutorialData;
	}

	@Transactional
	public TutorialModel createTutorial(TutorialModel tutorial) {
		TutorialModel _tutorial = tutorialRepository
				.save(new TutorialModel(tutorial.getTitle(), tutorial.getDescription(), false));
		return _tutorial;
	}

	@Transactional
	public TutorialModel updateTutorial(Long id, TutorialModel tutorial) {
		Optional<TutorialModel> tutorialData = tutorialRepository.findById(id);
		TutorialModel _tutorial = null;

		if (tutorialData.isPresent()) {
			_tutorial = tutorialData.get();
			_tutorial.setTitle(tutorial.getTitle());
			_tutorial.setDescription(tutorial.getDescription());
			_tutorial.setPublished(tutorial.isPublished());
			tutorialRepository.save(_tutorial);
		}

		return _tutorial;
	}
	
	@Transactional
	public void deleteTutorial(Long id) {
		tutorialRepository.deleteById(id);
	}
	
	@Transactional
	public void deleteAllTutorials() {
		tutorialRepository.deleteAll();
	}
}
