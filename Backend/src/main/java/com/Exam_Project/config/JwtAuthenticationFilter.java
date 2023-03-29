package com.Exam_Project.config;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import com.Exam_Project.service.impl.UserDetailServiceimpl;

import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter{
	@Autowired
	private UserDetailServiceimpl userDetailService;
	@Autowired
	private JwtUtil jwtUtil;
	@Override
	protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
			throws ServletException, IOException {
		final String requestTokenHeader=request.getHeader("Authorization");
		System.out.println(requestTokenHeader);
		String username=null;
		String jwtToken=null;
		
		if(requestTokenHeader!=null && requestTokenHeader.startsWith("Bearer")) {
			 jwtToken=requestTokenHeader.substring(7);
			 try {
			 username=this.jwtUtil.extractUsername(jwtToken);
			 }catch (ExpiredJwtException e) {
				 e.printStackTrace();
				System.out.println("jwt Token has expired");
			}catch(Exception e) {
				e.printStackTrace();
				System.out.println("error");
			}
		}else {
			System.out.println("Invalid token not start with bearer String ");
		}
		if(username!=null && SecurityContextHolder.getContext().getAuthentication()==null) {
		final	UserDetails userDetails=this.userDetailService.loadUserByUsername(username);
		if(this.jwtUtil.validateToken(jwtToken, userDetails)){
			UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken=new UsernamePasswordAuthenticationToken(userDetails,null,userDetails.getAuthorities());
			usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
			SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
		}
		}else {
			System.out.println("Token is not valid");
		
		}
		filterChain.doFilter(request, response);
	}
}
