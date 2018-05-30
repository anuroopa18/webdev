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


import webdev.models.Lesson;
import webdev.models.Widget;
import webdev.repositories.LessonRepository;
import webdev.repositories.WidgetRepository;

@RestController
@CrossOrigin(origins = "*", maxAge = 3600)
public class WidgetService {

	@Autowired
	WidgetRepository repository;

	@Autowired
	LessonRepository lessonRepository;

	@GetMapping("/api/widget")
	public List<Widget> findAllWidgets() {
		return (List<Widget>) repository.findAll();
	}

	@GetMapping("/api/widget/{widgetId}")
	public Widget findWidgetById(@PathVariable("widgetId") int wid) {
		Optional<Widget> data = repository.findById(wid);
		if (data.isPresent()) {
			return data.get();
		}
		return null;
	}

	@PutMapping("/api/widget/{widgetId}")
	public Widget updateWidget(@PathVariable("widgetId") int widgetId, @RequestBody Widget newWidget) {
		Optional<Widget> data = repository.findById(widgetId);
		if (data.isPresent()) {
			Widget widget = data.get();
			widget.setClassName(newWidget.getClassName());
			widget.setHeight(newWidget.getHeight());
			widget.setHref(newWidget.getHref());
			widget.setListItems(newWidget.getListItems());
			widget.setListType(newWidget.getListType());
			widget.setOrder_no(newWidget.getOrder_no());
			widget.setSize(newWidget.getSize());
			widget.setSrc(newWidget.getSrc());
			widget.setStyle(newWidget.getStyle());
			widget.setText(newWidget.getText());
			widget.setWidgetType(newWidget.getWidgetType());
			widget.setWidth(newWidget.getWidth());
			widget.setName(newWidget.getName());
			repository.save(newWidget);
			return newWidget;
		}
		return null;
	}

	@DeleteMapping("/api/widget/{widgetId}")
	public void deleteWidget(@PathVariable("widgetId") int widgetId) {
		repository.deleteById(widgetId);
	}

	@PostMapping("/api/lesson/{lessonId}/widget")
	public Widget createWidget(@PathVariable("lessonId") int lessonId, @RequestBody Widget newWidget) {
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			newWidget.setLesson(lesson);
			return repository.save(newWidget);
		}
		return null;
	}
	
	
	@PostMapping("/api/lesson/{lessonId}/widget/save")
	public void saveWidgets(@PathVariable("lessonId") int lessonId, @RequestBody List<Widget> newWidgets) {
		List<Widget> widgetsToDelete =findAllWidgetsForLesson(lessonId);
		repository.deleteAll(widgetsToDelete);
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if (data.isPresent()) {
			Lesson lesson = data.get();
			for(Widget widget: newWidgets) {
				widget.setLesson(lesson);
			     repository.save(widget);
			}
		}
		
	}
	
	@GetMapping("/api/lesson/{lessonId}/widget")
	public List<Widget> findAllWidgetsForLesson(
			@PathVariable("lessonId") int lessonId) {
		List<Widget> list = new ArrayList<Widget>();
		Optional<Lesson> data = lessonRepository.findById(lessonId);
		if(data.isPresent()) {
			Lesson lesson = data.get();
			return repository.findAllWidgetsForLessons(lesson);
		}
		return list;		
	}

}
