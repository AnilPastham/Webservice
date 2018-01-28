package com.zolon.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class AppServletContextListener implements ServletContextListener {
    private static Properties properties;
    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
    	    	
        String cfgfile = servletContextEvent.getServletContext().getInitParameter("config_file");
        try {
			properties.load(servletContextEvent.getServletContext().getResourceAsStream(cfgfile));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

        // store it in application scope as well
        servletContextEvent.getServletContext().setAttribute("prop",properties);
    }

    public static Properties getProperties(){
        return properties;
    }

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
