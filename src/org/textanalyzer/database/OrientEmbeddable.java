/**
 * 
 */
package org.textanalyzer.database;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

import com.orientechnologies.orient.core.command.OCommandRequest;
import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.core.query.OQuery;
import com.orientechnologies.orient.core.query.nativ.ONativeSynchQuery;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNative;
import com.orientechnologies.orient.core.query.nativ.OQueryContextNativeSchema;
import com.orientechnologies.orient.core.record.impl.ODocument;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;



/**
 * @author Michael Schilonka & Maximilian Quellmalz
 *
 */
public class OrientEmbeddable {
	
	public static OObjectDatabaseTx createDatabase(){
		/**
		  * Initialization of the database.
		  */
		OObjectDatabaseTx db = null;
		/**
		  * Tries to open an already existing database.
		  * If the database already exists, the classes ProfileInformation and ResultSet are registered.
		  */
		 try{
			 db = new OObjectDatabaseTx("local:db/").open("admin", "admin");
			 System.out.println("Could open the db.");
			 db.getEntityManager().registerEntityClass(ProfileInformation.class);
			 db.getEntityManager().registerEntityClass(ResultSet.class);
		 }
		 /**
		  * A new Database is created and opened afterwards.
		  */
		 catch(OStorageException ex){
			 System.out.println("Could not open the db.");
			 db = new OObjectDatabaseTx ("local:db/").create();
			 System.out.println("Created the db.");
			 db = new OObjectDatabaseTx("local:db/").open("admin", "admin");
			 System.out.println("Could open the db.");
		 }
		 /**
		  * Finally the classes ProfileInformation and ResultSet are registered.
		  */
		 finally{
			 db.getEntityManager().registerEntityClass(ProfileInformation.class);
			 db.getEntityManager().registerEntityClass(ResultSet.class);
			 System.out.println("Registered classes.");
		 }
		 /**
		  * In the end the database is returned.
		  */
		 return db;
	}
	
	
	/**
	 * Creates an object in the database.
	 */
	public static void createObject(Object myObject) {	
	createDatabase().save(myObject);	
	}
	

	/**
	 * Removes a given object from database.
	 */
	public static void removeObject(Object myObject) {
		createDatabase().delete(myObject);
		createDatabase().close();
	}
	

	/**
	 * Returns a List of all existing Profiles in the database.
	 */
	public static List<ProfileInformation> getAllProfiles() {
		 List<ProfileInformation> profileList = new LinkedList<ProfileInformation>();
		 // Array oder LinkedList
		 System.out.println("All Profiles");
		 for (ProfileInformation p : createDatabase().browseClass(ProfileInformation.class)){
		 profileList.add(p);
		 }
		 return profileList;
	}

	

	public static ProfileInformation getProfileInformation(final int myId) {
		
		System.err.println("hass");
		OObjectDatabaseTx db = createDatabase();
		ProfileInformation help = null;
		for (ProfileInformation p : db.browseClass(ProfileInformation.class)){
			if (p.getId()==myId) {
				help=p;
			}			
			
		}
		return help;

	}

	
	public void editProfile(final int myId, ProfileInformation myProfile) {
		OObjectDatabaseTx db = createDatabase();
		for (ProfileInformation p : db.browseClass(ProfileInformation.class)){
			if (p.getId()==myId) {
				p = myProfile;
				db.save(p);
			}			
		}
		
	}
	


	
	 public static void main(String[] args) throws Exception {

		 OObjectDatabaseTx db = createDatabase();
		 
		 
		 
		 ProfileInformation profile = new ProfileInformation();
		 profile.setId(100);
		 profile.setAge(99999);
		 profile.setFirstName("Peteasdasdr");
		 profile.setLastName("Klauasdasds");
		 profile.setProfession("Student");
		 HashMap<String,Integer> most = new HashMap<String, Integer>();
		 most.put("Haus", 20);
		 most.put("Garten", 15);
		 HashMap<String,Integer> custom = new HashMap<String, Integer>();
		 custom.put("Schlauberger", 5);
		 custom.put("Scheiße", 2);
		 ResultSet result = new ResultSet();
		 result.setId(1000);
		 result.setAvaragePhraseLength(12);
		 result.setPseudoIQ(40);
		 result.setWordCount(500);
		 result.setWrongWordCount(50);
		 result.setMostFrequentWord(most);
		 result.setCustomWordCount(custom);
		 ResultSet result1 = new ResultSet();
		 result1.setId(1000);
		 result1.setAvaragePhraseLength(42);
		 result1.setPseudoIQ(455);
		 result1.setWordCount(5500);
		 result1.setWrongWordCount(550);
		 result1.setMostFrequentWord(most);
		 result1.setCustomWordCount(custom);
		 
		 createObject(profile);
		 createObject(result);
		 createObject(result1);
		 
		 
		// db.save(profile);
		 //db.save(result);
		 //db.save(result1);
		 
		 
		 long c = db.countClass("ProfileInformation");
		 long d = db.countClass("ResultSet");
		 System.out.println("ProfileInformation instances: " + c);
		 System.out.println("ResultSet instances: " + d);
		 
		 long start = System.currentTimeMillis();
		 
		 
		 List<ProfileInformation> a = getAllProfiles();
		 for (int i=0; i<a.size();i++){
		 System.out.println(a.get(i).getAge());
			 
		 }
		 System.out.println("hasssssss");
		 ProfileInformation hans = (ProfileInformation) getProfileInformation(100);
		 	System.out.println(hans.getAge());
			System.out.println(hans.getFirstName());
			System.out.println(hans.getLastName());


	/**	 @SuppressWarnings({ "deprecation", "serial" })
		List<ODocument> resultp = db.getUnderlying().command(
				  new ONativeSynchQuery<OQueryContextNativeSchema<ODocument>>(null, "ProfileInformation",
				                                                new OQueryContextNativeSchema<ODocument>()) {
				    @Override
				    public boolean filter(OQueryContextNativeSchema<ODocument> iRecord) {
				      return iRecord.field("id").eq(1000L).go();
				    };
				  }).execute();	 
		 
		 @SuppressWarnings({ "deprecation", "serial" })
		List<ODocument> resulte = db.getUnderlying().command(
				  new ONativeSynchQuery<OQueryContextNativeSchema<ODocument>>(null, "ResultSet",
				                                                new OQueryContextNativeSchema<ODocument>()) {
				    @Override
				    public boolean filter(OQueryContextNativeSchema<ODocument> iRecord) {
				      return iRecord.field("id").eq(1000L).go();
				    };
				  }).execute();	 
		 
		 System.out.println("Time consumed: " +( System.currentTimeMillis() - start));
		 
		ProfileInformation peter =  (ProfileInformation) db.getUserObjectByRecord(resultp.get(0), "ProfileInformation");
		
		System.out.println(peter.getAge());
		System.out.println(peter.getFirstName());
		System.out.println(peter.getLastName()); */
		
		
		//ResultSet doc  = (ResultSet) db.getUserObjectByRecord(resulte.get(0), "ResultSet");

		//System.out.println(doc.getPseudoIQ());
		//System.out.println(doc.getWordCount());
		//System.out.println(doc.getWrongWordCount()); 
		
		 //removeObject(peter);		
		 //removeProfile(profile);
		//removeResult(result);
		//removeResult(result1);
		
		c = db.countClass("ProfileInformation");
		d = db.countClass("ResultSet");
		System.out.println("ProfileInformation instances: " + c);
		System.out.println("ResultSet instances: " + d);
		

	 }


}
