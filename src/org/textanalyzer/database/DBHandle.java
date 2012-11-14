package org.textanalyzer.database;

import com.orientechnologies.orient.core.exception.OStorageException;
import com.orientechnologies.orient.object.db.OObjectDatabasePool;
import com.orientechnologies.orient.object.db.OObjectDatabaseTx;

/**
 * Database Handle
 * 
 * @author Michael Schilonka
 * @author Maximilian Quellmalz
 * @version 12.11.2012
 */

public final class DBHandle extends OObjectDatabaseTx {

	private DBHandle(String iURL) {
		super(iURL);
	}

	/**
	 * @param connector
	 *            object of type DBHandle
	 */
	private static DBHandle connector;

	/**
	 * Validation of database. First it is tried to open an already existing
	 * database. If the database already exists, the classes ProfileInformation
	 * and ResultSet are registered. A new Database is created and opened
	 * afterwards. Finally the classes ProfileInformation and ResultSet are
	 * registered. In the end the database is returned.
	 * 
	 * @return database
	 */
	public static DBHandle createDB() {
		if (connector != null) {
			return connector;
		}

		try {
			connector = new DBHandle("local:db/").open("admin", "admin");
			System.out.println("Could open the db.");
			connector.getEntityManager().registerEntityClass(
					ProfileInformation.class);
			connector.getEntityManager().registerEntityClass(ResultSet.class);
			connector.getEntityManager().registerEntityClass(DBWord.class);
			connector.getEntityManager().registerEntityClass(Document.class);
		}

		catch (OStorageException ex) {
			System.out.println("Could not open the db.");
			connector = new DBHandle("local:db/").create();
			System.out.println("Created the db.");
			connector = new DBHandle("local:db/").open("admin", "admin");
			System.out.println("Could open the db.");
		}

		finally {
			connector.getEntityManager().registerEntityClass(
					ProfileInformation.class);
			connector.getEntityManager().registerEntityClass(ResultSet.class);
			connector.getEntityManager().registerEntityClass(DBWord.class);
			connector.getEntityManager().registerEntityClass(Document.class);
			System.out.println("Registered classes.");
		}
		return connector;

	}
	
	public static OObjectDatabaseTx getDB(){
		return OObjectDatabasePool.global().acquire("local:db/","admin","admin");
	}

}
