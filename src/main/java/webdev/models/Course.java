package webdev.models;

import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Course {
	 @Id
	 @GeneratedValue(strategy=GenerationType.IDENTITY)
	 private int id;
	 private String title;
	 
	 @CreationTimestamp
	 @JsonFormat(pattern = "MMMM"+" "+"dd"+","+" "+"yyyy")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date created;
	 
	 @UpdateTimestamp
	 @JsonFormat(pattern = "MMMM"+" "+"dd"+","+" "+"yyyy")
	 @Temporal(TemporalType.TIMESTAMP)
	 private Date modified;
	 @OneToMany(mappedBy="course")
	 private List<Module> modules;
	 
	public List<Module> getModules() {
		return modules;
	}
	public void setModules(List<Module> modules) {
		this.modules = modules;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getCreated() {
		return created;
	}
	public void setCreated(Date created) {
		this.created = created;
	}
	public Date getModified() {
		return modified;
	}
	public void setModified(Date modified) {
		this.modified = modified;
	}

}
