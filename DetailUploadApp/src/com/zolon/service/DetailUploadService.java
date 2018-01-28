package com.zolon.service;

import java.io.FileNotFoundException;
import java.io.IOException;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.zolon.form.Detail;

import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;

public class DetailUploadService {
	
	private Detail detail;
	
    public DetailUploadService(Detail detail) {
		super();
		this.detail = detail;
	}
    
    public void populateDetails(FileItem item) {
    	
    	 switch (item.getFieldName()) {
         case "name":
             detail.setName(item.getString());
             break;
         case "email":
             detail.setEmail(item.getString());
             break;
         case "id":
             detail.setId(item.getString());
             break;
         case "state":
             detail.getAddress().setState(item.getString());
             break;
         case "city":
             detail.getAddress().setCity(item.getString());
             break;
         case "street":
             detail.getAddress().setStreet(item.getString());
             break;
         case "zip":
             detail.getAddress().setZip(item.getString());
             break;
         }    	
    }

	public Detail getDetail() {
		return detail;
	}

	public void setDetail(Detail detail) {
		this.detail = detail;
	}
}
