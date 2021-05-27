package Mlem.com.Common.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import Mlem.com.Common.Entity.Categories;
import Mlem.com.Common.Entity.GeneralCourse;
import Mlem.com.Common.Repository.CategoriesRepository;

@Service
public class CategoriesService {
	@Autowired
	private CategoriesRepository categoriesRepository;
	
	public ArrayList<Categories> getAllCategories() {
		return (ArrayList<Categories>) categoriesRepository.findAll();
	}

	
	public Categories saveCategories(Categories categories) {
		return categoriesRepository.save(categories);  
	}

	
	public void deleteCategories(int id) {
		categoriesRepository.deleteById(id);
	}

	
	public Optional<Categories> findCategoriesById(int id) {
		return categoriesRepository.findById(id);
	}

}
