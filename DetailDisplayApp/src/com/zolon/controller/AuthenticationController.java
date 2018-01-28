package com.zolon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MultivaluedMap;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zolon.form.Detail;
import com.zolon.form.User;
import com.zolon.util.DetailDisplayAppUtil;

@WebServlet("/authenticate")
public class AuthenticationController extends HttpServlet{
	
	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		HttpSession session = request.getSession();
			
		session.setAttribute("loggedin", false);
	    request.getRequestDispatcher("/WEB-INF/views/adminlogin.jsp").forward(request, response);
	}
	
	@Override
    protected void doPost(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		System.out.println( "SERVERNAME: " + request.getServerName());
		System.out.println( "SERVERPORT: " + request.getServerPort());
		
		HttpSession session = request.getSession();
				
		Client client = Client.create();
		WebResource webResource = client.resource("http://" + request.getServerName() + ":" + request.getServerPort() + "/restwebservice/rest/detail/authenticateAdmin");
		
		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		User user = new User();
		user.setUser("");
		user.setPassword("");
		formData.add("user", DetailDisplayAppUtil.searializeUser(user));
		String userAuthenticated = webResource.type("application/x-www-form-urlencoded").post(String.class, formData);
		
		System.out.println("USER LOGGED IN: " + userAuthenticated);
		
		if(userAuthenticated.equals("true")) {
			session.setAttribute("loggedin", true);
			response.sendRedirect("http://" + request.getServerName() + ":" + request.getServerPort() + "/DetailDisplayApp/display");
		} else {
			session.setAttribute("loggedin", false);
	        request.getRequestDispatcher("/WEB-INF/views/adminlogin.jsp").forward(request, response);
		}
	}


}
