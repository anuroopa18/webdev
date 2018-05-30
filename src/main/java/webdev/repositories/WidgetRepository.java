package webdev.repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import webdev.models.Lesson;
import webdev.models.Widget;

public interface WidgetRepository extends CrudRepository<Widget, Integer> {
	
	@Query("SELECT w FROM Widget w WHERE w.lesson=:lesson ORDER BY w.id ASC")
	List<Widget> findAllWidgetsForLessons(@Param("lesson") Lesson lesson);

}
