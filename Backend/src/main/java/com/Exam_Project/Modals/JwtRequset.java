package com.Exam_Project.Modals;

public class JwtRequset {
String username;
String password;
public JwtRequset() {
	 
}
public JwtRequset(String username, String password) {
	super();
	this.username = username;
	this.password = password;
}
public String getUsername() {
	return username;
}
public void setUsername(String username) {
	this.username = username;
}
public String getPassword() {
	return password;
}
public void setPassword(String password) {
	this.password = password;
}

}
