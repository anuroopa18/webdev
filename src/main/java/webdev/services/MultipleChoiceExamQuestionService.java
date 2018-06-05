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
import webdev.models.MultipleChoiceExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.MultipleChoiceExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class MultipleChoiceExamQuestionService {
	
	@Autowired
	MultipleChoiceExamQuestionRepository multipleChoiceExamRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{eid}/choice")
	public MultipleChoiceExamQuestion createMultipleChoiceExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody MultipleChoiceExamQuestion newMultipleChoiceExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newMultipleChoiceExamQuestion.setExam(exam);
			return multipleChoiceExamRepository.save(newMultipleChoiceExamQuestion);
		}
		return null;		
	}
	
	@GetMapping("/api/choice")
	public List<MultipleChoiceExamQuestion> findAllMultipleChoiceExamQuestions() {
		return (List<MultipleChoiceExamQuestion>) multipleChoiceExamRepository.findAll();
	}
	
	@GetMapping("/api/choice/{id}")
	public MultipleChoiceExamQuestion findMultipleChoiceExamQuestionById(@PathVariable("id") int id) {
		Optional<MultipleChoiceExamQuestion> data = multipleChoiceExamRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{eid}/choice")
	public List<BaseExamQuestion> findAllMultipleChoiceExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<BaseExamQuestion> list = new ArrayList<BaseExamQuestion>();
		List<BaseExamQuestion> tlist = new ArrayList<BaseExamQuestion>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
			for(BaseExamQuestion beq: tlist) {
				if(beq.getType() != "MultipleChoice")
				{
					tlist.remove(beq);
				}
				
			}
			
			return tlist;
		}
		return list;		
	}
	
	@DeleteMapping("/api/choice/{id}")
	public void deleteMultipleChoiceExamQuestion(@PathVariable("id") int id)
	{
		multipleChoiceExamRepository.deleteById(id);
	}
	

}
