package Mlem.com.Common.Entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Course")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Course {
	@Id
	@Column(name = "course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "general_course_id")
	private Long generalCourseId;
	@Column(name = "name_course")
	private String nameCourse;
	@Column(name = "des_course")
	private String desCourse;
	@Column(name = "create_date")
	private Date createDate;
}
