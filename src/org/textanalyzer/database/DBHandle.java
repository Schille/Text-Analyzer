package org.textanalyzer.database;

import java.util.List;

import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

public final class DBHandle extends OObjectDatabaseTx{
	 
	 private DBHandle(String iURL) {
		super(iURL);
	}
	 	
	private static DBHandle connector;
	 
	 public static DBHandle createDB(){
		 if(connector != null){
			 return connector;
		 }

		/**
		  * Tries to open an already existing database.
		  * If the database already exists, the classes ProfileInformation and ResultSet are registered.
		  */
		 try{
			 connector = new DBHandle("local:db/").open("admin", "admin");
			 System.out.println("Could open the db.");
			 connector.getEntityManager().registerEntityClass(ProfileInformation.class);
			 connector.getEntityManager().registerEntityClass(ResultSet.class);
			 connector.getEntityManager().registerEntityClass(DBWord.class);
		 }
		 /**
		  * A new Database is created and opened afterwards.
		  */
		 catch(OStorageException ex){
			 System.out.println("Could not open the db.");
			 connector = new DBHandle("local:db/").create();
			 System.out.println("Created the db.");
			 connector = new DBHandle("local:db/").open("admin", "admin");
			 System.out.println("Could open the db.");
		 }
		 /**
		  * Finally the classes ProfileInformation and ResultSet are registered.
		  */
		 finally{
			 connector.getEntityManager().registerEntityClass(ProfileInformation.class);
			 connector.getEntityManager().registerEntityClass(ResultSet.class);
			 connector.getEntityManager().registerEntityClass(DBWord.class);
			 System.out.println("Registered classes.");
		 }
		 /**
		  * In the end the database is returned.
		  */
		 return connector;
		 
	 }

}
