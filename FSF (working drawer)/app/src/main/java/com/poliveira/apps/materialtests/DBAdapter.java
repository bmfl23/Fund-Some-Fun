// ------------------------------------ DBADapter.java ---------------------------------------------

// TODO: Change the package to match your project.
package com.poliveira.apps.materialtests;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;


// TO USE:
// Change the package (at top) to match your project.
// Search for "TODO", and make the appropriate changes.
public class DBAdapter {

	/////////////////////////////////////////////////////////////////////
	//	Constants & Data
	/////////////////////////////////////////////////////////////////////
	// For logging:
	private static final String TAG = "DBAdapter";
	
	// DB Fields
	public static final String KEY_ROWID = "_id";  //<< BRANDON TODO: might need to be changed to "ownerID"
	public static final int COL_ROWID = 0;

	/*
	 * CHANGE 1:
	 */
	// TODO: Setup your fields here:
	//TODO = Done
	public static final String KEY_dateCreated = "dateCreated";			//col 2  << Use to determine 6 most recent campaigns
	public static final String KEY_startDate = "startDate";				//col 3
	public static final String KEY_endDate = "enddate";					//col 4
	public static final String KEY_recipFName = "recipFName";			//col 6
	public static final String KEY_recipLName = "recipLName";			//col 6
	public static final String KEY_previewImage = "previewImage";		//col 11
	public static final String KEY_goalAmount = "goalAmount";			//col 13
	public static final String KEY_raised= "raised";					//col 14
	public static final String KEY_customURL = "customURL";				//col 17
	public static final String KEY_city = "city";					//col 18
	public static final String KEY_state = "state";					//col 19
	
	// TODO: Setup your field numbers here (0 = KEY_ROWID, 1=...)
	//TODO = Done
	public static final int COL_dateCreated = 2;
	public static final int COL_startDate = 3;
	public static final int COL_endDate = 4;
	public static final int COL_recipFName = 6;
	public static final int COL_recipLName = 7;
	public static final int COL_previewImage = 11;
	public static final int COL_goalAmount = 13;
	public static final int COL_raised= 14;
	public static final int COL_customURL = 17;
	public static final int COL_city = 18;
	public static final int COL_state = 19;

	
	public static final String[] ALL_KEYS = new String[] {KEY_ROWID, KEY_dateCreated, KEY_startDate, KEY_endDate, KEY_recipFName, KEY_recipLName, KEY_previewImage, KEY_goalAmount, KEY_raised, KEY_customURL, KEY_city, KEY_state};
	
	//Brandon TODO: change db name and table below
	// TODO = DONE
	// DB info: it's name, and the table we are using (just one).
	public static final String DATABASE_NAME = "fsf";
	public static final String DATABASE_TABLE = "campaigns_table";


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                        CREATE DB & TRACK Ver NOT NEEDED                                             //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Track DB version if a new version of your app changes the format.

	public static final int DATABASE_VERSION = 2;

	/*
	private static final String DATABASE_CREATE_SQL = 
			"create table " + DATABASE_TABLE 
			+ " (" + KEY_ROWID + " integer primary key autoincrement, "

			/*
			 * CHANGE 2:
			 */
	/*
			// TODO: Place your fields here!
			// + KEY_{...} + " {type} not null"
			//	- Key is the column name you created above.
			//	- {type} is one of: text, integer, real, blob
			//		(http://www.sqlite.org/datatype3.html)
			//  - "not null" means it is a required field (must be given a value).
			// NOTE: All must be comma separated (end of line!) Last one must have NO comma!!

			+ KEY_NAME + " text not null, "
			+ KEY_STUDENTNUM + " integer not null, "
			+ KEY_FAVCOLOUR + " string not null"
			
			// Rest  of creation:
			+ ");";
	*/
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                      ^^ABOVE NOT NEEDED^^                                           //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Context of application who uses us.
	private final Context context;
	
	private DatabaseHelper myDBHelper;
	private SQLiteDatabase db;

	/////////////////////////////////////////////////////////////////////
	//	Public methods:
	/////////////////////////////////////////////////////////////////////
	
	public DBAdapter(Context ctx) {
		this.context = ctx;
		myDBHelper = new DatabaseHelper(context);
	}
	
	// Open the database connection.
	public DBAdapter open() {
		db = myDBHelper.getWritableDatabase();
		return this;
	}
	
	// Close the database connection.
	public void close() {
		myDBHelper.close();
	}


	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                             ADD/DELETE to/from DB(currently) NOT NEEDED                             //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Add a new set of values to the database.
	//public long insertRow(String name, int studentNum, String favColour) {
		/*
		 * CHANGE 3:
		 */		
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		/*
		ContentValues initialValues = new ContentValues();
		initialValues.put(KEY_NAME, name);
		initialValues.put(KEY_STUDENTNUM, studentNum);
		initialValues.put(KEY_FAVCOLOUR, favColour);
		
		// Insert it into the database.
		return db.insert(DATABASE_TABLE, null, initialValues);
	}
	
	// Delete a row from the database, by rowId (primary key)
	public boolean deleteRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		return db.delete(DATABASE_TABLE, where, null) != 0;
	}
	
	public void deleteAll() {
		Cursor c = getAllRows();
		long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
		if (c.moveToFirst()) {
			do {
				deleteRow(c.getLong((int) rowId));				
			} while (c.moveToNext());
		}
		c.close();
	}
	*/
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                      ^^ABOVE NOT NEEDED^^                                           //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////

	// Things needed in blocked out section //
	Cursor c = getAllRows();
	long rowId = c.getColumnIndexOrThrow(KEY_ROWID);
	///////////////////////////////////////////////

	// Return all data in the database.
	public Cursor getAllRows() {
		String where = null;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
							where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	// Get a specific row (by rowId)
	public Cursor getRow(long rowId) {
		String where = KEY_ROWID + "=" + rowId;
		Cursor c = 	db.query(true, DATABASE_TABLE, ALL_KEYS, 
						where, null, null, null, null, null);
		if (c != null) {
			c.moveToFirst();
		}
		return c;
	}

	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                UPDATE EXISTING ROW/ LL ACCESS & CREATION DB(currently) NOT NEEDED                   //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	// Change an existing row to be equal to new data.
	//public boolean updateRow(long rowId, String name, int studentNum, String favColour) {
		//String where = KEY_ROWID + "=" + rowId;

		/*
		 * CHANGE 4:
		 */
		// TODO: Update data in the row with new fields.
		// TODO: Also change the function's arguments to be what you need!
		// Create row's data:
		/*
		ContentValues newValues = new ContentValues();
		newValues.put(KEY_NAME, name);
		newValues.put(KEY_STUDENTNUM, studentNum);
		newValues.put(KEY_FAVCOLOUR, favColour);
		
		// Insert it into the database.
		return db.update(DATABASE_TABLE, newValues, where, null) != 0;
	}
	*/
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	//                                      ^^ABOVE NOT NEEDED^^                                           //
	/////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	
	/////////////////////////////////////////////////////////////////////
	//	Private Helper Classes:
	/////////////////////////////////////////////////////////////////////
	
	/**
	 * Private class which handles database creation and upgrading.
	 * Used to handle low-level database access.
	 */

	// >>>>>>>>>>>>>>>change>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>

	private static class DatabaseHelper extends SQLiteOpenHelper
	{
		DatabaseHelper(Context context) {
			super(context, DATABASE_NAME, null, DATABASE_VERSION);
		}


		@Override
		public void onCreate(SQLiteDatabase _db) {
			//_db.execSQL(DATABASE_CREATE_SQL);
		}


		@Override
		public void onUpgrade(SQLiteDatabase _db, int oldVersion, int newVersion) {
			Log.w(TAG, "Upgrading application's database from version " + oldVersion
					+ " to " + newVersion + ", which will destroy all old data!");
			
			// Destroy old database:
			_db.execSQL("DROP TABLE IF EXISTS " + DATABASE_TABLE);
			
			// Recreate new database:
			onCreate(_db);
		}

	}

}
