package com.zolon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Properties;

import com.sun.jersey.core.util.Base64;
import com.zolon.form.Detail;
import com.zolon.form.User;

public class DetailDisplayAppUtil {
    @SuppressWarnings("unchecked")
	public static ArrayList<Detail> deserializeListOfDetails(String stringDetail) throws IOException, ClassNotFoundException {
    	ArrayList<Detail> detailObj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
		byte [] detailBytesDecoded = Base64.decode(stringDetail);

		try {
			bis = new ByteArrayInputStream(detailBytesDecoded);
			ois = new ObjectInputStream(bis);
			detailObj = (ArrayList<Detail>)ois.readObject();
		} finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
		return detailObj;
    }
    
	public static String searializeUser(User user) throws IOException {
    	String userString = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream out = null;
		try {
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(user);
			out.flush();
	    	userString = bos.toString();
		} finally {
            if (bos != null) {
                bos.close();
            }
            if (out != null) {
                out.close();
            }
        }
		return userString;
    }
	
	public static String getProperty(String key) {
		Properties prop = new Properties();
		InputStream input = null;
		
		String property = null;

		try {

			input = new FileInputStream("WebContent/WEB-INF/properties/config.properties");

			// load a properties file
			prop.load(input);

			// get the property value and print it out
			System.out.println("PROPERTY: " + prop.getProperty(key));
			property = prop.getProperty(key);


		} catch (IOException ex) {
			ex.printStackTrace();
		} finally {
			if (input != null) {
				try {
					input.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return property;
	}
}
