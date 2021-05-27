package Mlem.com.Common.Security;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import Mlem.com.Common.Entity.Provider;
import Mlem.com.Common.Entity.User;
import Mlem.com.Common.Services.CustomOAuth2UserService;
import Mlem.com.Common.Services.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {


	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
	
	@Bean
	public DaoAuthenticationProvider authenticationProvider() {
		DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
		authProvider.setUserDetailsService(userDetailsService());
		authProvider.setPasswordEncoder(passwordEncoder());
		
		return authProvider;
	}

	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		auth.authenticationProvider(authenticationProvider());
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		//login by cookie
		User user =userService.getMyUserCookie("MY_USER");
			if (user!=null) {
				if (user.getRole()==1) {
					http.authorizeRequests()
					.antMatchers("/admin/**").permitAll();
				}else if(user.getRole()==2) {
					http.authorizeRequests()
					.antMatchers("/manager_course/**").permitAll();
				}else if(user.getRole()==3) {
					http.authorizeRequests()
					.antMatchers("/manager_categories/**").permitAll();
				}
				else if(user.getRole()==4) {
					http.authorizeRequests()
					.antMatchers("/teacher/**").permitAll();
				}
				else {
					http.authorizeRequests()
					.antMatchers("/**").permitAll();
				}
			}
			//public page
			http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/",
					"/generalCourse/create",
					"/login",
					"/oauth/**",
					"/css/**",
					"/images/**",
					"/js/**",
					"/vendor/**",
					"/manager/**").permitAll()
			//page must login by user role
			.antMatchers("/view")
			.access("hasAnyRole('ROLE_USER')")
			
			//page must login by admin role
			.antMatchers("/admin/**")
			.access("hasRole('ROLE_ADMIN')")
			
			//page must login by teach role
			.antMatchers("/teacher/**")
			.access("hasRole('ROLE_TEACHER')")
			
			//page must login by manager categories role
			.antMatchers("/manager_categories/**")
			.access("hasRole('ROLE_MANAGER_CATEGORIES')")
			
			//page must login by manager course role
			.antMatchers("/manager_course/**")
			.access("hasRole('ROLE_MANAGER_COURSE')")
			
			.anyRequest().authenticated()
			.and()
//			.formLogin().permitAll()
//				.loginPage("/login")
//				.usernameParameter("email")
//				.passwordParameter("pass")
//				.defaultSuccessUrl("/list")
//			.and()
			.oauth2Login()
				.loginPage("/login")
				
				//.loginProcessingUrl("/processlogin")
				.userInfoEndpoint()
					.userService(oauthUserService)
				.and()
				.successHandler(new AuthenticationSuccessHandler() {
					
					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						System.out.println("AuthenticationSuccessHandler invoked");
						System.out.println("Authentication name: " + authentication);
						DefaultOAuth2User oauthUser = (DefaultOAuth2User) authentication.getPrincipal();
						User newUser = new User();
						newUser.setEmail(oauthUser.getAttribute("email"));
						newUser.setFullName(oauthUser.getAttribute("name"));
					    newUser.setRole(5);
					    
					    if (oauthUser.getAttribute("sub")!=null) {
					    	    newUser.setProvider(Provider.GOOGLE);
							    newUser.setAvatar(oauthUser.getAttribute("picture"));
							    newUser.setEnable(oauthUser.getAttribute("email_verified"));
						} else {
							 newUser.setProvider(Provider.FACEBOOK);
							 newUser.setEnable(true);
						}
						//save user into database
						userService.processOAuthPostLogin(newUser);							
						
						//add new user into session
						//request.getSession(true).setAttribute("MY_USER", newUser);
						
						//set my user cookie
						userService.setMyUserCookie(newUser,response); 
						
						response.sendRedirect("/");
					}
				})
				//.defaultSuccessUrl("/list")
			.and()
			.logout()		
			.logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
			.deleteCookies("MY_USER")
			.invalidateHttpSession(true)
			.logoutSuccessUrl("/").permitAll()
			.and()
			.exceptionHandling().accessDeniedPage("/403")
			;
	}
	
	
	@Override
	public void configure(WebSecurity web) throws Exception {
//		User user =userService.getMyUserCookie("MY_USER");
//		if (user!=null) {
//			web.ignoring().antMatchers("/a");
//			System.out.println(user);
//		}
		
		web.ignoring().antMatchers("/resources");
	}


	@Autowired
	private CustomOAuth2UserService oauthUserService;
	
	
	@Autowired
	private UserService userService;

	
	
	
	
	
	
}