package com.Exam_Project.Modals;

public class jwtResponse {
String token;

public jwtResponse(String token) {
	super();
	this.token = token;
}

public jwtResponse() {
	super();
	// TODO Auto-generated constructor stub
}

public String getToken() {
	return token;
}

public void setToken(String token) {
	this.token = token;
}

}
