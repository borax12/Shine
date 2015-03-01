package borax12.com.shine.data;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

import junit.framework.Test;

import java.util.HashSet;

/**
 * Created by borax12 on 2/28/2015.
 */
public class TestDb extends AndroidTestCase {

    public static final String LOG_TAG = TestDb.class.getSimpleName();

    void deleteDatabase(){
        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
    }

    @Override
    protected void setUp(){
        deleteDatabase();
    }

    public void testCreateDb() throws Throwable{
        // build a HashSet of all of the table names we wish to look for
        // Note that there will be another table in the DB that stores the
        // Android metadata (db version information)
        final HashSet<String> tableNameHashSet = new HashSet<String>();
        tableNameHashSet.add(WeatherContract.LocationEntry.TABLE_NAME);
        tableNameHashSet.add(WeatherContract.WeatherEntry.TABLE_NAME);

        mContext.deleteDatabase(WeatherDbHelper.DATABASE_NAME);
        SQLiteDatabase db = new WeatherDbHelper(this.mContext).getWritableDatabase();
        assertEquals(true, db.isOpen());

        // have we created the tables we want?
        Cursor c = db.rawQuery("SELECT name FROM sqlite_master WHERE type='table'", null);

        assertTrue("Error:This means the database has not been created correctly",c.moveToFirst());

        //verify that the tables haven been created
        do {
            tableNameHashSet.remove(c.getString(0));
        }while (c.moveToNext());

        //now do our tables contain the correct columns
        c = db.rawQuery("PRAGMA table_info(" + WeatherContract.LocationEntry.TABLE_NAME + ")",null);

        assertTrue("Error: This means that we were unable to query the database for table information.",
                c.moveToFirst());

        // Build a HashSet of all of the column names we want to look for
        final HashSet<String> locationColumnHashSet = new HashSet<String>();
        locationColumnHashSet.add(WeatherContract.LocationEntry._ID);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_CITY_NAME);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LAT);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_COORD_LONG);
        locationColumnHashSet.add(WeatherContract.LocationEntry.COLUMN_LOCATION_SETTING);

        int columnNameIndex = c.getColumnIndex("name");
        do {
            String columnName = c.getString(columnNameIndex);
            locationColumnHashSet.remove(columnName);
        } while(c.moveToNext());

        // if this fails, it means that your database doesn't contain all of the required location
        // entry columns
        assertTrue("Error: The database doesn't contain all of the required location entry columns",locationColumnHashSet.isEmpty());
        db.close();
    }

    /*public void testLocationTable() {
        // First step: Get reference to writable database

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues of what you want to insert
        // (you can use the createNorthPoleLocationValues if you wish)
        ContentValues testValues= TestUtilities.createNorthPoleLocationValues();

        // Insert ContentValues into database and get a row ID back
        long locationRowId=db.insert(WeatherContract.LocationEntry.TABLE_NAME,null,testValues);


        assertTrue(locationRowId!=-1);
        // Query the database and receive a Cursor back

        Cursor cursor = db.query(WeatherContract.LocationEntry.TABLE_NAME,
                                null,//all coloums
                                null,//coloums for where clause
                                null,//values for where clause
                                null,//columns to group by
                                null,//columns to filter group by
                                null );//columns to sort by
        // Move the cursor to a valid database row
        assertTrue("Error:No records returned from location query",cursor.moveToFirst());
        // Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)
        TestUtilities.validateCurrentRecord("Error:Location query validation failed",cursor,testValues);
        // Finally, close the cursor and database

        assertFalse("Error : More than one record returned from location query ",cursor.moveToNext());

        cursor.close();
        db.close();

    }*/

    public void testWeatherTable(){

        // First insert the location, and then use the locationRowId to insert
        // the weather. Make sure to cover as many failure cases as you can.
        long locationRowId=insertLocation();

        assertFalse("Error: Location Not Inserted Correctly", locationRowId == -1L);

        // Instead of rewriting all of the code we've already written in testLocationTable
        // we can move this code to insertLocation and then call insertLocation from both
        // tests. Why move it? We need the code to return the ID of the inserted location
        // and our testLocationTable can only return void because it's a test.



        // First step: Get reference to writable database

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues of what you want to insert
        // (you can use the createWeatherValues TestUtilities function if you wish)
        ContentValues contentValues= TestUtilities.createWeatherValues(locationRowId);

        // Insert ContentValues into database and get a row ID back

        long weatherRowId= db.insert(WeatherContract.WeatherEntry.TABLE_NAME,null,contentValues);
        assertTrue(weatherRowId != -1);

        // Query the database and receive a Cursor back

        Cursor cursor = db.query(WeatherContract.WeatherEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null);
        // Move the cursor to a valid database row

        assertTrue("Error:No records returned from weather query",cursor.moveToFirst());

        // Validate data in resulting Cursor with the original ContentValues
        // (you can use the validateCurrentRecord function in TestUtilities to validate the
        // query if you like)
        TestUtilities.validateCurrentRecord("TestInsertRdb weatherEntry failed to validate",cursor,contentValues);


        assertFalse( "Error: More than one record returned from weather query",cursor.moveToNext());
        // Finally, close the cursor and database
        cursor.close();
        db.close();
    }

    public long insertLocation() {

        WeatherDbHelper dbHelper = new WeatherDbHelper(mContext);
        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create ContentValues of what you want to insert
        // (you can use the createNorthPoleLocationValues if you wish)
        ContentValues testValues= TestUtilities.createNorthPoleLocationValues();

        // Insert ContentValues into database and get a row ID back
        long locationRowId=db.insert(WeatherContract.LocationEntry.TABLE_NAME,null,testValues);
        return locationRowId;
    }


}
