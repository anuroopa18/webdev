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
import webdev.models.TrueOrFalseExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.TrueOrFalseExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class TrueOrFalseExamQuestionService {
	
	@Autowired
	TrueOrFalseExamQuestionRepository trueOrFalseExamQuestionRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{eid}/truefalse")
	public TrueOrFalseExamQuestion createTrueOrFalseExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody TrueOrFalseExamQuestion newTrueOrFalseExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newTrueOrFalseExamQuestion.setExam(exam);
			return trueOrFalseExamQuestionRepository.save(newTrueOrFalseExamQuestion);
		}
		return null;		
	}
	
	@GetMapping("/api/truefalse")
	public List<TrueOrFalseExamQuestion> findAllTrueOrFalseQuestions() {
		return (List<TrueOrFalseExamQuestion>) trueOrFalseExamQuestionRepository.findAll();
	}
	
	@GetMapping("/api/truefalse/{id}")
	public TrueOrFalseExamQuestion findTrueOrFalseExamQuestionById(@PathVariable("id") int id) {
		Optional<TrueOrFalseExamQuestion> data = trueOrFalseExamQuestionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{eid}/truefalse")
	public List<BaseExamQuestion> findAllTrueOrfalseQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<BaseExamQuestion> list = new ArrayList<BaseExamQuestion>();
		List<BaseExamQuestion> tlist = new ArrayList<BaseExamQuestion>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
			for(BaseExamQuestion beq: tlist) {
				if(beq.getType() != "TrueOrFalse")
				{
					tlist.remove(beq);
				}
				
			}
			
			return tlist;
		}
		return list;		
	}
	
	@DeleteMapping("/api/truefalse/{id}")
	public void deleteTrueOrfalseQuestion(@PathVariable("id") int id)
	{
		trueOrFalseExamQuestionRepository.deleteById(id);
	}


}
