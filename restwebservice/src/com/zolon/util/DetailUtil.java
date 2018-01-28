package com.zolon.util;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.List;
import java.util.Properties;

import com.mongodb.gridfs.GridFSDBFile;
import com.sun.jersey.core.util.Base64;
import com.zolon.form.Detail;
import com.zolon.form.User;

import sun.misc.BASE64Encoder;


public class DetailUtil {
	
    public static void populateDetails(Detail detail, String key, String value) {
    	
   	 switch (key) {
        case "name":
            detail.setName(value);
            break;
        case "email":
            detail.setEmail(value);
            break;
        case "_id":
            detail.setId(value);
            break;
        case "state":
            detail.getAddress().setState(value);
            break;
        case "city":
            detail.getAddress().setCity(value);
            break;
        case "street":
            detail.getAddress().setStreet(value);
            break;
        case "zip":
            detail.getAddress().setZip(value);
            break;
        }    	
   }

    public static String serializeImage(GridFSDBFile file) {
        
    	try {
    		ByteArrayOutputStream baos = new ByteArrayOutputStream();
    		
    		file.writeTo(baos);

			return new String(baos.toByteArray());
			
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
    
    public static Detail deserializeDetail(String stringDetail) throws IOException, ClassNotFoundException {
        Detail detailObj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
		byte [] detailBytesDecoded = stringDetail.getBytes();

		try {
			bis = new ByteArrayInputStream(detailBytesDecoded);
			ois = new ObjectInputStream(bis);
			detailObj = (Detail)ois.readObject();
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
    
    public static User deserializeUser(String stringUser) throws IOException, ClassNotFoundException {
        User userObj = null;
        ByteArrayInputStream bis = null;
        ObjectInputStream ois = null;
		byte [] userBytesDecoded = stringUser.getBytes();

		try {
			bis = new ByteArrayInputStream(userBytesDecoded);
			ois = new ObjectInputStream(bis);
			userObj = (User)ois.readObject();
		} finally {
            if (bis != null) {
                bis.close();
            }
            if (ois != null) {
                ois.close();
            }
        }
		return userObj;
    }
    
    public static String serializeListOfDetails(List<Detail> details) throws IOException {
		ByteArrayOutputStream bos = null;
		ObjectOutputStream out = null;
		try {
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(details);
			out.flush();
			return new String(Base64.encode(bos.toByteArray()));
		} finally {
            if (out != null) {
            	out.close();
            }
            if (bos != null) {
            	bos.close();
            }
        }
    }
}
