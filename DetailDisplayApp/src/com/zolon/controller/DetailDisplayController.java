package com.zolon.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.WebResource;
import com.zolon.form.Detail;
import com.zolon.util.DetailDisplayAppUtil;

@WebServlet("/display")
public class DetailDisplayController extends HttpServlet {
	
	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		System.out.println( "SERVERNAME: " + request.getServerName());
		System.out.println( "SERVERPORT: " + request.getServerPort());
		
		HttpSession session = request.getSession();
		
		boolean loggedIn = session.getAttribute("loggedin") == null ? false : (boolean) session.getAttribute("loggedin");
		
		System.out.println("LOGGEDIN: " + loggedIn);
				
		if(!loggedIn) {
	        request.getRequestDispatcher("/WEB-INF/views/adminlogin.jsp").forward(request, response);
		} else {
			Client client = Client.create();
			WebResource webResource = client.resource("http://" + request.getServerName() + ":" + request.getServerPort() + "/restwebservice/rest/detail/getDetails");
			String details = webResource.post(String.class);
			ArrayList<Detail> listOfDetails = null;
			try {
				listOfDetails = DetailDisplayAppUtil.deserializeListOfDetails(details);
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			request.setAttribute("details", listOfDetails);

	        request.getRequestDispatcher("/WEB-INF/views/displaydetail.jsp").forward(request, response);
		}
    }
}
