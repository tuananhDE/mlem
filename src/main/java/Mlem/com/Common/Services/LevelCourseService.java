package Mlem.com.Common.Services;

import java.util.ArrayList;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mlem.com.Common.Entity.LevelCourse;
import Mlem.com.Common.Repository.LevelCourseRepositpry;

@Service
public class LevelCourseService {
	@Autowired
	private LevelCourseRepositpry levelCourseRepositpry;
	
	public ArrayList<LevelCourse> getAllLevelCourse() {
		return (ArrayList<LevelCourse>) levelCourseRepositpry.findAll();
	}
		
	public LevelCourse saveCategories(LevelCourse categories) {
		return levelCourseRepositpry.save(categories);  
	}

	
	public void deleteLevelCourse(int id) {
		levelCourseRepositpry.deleteById(id);
	}

	
	public Optional<LevelCourse> findLevelCourseById(int id) {
		return levelCourseRepositpry.findById(id);
	}

}
