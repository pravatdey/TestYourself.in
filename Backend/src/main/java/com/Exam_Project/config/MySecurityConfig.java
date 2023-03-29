package com.Exam_Project.config;

import javax.activation.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.util.AntPathMatcher;

import com.Exam_Project.service.impl.UserDetailServiceimpl;
 

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class MySecurityConfig   {
	
	@Autowired
	private   JwtAuthenticationEntryPoint unauthorizedHandler;
	@Autowired
	private JwtAuthenticationFilter jwtauthenticationFilter;
	@Autowired
  private UserDetailServiceimpl userDetailServiceimpli;
	@Bean
	public AuthenticationManager authenticationManagerBean(AuthenticationConfiguration configuration)throws Exception {
		return configuration.getAuthenticationManager();
		
	}
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return  new BCryptPasswordEncoder() ;
	}
//	@Bean
//	public void configure(AuthenticationManagerBuilder auth)throws Exception{
//		auth.userDetailsService(this.userDetailServiceimpli).passwordEncoder(passwordEncoder());
//	}
//	
//	@Bean
//	public UserDetailsService userDetailsService(DataSource datasouce) throws Exception{
//		return (UserDetailsService) ((AuthenticationManagerBuilder) datasouce).userDetailsService(this.userDetailServiceimpli).passwordEncoder(passwordEncoder()).and().build();
////		return userDetailServiceimpli;
//		
//		
//	}
	@Bean
	public DaoAuthenticationProvider daoAuthenticationProvider() {
		DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
		provider.setUserDetailsService(this.userDetailServiceimpli);
		provider.setPasswordEncoder(passwordEncoder());
		return provider;
	}
    @Bean
    public SecurityFilterChain  filterChain(HttpSecurity http) throws Exception {
    	 
    	http   
    	       .csrf()
    	       .disable()
    	       .cors()
    	       .disable()
    	       .authorizeHttpRequests() 
    	       .requestMatchers("/generate-token","/user/").permitAll()
    	       .requestMatchers(HttpMethod.OPTIONS).permitAll()
    	       .anyRequest().authenticated()
    	       .and()
    	       .exceptionHandling().authenticationEntryPoint(this.unauthorizedHandler)
    	       .and()
    	       .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    	   http.addFilterBefore(this.jwtauthenticationFilter, UsernamePasswordAuthenticationFilter.class);
    	   http.authenticationProvider(daoAuthenticationProvider());
		return http.build();
    	 
    }

	 
	
}
