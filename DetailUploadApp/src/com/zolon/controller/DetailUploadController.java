package com.zolon.controller;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;

import org.apache.tomcat.util.http.fileupload.FileItem;
import org.apache.tomcat.util.http.fileupload.FileItemFactory;
import org.apache.tomcat.util.http.fileupload.FileUploadException;
import org.apache.tomcat.util.http.fileupload.disk.DiskFileItemFactory;
import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;
import org.apache.tomcat.util.http.fileupload.servlet.ServletRequestContext;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.core.util.Base64;
import com.sun.jersey.core.util.MultivaluedMapImpl;
import com.zolon.form.Detail;
import com.zolon.service.DetailUploadService;
import com.zolon.util.DetailUploadAppUtil;


@WebServlet("/upload")
@MultipartConfig
public class DetailUploadController extends HttpServlet{
    
	@Override
    protected void doGet(
            HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

		Detail detail = new Detail();
		request.setAttribute("uploaded", false);
		request.setAttribute("detail", detail);

        request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request, response);
    }
    
	@Override
    protected void doPost(
    		HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
		
		Detail detail = new Detail();

		DetailUploadService uploadService = new DetailUploadService(detail);
		
		FileItemFactory itemfactory = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(itemfactory);
		
		List<FileItem> items = null;
		byte [] serializeImage = null;
		try {
			items = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(new ServletRequestContext(request));
			for(FileItem item: items) {
				if(item.isFormField()){
					uploadService.populateDetails(item);
				} else {
					serializeImage = DetailUploadAppUtil.serializeImage(item);
				}
			}
		} catch (FileUploadException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	


		
		Client client = Client.create();
		WebResource webResource = client.resource("http://" + request.getServerName() + ":" + request.getServerPort() + "/restwebservice/rest/detail/insertImage");

		MultivaluedMap<String, String> formData = new MultivaluedMapImpl();
		formData.add("image", new String(serializeImage));
		String id = webResource.type("application/x-www-form-urlencoded").post(String.class, formData);
		uploadService.getDetail().setId(id);
		
		
		webResource = client.resource("http://" + request.getServerName() + ":" + request.getServerPort() + "/restwebservice/rest/detail/insertDetails");
		

		formData = new MultivaluedMapImpl();
		
		String detailString = DetailUploadAppUtil.searializeUser(uploadService.getDetail());
				
		formData.add("detail", detailString);
		
		String returnedDetails = webResource.type("application/x-www-form-urlencoded").post(String.class, formData);
		
		System.out.println(returnedDetails);
		
		request.setAttribute("uploaded", true);
		request.setAttribute("id", returnedDetails);

		request.getRequestDispatcher("/WEB-INF/views/upload.jsp").forward(request, response);
    }

}
