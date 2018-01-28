package com.zolon.dao;

import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.mongodb.BasicDBObject;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;
import com.mongodb.gridfs.GridFS;
import com.mongodb.gridfs.GridFSDBFile;
import com.mongodb.gridfs.GridFSInputFile;
import com.zolon.form.Detail;
import com.zolon.util.AppServletContextListener;
import com.zolon.util.DetailUtil;

public class ImageDAO {
	
    private static ImageDAO instance = null;
 
    private MongoClient connection;
    
    private static final String databaseName = "GridFSTestJava";
 
    public static ImageDAO getInstance()
    {
        if (instance == null)
        	instance = new ImageDAO();
 
        return instance;
    }
	
	private ImageDAO(){
		//System.out.println("DB SERVER: " + AppServletContextListener.getProperties().getProperty("database"));
		try {
			connection = new MongoClient(new MongoClientURI("mongodb://54.147.139.225:27017"));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void insertDetails(Detail detail) {
		DBObject details = new BasicDBObject("_id", detail.getId())
				                    .append("email", detail.getEmail())
		                            .append("name", detail.getName())
		                            .append("address", new BasicDBObject("street", detail.getAddress().getStreet())
		                                                         .append("city", detail.getAddress().getCity())
		                                                         .append("state", detail.getAddress().getState())
		                                                         .append("zip", detail.getAddress().getZip()));
		DB database = connection.getDB(databaseName);
		DBCollection collection = database.getCollection("detail");
		    
		collection.insert(details);
	}
	
	public ArrayList<Detail> getDetails() {
		ArrayList<Detail> listOfDetails = new ArrayList<Detail>();
		Detail detail = null;
		String dbName = databaseName;
		DB db = connection.getDB( dbName );
		GridFS fs = new GridFS( db );
		String imageString = null;
		Map <String, String> imageMap = new HashMap<String, String>();
		List<DBObject> dbObjects = db.getCollection("fs.files").find().toArray(); 
		for(DBObject dbObject: dbObjects) {
			Set<String> fields = dbObject.keySet();
			System.out.println("");
			for(String field: fields) {
				System.out.print("Column: " + field + " Value: " + dbObject.get(field));
				if(field.equals("_id")) {
					GridFSDBFile out = fs.findOne( new BasicDBObject( "_id" , dbObject.get(field) ) );
					imageString = DetailUtil.serializeImage(out);
					//System.out.println("WEBSERVICE IMAGE: " + imageString);
					imageMap.put(dbObject.get(field).toString(), imageString);				
				}
			}
		}
		
		dbObjects = db.getCollection("detail").find().toArray(); 
		for(DBObject dbObject: dbObjects) {
			Set<String> fields = dbObject.keySet();
			System.out.println("");
			detail = new Detail();
			for(String field: fields) {
				System.out.print("Column: " + field + " Value: " + dbObject.get(field));
				DetailUtil.populateDetails(detail,field,dbObject.get(field).toString());
				if(field.equals("address")) {
					DBObject address = (DBObject)dbObject.get(field);
					Set<String> addressFields =  address.keySet();
					for(String addressItem: addressFields) {
						DetailUtil.populateDetails(detail,addressItem,address.get(addressItem).toString());
					}					
				}
				if(field.equals("_id")) {
					detail.setImage(imageMap.get(dbObject.get(field).toString()));
				}
			}
			listOfDetails.add(detail);
		}		
		return listOfDetails;
	}
	
	
    
    public Object insertImage(byte [] image) {
        
    	try {
            			
			//Connect to database
			String dbName = databaseName;
			DB db = connection.getDB( dbName );
			
			//Create GridFS object
			GridFS fs = new GridFS( db );
			
			//Save image into database
			GridFSInputFile in = fs.createFile( image );
			in.setContentType("image/jpg");
			in.save();
						
			//Find saved image
			//GridFSDBFile out = fs.findOne( new BasicDBObject( "_id" , in.getId() ) );

			//Save loaded image from database into new image file
/*			FileOutputStream outputImage = new FileOutputStream("C:/Temp/snapshotCopy.png");
			out.writeTo( outputImage );
			outputImage.close();*/
			
			return in.getId();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
    }
}
