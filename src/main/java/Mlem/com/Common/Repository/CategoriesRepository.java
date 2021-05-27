package Mlem.com.Common.Repository;

import org.springframework.data.jpa.repository.JpaRepository;

import Mlem.com.Common.Entity.Categories;
import Mlem.com.Common.Entity.GeneralCourse;

public interface CategoriesRepository extends JpaRepository< Categories, Integer> {

}

