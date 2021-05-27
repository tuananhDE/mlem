package Mlem.com.Common.Services;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;

import Mlem.com.Common.Entity.Provider;
import Mlem.com.Common.Entity.Role;
import Mlem.com.Common.Entity.User;
import Mlem.com.Common.Repository.RoleRepository;
import Mlem.com.Common.Repository.UserRepository;

@Service
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	UserRepository userRepository;

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		OAuth2User user = super.loadUser(userRequest);
		System.out.println("CustomOAuth2UserService invoked");
		List<GrantedAuthority> grantList = new ArrayList<GrantedAuthority>();

		// At this point, you would load your data (e.g. from database) and modify the
		// authorities as you wish
		// For the sake of testing, we'll just add the role 'ADMIN' to the user

		String email = user.getAttribute("email");
		User u = userRepository.getUserByEmail(email, Provider.GOOGLE);
		int roleID = 5;

		if (u != null) {
			roleID = u.getRole();
		}

		Optional<Role> role = roleRepository.findById(roleID);
		String roleName = role.get().getName();
		switch (roleName) {
		case "Admin":
			grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantList.add(new SimpleGrantedAuthority("ROLE_MANAGER_CATEGORIES"));
			grantList.add(new SimpleGrantedAuthority("ROLE_MANAGER_COURSE"));
			grantList.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
			grantList.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
			break;
		case "Teacher":
			grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantList.add(new SimpleGrantedAuthority("ROLE_TEACHER"));
			break;
		case "Manager Categories":
			grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantList.add(new SimpleGrantedAuthority("ROLE_MANAGER_CATEGORIES"));
			break;
		case "Manager Course":
			grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
			grantList.add(new SimpleGrantedAuthority("ROLE_MANAGER_COURSE"));
			break;
		default:
			grantList.add(new SimpleGrantedAuthority("ROLE_USER"));
			break;
		}

		Map<String, Object> attributes = user.getAttributes();

		try {
			return new DefaultOAuth2User(grantList, attributes, "sub");
		} catch (Exception e) {
			return new DefaultOAuth2User(grantList, attributes, "id");
		}
	}

}