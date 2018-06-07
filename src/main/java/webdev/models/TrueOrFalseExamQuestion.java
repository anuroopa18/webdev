package webdev.models;

import javax.persistence.Entity;

@Entity
public class TrueOrFalseExamQuestion extends BaseExamQuestion {
	private boolean isTrue;

	public boolean getisTrue() {
		return isTrue;
	}
	public void setisTrue(boolean isTrue) {
		this.isTrue = isTrue;
	}
	
}
