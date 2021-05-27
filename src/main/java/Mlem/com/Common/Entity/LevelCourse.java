package Mlem.com.Common.Entity;

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
@Table(name = "Levelcourse")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Data

public class LevelCourse {
	@Id
	@Column(name = "lc_id")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	@Column(name = "level_name")
	private String levelName;
	
	
	

}
