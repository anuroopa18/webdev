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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import webdev.models.Exam;
import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.ExamRepository;
import webdev.repositories.LessonRepository;



@RestController
@CrossOrigin(origins = "*")
public class ExamService {
	
	@Autowired
	ExamRepository examRepository;
	
	@Autowired
	LessonRepository lessonRepository;
	
	@GetMapping("/api/exam")
	public List<Exam> findAllExams() {
		return (List<Exam>) examRepository.findAll();
	}
	
	@GetMapping("/api/exam/{id}")
	public Exam findExamById(@PathVariable("id") int eid) {
		Optional<Exam> data = examRepository.findById(eid);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/lesson/{lid}/exam")
	public List<Widget> findAllExamsForLesson(
			@PathVariable("lid") int lessonId) {
		List<Widget> list = new ArrayList<Widget>();
		List<Widget> wlist = new ArrayList<Widget>();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			wlist.addAll(lesson.getWidget()) ;
			for(Widget w: wlist) {
				if(w.getTyp() != "Exam")
				{
                     wlist.remove(w);
				}
				
			}
			
			return wlist;
			
		}
		return list;		
	}
	
	@DeleteMapping("/api/exam/{id}")
	public void deleteExam(@PathVariable("id") int eid)
	{
		examRepository.deleteById(eid);
	}

	@PostMapping("/api/lesson/{lid}/exam")
	public Exam createExam(
			@PathVariable("lid") int lessonId,
			@RequestBody Exam newExam) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);

		if(data.isPresent()) {
			Lesson lesson = data.get();
			newExam.setLesson(lesson);
			return examRepository.save(newExam);
		}
		return null;		
	}

	
	
}