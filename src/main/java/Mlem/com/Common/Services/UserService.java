package Mlem.com.Common.Services;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.google.gson.Gson;

import Mlem.com.Common.Entity.Provider;
import Mlem.com.Common.Entity.User;
import Mlem.com.Common.Repository.UserRepository;
import lombok.Data;


@Service
@Data
public class UserService {

	@Autowired
	private UserRepository repo;

	public void processOAuthPostLogin(User user) {
		User existUser = repo.getUserByEmail(user.getEmail(), user.getProvider());
		System.out.print("find user" + existUser);

		if (existUser == null) {

			repo.save(user);

		}

	}
	public User getUserByEmailAndProvider(String email,Provider provider) {
		return repo.getUserByEmail(email, provider);
	}

	public Optional<User> getUseById(int id) {
		return repo.findById(id);
	}
	
	public void deleteUser(int id) {
		repo.deleteById(id);
	}
	
	public User updateEnableUser(int id,boolean enable) {
		User user = repo.findById(id).get();
		user.setEnable(enable);
		return repo.save(user);
	}
	
	public User updateRoleUser(int id,int roleId) {
		User user = repo.findById(id).get();
		user.setRole(roleId);
		return repo.save(user);
	}

	public User getMyUserCookie(String userCookie) {
		User user =null;
		try {
			 Gson gson = new Gson();
			 user  = gson.fromJson(userCookie, User.class);
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println(e.getMessage());
		}
		return user;
		}

	public void setMyUserCookie(User newUser, HttpServletResponse response) throws UnsupportedEncodingException {
		Gson gson = new Gson();	
		//User object to string
		String string = gson.toJson(newUser);
		Cookie newCookie = new Cookie("MY_USER", URLEncoder.encode(string, "utf-8"));					
        newCookie.setMaxAge(24 * 60 * 60);
        newCookie.setPath("/");
        response.addCookie(newCookie);
		
	}
	
	public User getAccount(String userCookie) {
		if(userCookie.equals("defaultCookieValue")) {
			
			return null;
			
		} 
		else {
			User user = getMyUserCookie(userCookie);
			User user1 = getUserByEmailAndProvider(user.getEmail(),user.getProvider());
			user.setFullName(user.getFullName().replace('+', ' '));
			return user1;
		}
		
	}


}
