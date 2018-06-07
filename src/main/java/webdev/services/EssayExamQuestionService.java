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
import webdev.models.BaseExamQuestion;
import webdev.models.EssayExamQuestion;
import webdev.models.Exam;
import webdev.repositories.EssayExamQuestionRepository;
import webdev.repositories.ExamRepository;

@RestController
@CrossOrigin(origins = "*")
public class EssayExamQuestionService {
	
	@Autowired
	EssayExamQuestionRepository essayExamQuestionRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{eid}/essay")
	public EssayExamQuestion createEssayExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newEssayExamQuestion.setExam(exam);
			return essayExamQuestionRepository.save(newEssayExamQuestion);
		}
		return null;		
	}
	
	
	
	@GetMapping("/api/essay")
	public List<EssayExamQuestion> findAllEssayExamQuestions() {
		return (List<EssayExamQuestion>) essayExamQuestionRepository.findAll();
	}
	
	@GetMapping("/api/essay/{id}")
	public EssayExamQuestion findEssayExamQuestion(@PathVariable("id") int id) {
		Optional<EssayExamQuestion> data = essayExamQuestionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{eid}/essay")
	public List<BaseExamQuestion> findAllEssayExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<BaseExamQuestion> list = new ArrayList<BaseExamQuestion>();
		List<BaseExamQuestion> tlist = new ArrayList<BaseExamQuestion>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
			for(BaseExamQuestion beq: tlist) {
				if(beq.getType() != "Essay")
				{
					tlist.remove(beq);
				}
				
			}
			
			return tlist;
		}
		return list;		
	}
	
	@DeleteMapping("/api/essay/{id}")
	public void deleteEssayExamQuestion(@PathVariable("id") int id)
	{
		essayExamQuestionRepository.deleteById(id);
	}
	
	@PutMapping("/api/essay/{id}")
	public EssayExamQuestion updateEssayExamQuestion(@PathVariable("id") int qid, @RequestBody EssayExamQuestion newEssayExamQuestion) {
		Optional<EssayExamQuestion> data = essayExamQuestionRepository.findById(qid);
		if(data.isPresent()) {
			EssayExamQuestion essayExamQuestion = data.get();
			if(newEssayExamQuestion.getTitle() != null && !newEssayExamQuestion.getTitle().equals("") ) {
				essayExamQuestion.setTitle(newEssayExamQuestion.getTitle());
			}
			if (newEssayExamQuestion.getDescription() != null && !newEssayExamQuestion.getDescription().equals("")) {
				essayExamQuestion.setDescription(newEssayExamQuestion.getDescription());
			}
			if(newEssayExamQuestion.getPoints() != 0) {
				essayExamQuestion.setPoints(newEssayExamQuestion.getPoints());
			}
			
			essayExamQuestionRepository.save(essayExamQuestion);
			return essayExamQuestion;
		}
		return null;
	}
	

}
