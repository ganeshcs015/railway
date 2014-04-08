package com.techblogon.loginexample;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class LoginDataBaseAdapter 
{
		static final String DATABASE_NAME = "railway.db";
		static final int DATABASE_VERSION = 1;
		public static final int NAME_COLUMN = 1;
		// TODO: Create public field for each column in your table.
		// SQL Statement to create a new database.
		static final String DATABASE_CREATE = "create table "+"LOGIN"+
		                             "( " +"ID"+" integer primary key autoincrement,"+ 
		                             "USERNAME  text,PASSWORD text,FIRSTNAME text,LASTNAME text,"
		                             + "DOB varchar,EMAIL varchar,PHONE integer); ";
		
		/*static final String DATABASE_CREATE = "create table "+"LOGIN"+
                "( " +"ID"+" integer primary key autoincrement,"+ 
                "USERNAME  text,PASSWORD text,FIRSTNAME text,LASTNAME text,"
                + "DOB text,EMAIL text); ";*/
		//creating table DATABASE_TRAIN
		static final String DATABASE_TRAIN = "create table "+"TRAINS"+
				"(" +"TRAINNO"+ " text primary key,"+
				"TRAINNAME text);";
		
		//creating table DATABASE_TRAININF
		static final String DATABASE_TRAININF = "create table"+"TRAINDETAIL"+
				"(" + "TRAINNUM"+ "varchar foreign key,SOURCE text,DESTINATION text"+
				"SOURCETIME varchar,DESTINATIONTIME varchar,FARE integer);";
		
		
		
		// Variable to hold the database instance
		public  SQLiteDatabase db;
		// Context of the application using the database.
		private final Context context;
		// Database open/upgrade helper
		private DataBaseHelper dbHelper;
		public  LoginDataBaseAdapter(Context _context) 
		{
			context = _context;
			dbHelper = new DataBaseHelper(context, DATABASE_NAME, null, DATABASE_VERSION);
		}
		public  LoginDataBaseAdapter open() throws SQLException 
		{
			db = dbHelper.getWritableDatabase();
			return this;
		}
		public void close() 
		{
			db.close();
		}

		public  SQLiteDatabase getDatabaseInstance()
		{
			return db;
		}
		
		public void insertEntry(String userName,String password,String firstname,
				String lastname,String DOB,String email,String phone)
		//public void insertEntry(String userName,String password,String firstname,
				//String lastname,String DOB,String email)
		{
	       ContentValues newValues = new ContentValues();
			// Assign values for each row.
			newValues.put("USERNAME", userName);
			newValues.put("PASSWORD",password);
			newValues.put("FIRSTNAME", firstname);
			newValues.put("LASTNAME", lastname);
			newValues.put("DOB", DOB);
			newValues.put("EMAIL", email);
			newValues.put("PHONE", phone);
			
			
			// Insert the row into your table
			db.insert("LOGIN", null, newValues);
			///Toast.makeText(context, "Reminder Is Successfully Saved", Toast.LENGTH_LONG).show();
		}
		public int deleteEntry(String UserName)
		{
			//String id=String.valueOf(ID);
		    String where="USERNAME=?";
		    int numberOFEntriesDeleted= db.delete("LOGIN", where, new String[]{UserName}) ;
	       // Toast.makeText(context, "Number for Entry Deleted Successfully : "+numberOFEntriesDeleted, Toast.LENGTH_LONG).show();
	        return numberOFEntriesDeleted;
		}	
		public String getSinlgeEntry(String userName)
		{
			Cursor cursor=db.query("LOGIN", null, " USERNAME=?", new String[]{userName}, null, null, null);
	        if(cursor.getCount()<1) // UserName Not Exist
	        {
	        	cursor.close();
	        	return "NOT EXIST";
	        }
		    cursor.moveToFirst();
			String password= cursor.getString(cursor.getColumnIndex("PASSWORD"));
			cursor.close();
			return password;				
		}
		public void  updateEntry(String userName,String password,String firstname,
				String DOB,String lastname,String email,String phone)
		//public void  updateEntry(String userName,String password,String firstname,
				//String DOB,String lastname,String email)
		{
			// Define the updated row content.
			ContentValues updatedValues = new ContentValues();
			// Assign values for each row.
			updatedValues.put("USERNAME", userName);
			updatedValues.put("PASSWORD",password);
			updatedValues.put("FIRSTNAME", firstname);
			updatedValues.put("LASTNAME", lastname);
			updatedValues.put("DOB",DOB);
			updatedValues.put("EMAIL", email);
			updatedValues.put("PHONE",phone);
	        String where="USERNAME = ?";
		    db.update("LOGIN",updatedValues, where, new String[]{userName});			   
		}		
}

