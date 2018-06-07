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
import webdev.models.Exam;
import webdev.models.FillInTheBlanksExamQuestion;
import webdev.repositories.ExamRepository;
import webdev.repositories.FillInTheBlanksExamQuestionRepository;

@RestController
@CrossOrigin(origins = "*")
public class FillInTheBlanksExamQuestionService {
	
	@Autowired
	FillInTheBlanksExamQuestionRepository fillIntheBlanksExamQuestionRepository;
	
	@Autowired
	ExamRepository examRepository;
	
	@PostMapping("/api/exam/{eid}/blanks")
	public FillInTheBlanksExamQuestion createFillInTheBlanksExamQuestion(
			@PathVariable("eid") int examId,
			@RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion) {
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			newFillInTheBlanksExamQuestion.setId(Integer.MAX_VALUE);
			newFillInTheBlanksExamQuestion.setExam(exam);
			return fillIntheBlanksExamQuestionRepository.save(newFillInTheBlanksExamQuestion);
		}
		return null;		
	}
	@GetMapping("/api/blanks")
	public List<FillInTheBlanksExamQuestion> findAllFillInTheBlanksExamQuestions() {
		return (List<FillInTheBlanksExamQuestion>) fillIntheBlanksExamQuestionRepository.findAll();
	}
	
	@GetMapping("/api/blanks/{id}")
	public FillInTheBlanksExamQuestion findFillInTheBlanksExamQuestion(@PathVariable("id") int id) {
		Optional<FillInTheBlanksExamQuestion> data = fillIntheBlanksExamQuestionRepository.findById(id);
		if(data.isPresent()) {
			return data.get();
		}
		return null;
	}
	
	@GetMapping("/api/exam/{eid}/blanks")
	public List<BaseExamQuestion> findAllFillInTheBlanksExamQuestionsForExam(
			@PathVariable("eid") int examId) {
		List<BaseExamQuestion> list = new ArrayList<BaseExamQuestion>();
		List<BaseExamQuestion> tlist = new ArrayList<BaseExamQuestion>();
		Optional<Exam> data = examRepository.findById(examId);
		if(data.isPresent()) {
			Exam exam = data.get();
			tlist.addAll(exam.getQuestions()) ;
			for(BaseExamQuestion beq: tlist) {
				if(beq.getType() != "FillInTheBlanks")
				{
					tlist.remove(beq);
				}
				
			}
			
			return tlist;
		}
		return list;		
	}
	
	@DeleteMapping("/api/blanks/{id}")
	public void deleteFillInTheBlanksExamQuestion(@PathVariable("id") int id)
	{
		fillIntheBlanksExamQuestionRepository.deleteById(id);
	}
	
	@PutMapping("/api/blanks/{id}")
	public FillInTheBlanksExamQuestion updateFillInTheBlanksExamQuestion(@PathVariable("id") int qid, @RequestBody FillInTheBlanksExamQuestion newFillInTheBlanksExamQuestion) {
		Optional<FillInTheBlanksExamQuestion> data = fillIntheBlanksExamQuestionRepository.findById(qid);
		if(data.isPresent()) {
			FillInTheBlanksExamQuestion fillInTheBlanksExamQuestion = data.get();
			if(newFillInTheBlanksExamQuestion.getTitle() != null && !newFillInTheBlanksExamQuestion.getTitle().equals("") ) {
				fillInTheBlanksExamQuestion.setTitle(newFillInTheBlanksExamQuestion.getTitle());
			}
			if (newFillInTheBlanksExamQuestion.getDescription() != null && !newFillInTheBlanksExamQuestion.getDescription().equals("")) {
				fillInTheBlanksExamQuestion.setDescription(newFillInTheBlanksExamQuestion.getDescription());
			}
			if(newFillInTheBlanksExamQuestion.getPoints() != 0) {
				fillInTheBlanksExamQuestion.setPoints(newFillInTheBlanksExamQuestion.getPoints());
			}
			if (newFillInTheBlanksExamQuestion.getVariables() != null && !newFillInTheBlanksExamQuestion.getVariables().equals("")) {
				fillInTheBlanksExamQuestion.setVariables(newFillInTheBlanksExamQuestion.getVariables());
			}
			
			fillIntheBlanksExamQuestionRepository.save(fillInTheBlanksExamQuestion);
			return fillInTheBlanksExamQuestion;
		}
		return null;
	}

	
	

}
