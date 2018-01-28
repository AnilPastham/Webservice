package com.zolon.test;

import com.zolon.dao.ImageDAO;
import com.zolon.form.Address;
import com.zolon.form.Detail;

public class TestDAO {
	
	public static void main(String [] args) {
		ImageDAO imageDao = ImageDAO.getInstance();
		
		Detail detail = new Detail();
		Address address = new Address();
		
		address.setCity("herndon");
		address.setState("virginia");
		address.setStreet("a street");
		address.setZip("1234567");
		
		detail.setAddress(address);
		detail.setName("Ethan Fleming");
		detail.setEmail("ethan.fleming@ksapex.com");
		
		imageDao.insertDetails(detail);
				
		imageDao.getDetails();
	}

}
