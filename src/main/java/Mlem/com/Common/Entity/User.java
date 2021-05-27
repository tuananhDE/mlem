package Mlem.com.Common.Entity;


import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "Accounts")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class User{
@Id
@Column(name = "account_id")
@GeneratedValue(strategy = GenerationType.IDENTITY)
private int id;
@Column(name = "role_id")
private int role;

private String email;
@Column(name = "full_name")
private String fullName;
private String gender;
private boolean enable;
private String avatar;
@Column(name = "auth_provider")
private Provider provider;

}
