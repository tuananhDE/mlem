package Mlem.com.Common.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mlem.com.Common.Entity.GeneralCourse;
import Mlem.com.Common.Repository.GeneralCourseRepository;


@Service
public class GeneralCourseService {
	@Autowired
	private GeneralCourseRepository generalCourseRepository;

	
	public List<GeneralCourse> getAllGeneralCourse() {
		return (List<GeneralCourse>) generalCourseRepository.findAll();
	}

	
	public GeneralCourse saveGeneralCourse(GeneralCourse generalCourse) {
		return generalCourseRepository.save(generalCourse);  
	}

	
	public void deleteGeneralCourse(int id) {
		generalCourseRepository.deleteById(id);
	}

	
	public Optional<GeneralCourse> findGeneralCourseById(int id) {
		return generalCourseRepository.findById(id);
	}
}
