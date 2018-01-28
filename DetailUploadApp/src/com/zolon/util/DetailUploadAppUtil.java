package com.zolon.util;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectOutputStream;

import org.apache.tomcat.util.http.fileupload.FileItem;

import com.zolon.form.Detail;

import sun.misc.BASE64Encoder;
import sun.misc.IOUtils;


public class DetailUploadAppUtil {
	
	public static String searializeUser(Detail detail) throws IOException {
    	String detailString = null;
		ByteArrayOutputStream bos = null;
		ObjectOutputStream out = null;
		try {
			bos = new ByteArrayOutputStream();
			out = new ObjectOutputStream(bos);
			out.writeObject(detail);
			out.flush();
			detailString = bos.toString();
		} finally {
            if (bos != null) {
                bos.close();
            }
            if (out != null) {
                out.close();
            }
        }
		return detailString;
    }
	
    public static byte [] serializeImage(FileItem file) {
        
    	try {
    		file.getInputStream();
            byte[] imageBytes = IOUtils.readNBytes(file.getInputStream(), (int) file.getSize());

            BASE64Encoder base64Encoder = new BASE64Encoder();
            
			return base64Encoder.encode(imageBytes).getBytes();
			
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

}
