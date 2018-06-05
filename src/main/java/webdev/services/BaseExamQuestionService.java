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
import webdev.models.BaseExamQuestion;
import webdev.models.Exam;
import webdev.repositories.BaseExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class BaseExamQuestionService {
	
	@Autowired
	BaseExamQuestionRepository baseExamQuestionRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{eid}/base")
	public BaseExamQuestion createBaseExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody BaseExamQuestion newBaseExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newBaseExamQuestion.setExam(exam);
			return baseExamQuestionRepository.save(newBaseExamQuestion);
		}
		return null;		
	}
	
	@GetMapping("/api/base")
	public List<BaseExamQuestion> findAllBaseExamQuestions() {
		return (List<BaseExamQuestion>) baseExamQuestionRepository.findAll();
	}
	
	@GetMapping("/api/base/{id}")
	public BaseExamQuestion findBaseExamQuestionById(@PathVariable("id") int id) {
		Optional<BaseExamQuestion> data = baseExamQuestionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{eid}/base")
	public List<BaseExamQuestion> findAllBaseExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<BaseExamQuestion> list = new ArrayList<BaseExamQuestion>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
		    return exam.getQuestions();
		}
		return list;		
	}
	
	@DeleteMapping("/api/base/{id}")
	public void BaseExamQuestion(@PathVariable("id") int id)
	{
		baseExamQuestionRepository.deleteById(id);
	}
	

}
