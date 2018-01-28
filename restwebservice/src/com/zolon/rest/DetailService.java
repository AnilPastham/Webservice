package com.zolon.rest;

import java.io.IOException;
import java.util.List;

import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MultivaluedMap;

import com.zolon.dao.ImageDAO;
import com.zolon.form.Detail;
import com.zolon.form.User;
import com.zolon.util.DetailUtil;


@Path("/detail")  
public class DetailService { 
    
    @POST  
    @Path("/getDetails")  
    public String outputImages() throws IOException {  
		ImageDAO imageDao = ImageDAO.getInstance();
						
		return DetailUtil.serializeListOfDetails(imageDao.getDetails());
    }
    
    @POST
    @Path("/insertDetails")  
    public String insertDetails(MultivaluedMap<String, String> detail) throws IOException, ClassNotFoundException {  
		
    	String stringDetail = "";
		stringDetail = detail.getFirst("detail");

    	Detail detailObj = DetailUtil.deserializeDetail(stringDetail);

		ImageDAO imageDao = ImageDAO.getInstance();
				
		imageDao.insertDetails(detailObj);
				
		imageDao.getDetails();
		
		return detailObj.getId().toString();
   
    }
    
    @POST  
    @Path("/insertImage")  
    public String insertImage(MultivaluedMap<String, String> image) { 
    	
    	String imageString = null;
		imageString = image.getFirst("image"); 
    	    	    			
    	ImageDAO imageDao = ImageDAO.getInstance();
    	Object id = imageDao.insertImage(imageString.getBytes());
		imageDao.getDetails();
		
		return id.toString();   
    } 
    
    @POST  
    @Path("/authenticateAdmin")  
    public String authenticateAdmin(MultivaluedMap<String, String> image) throws ClassNotFoundException, IOException { 
    	
    	String userAuthenticated = "true";
    	
    	String userString = null;
		userString = image.getFirst("user"); 
		
    	User userObj = DetailUtil.deserializeUser(userString);
    	
    	System.out.println("USER: " + userObj.getUser() + " PASSWORD: " + userObj.getPassword());
		
		return userAuthenticated;   
    } 
}
