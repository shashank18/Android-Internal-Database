package com.mydatabase;

/* Simple Code to understand the concept of Internal Database
 * Import the project and run for best results
 * Author: Shashank S Mirji
 * email: shashankmirji@gmail.com*/


//supporting imports
import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MydatabaseActivity extends Activity {
    /** Called when the activity is first created. */
	/* Simple Code to understand the concept of Database
	 * Import the project and run for best results
	 * Author: Shashank S Mirji
	 * email: shashankmirji@gmail.com*/
	//creating objects 
	
	SQLiteDatabase db;
	Button btnInsert;
	Button btndisp;
	
	
	//on create,it sets memo.xml as main page
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        btnInsert=(Button)findViewById(R.id.button1);
        btndisp=(Button)findViewById(R.id.button2);
        
        //creates the database 
        try{
        //create a database
        db=openOrCreateDatabase("StudentDB",MODE_PRIVATE,null);      
    
        //create a table
        db.execSQL("Create Table Temp(id integer,name text)");
        }
        //displays the exception
        catch(SQLException e)
        {
        	
        }
        
        //listener method for insert button
        btnInsert.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
		
				EditText eid=(EditText) findViewById(R.id.editText1);
				EditText ename=(EditText)findViewById(R.id.editText2);
				
				//fetches the value from text fields and puts the values in the database
				ContentValues values=new ContentValues();
				values.put("id", eid.getText().toString());
				values.put("name", ename.getText().toString());
				if((db.insert("temp", null, values))!=-1)
				{
					Toast.makeText(MydatabaseActivity.this, "Record Successfully Inserted", 2000).show();
				}
				else
				{
					Toast.makeText(MydatabaseActivity.this, "Insert Error", 2000).show();
				}
				eid.setText("");
				ename.setText("");
				
			}
		});
        
        
        
//listener for display button class        
btndisp.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				// TODO Auto-generated method stub
				
				
				//disp on textview
				
				TextView aa=(TextView)findViewById(R.id.textView3);
				
				//fetches the data from the database and displays the same on the screen
			
				Cursor c=db.rawQuery("SELECT * FROM temp",null);
				c.moveToFirst();
				while(!c.isAfterLast())
				{
					
					 
					aa.append(c.getString(0)+"-"+c.getString(1)+"\n");
					//Toast.makeText(memo.this,c.getString(0)+ " "+c.getString(1), 1000).show();
					c.moveToNext();
				}
				c.close();
							}
			
			
		});

    }
    
   }

