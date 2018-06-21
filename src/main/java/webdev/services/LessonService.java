package webdev.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import webdev.models.Lesson;
import webdev.models.Module;
import webdev.repositories.LessonRepository;
import webdev.repositories.ModuleRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class LessonService {
	@Autowired
	LessonRepository repository;
	
	@Autowired
	ModuleRepository moduleRepository;
	
	@PostMapping("/api/course/{cid}/module/{mid}/lesson")
	public Lesson createLesson(@PathVariable("cid") int courseId,
			@PathVariable("mid") int moduleId,
			@RequestBody Lesson newLesson) {
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			newLesson.setModule(module);
			return repository.save(newLesson);
		}
		return null;		
	}
	
	@DeleteMapping("/api/lesson/{id}")
	public void deleteLesson(@PathVariable("id") int lid) {
		 repository.deleteById(lid);
	}
	@GetMapping("/api/lesson")
	public List<Lesson> findAllLessons() {
		return (List<Lesson>) repository.findAll();
	}
	@GetMapping("/api/lesson/{id}")
	public Lesson findLessonById(@PathVariable("id") int lid) {
		Optional<Lesson> data = repository.findById(lid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	@GetMapping("/api/module/{mid}/lesson")
	public List<Lesson> findAllLessonsForModule(
			@PathVariable("mid") int moduleId) {
		List<Lesson> list = new ArrayList<>();
		Optional<Module> data = moduleRepository.findById(moduleId);
		if(data.isPresent()) {
			Module module = data.get();
			return module.getLessons();
		}
		return list;		
	}
	@PutMapping("/api/lesson/{id}")
	public Lesson updateLesson(@PathVariable("id") int LessonId, @RequestBody Lesson newLesson) {
		Optional<Lesson> data = repository.findById(LessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			lesson.setTitle(newLesson.getTitle());
			lesson.setModule(newLesson.getModule());
			repository.save(newLesson);
			return newLesson;
		}
		return null;
	}

}
