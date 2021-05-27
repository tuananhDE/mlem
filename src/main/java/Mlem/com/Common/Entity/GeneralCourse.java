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
@Table(name = "generalcourse")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class GeneralCourse {
	@Id
	@Column(name = "general_course_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	@Column(name = "lc_id")
	private long lcId;
	@Column(name = "cate_id")
	private long cateId;
	@Column(name = "manager_id")
	private long managerId;
	@Column(name = "name_general_course")
	private String nameGeneralCourse;
	@Column(name = "des_general_course")
	private String desGeneralCourse;
	@Column(name = "price_course")
	private int priceCourse;
	@Column(name = "sale_price")
	private int salePrice;
	@Column(name = "create_date")
	private Date createDate;
	@Column(name = "content_general_course")
	private String contantGeneralCourse;
	@Column(name = "image_course")
	private String image;
	@Column(name = "urlvideo_course")
	private String urlVideoCourse;
	@Column(name = "urlyotube_course")
	private String urlYoutubeCourse;
}


