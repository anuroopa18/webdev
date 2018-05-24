package webdev.services;

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
import webdev.models.Course;
import webdev.repositories.CourseRepository;

@RestController
@CrossOrigin(origins="*",maxAge=3600)
public class CourseService {
	@Autowired
	CourseRepository repository;
	
	@PostMapping("/api/course")
	public Course createCourse(@RequestBody Course course) {
		return repository.save(course);
	}
	
	@DeleteMapping("/api/course/{id}")
	public void deleteCourse(@PathVariable("id") int cid) {
		 repository.deleteById(cid);
	}

	@GetMapping("/api/course")
	public List<Course> findAllCourses() {
		return (List<Course>) repository.findAll();
	}
     
	@GetMapping("/api/course/{id}")
	public Course findCourseById(@PathVariable("id") int cid) {
		Optional<Course> data = repository.findById(cid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@PutMapping("/api/course/{id}")
	public Course updateCourse(@PathVariable("id") int cid, @RequestBody Course newCourse) {
		Optional<Course> data = repository.findById(cid);
		if(data.isPresent()) {
			Course course = data.get();
			course.setTitle(newCourse.getTitle());
			course.setCreated(newCourse.getCreated());
			course.setModified(newCourse.getModified());
			repository.save(newCourse);
			return newCourse;
		}
		return null;
	}


}
