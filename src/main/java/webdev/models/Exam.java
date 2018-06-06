package webdev.models;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Exam extends Widget {
	@OneToMany(mappedBy="exam")
	@JsonIgnore
	private List<BaseExamQuestion> questions;
	private String examTitle;
	private int examPoints=0;
	
	public int getExamPoints() {
		return examPoints;
	}
	public void setExamPoints(int examPoints) {
		this.examPoints = examPoints;
	}
	public String getExamTitle() {
		return examTitle;
	}
	public void setExamTitle(String examTitle) {
		this.examTitle = examTitle;
	}
	public List<BaseExamQuestion> getQuestions() {
		return questions;
	}
	public void setQuestions(List<BaseExamQuestion> questions) {
		this.questions = questions;
	}
}