package com.techblogon.loginexample;

import android.app.Activity;
import android.content.Intent;
//import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class HomePage extends Activity implements OnClickListener {
	private Button findtrain;
	private Button generatedticket;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.homepage);
		findtrain = (Button)findViewById(R.id.findtrain);
		generatedticket = (Button)findViewById(R.id.viewgeneratedticket);
		findtrain.setOnClickListener(this);
		generatedticket.setOnClickListener(this);
	}
	@Override
	public void onClick(View v) {
		int id = v.getId();
		
		switch(id)
		{
		case R.id.findtrain:
			//Toast.makeText(HomePage.this,"Finding Train", Toast.LENGTH_SHORT).show();
			startActivity(new Intent(HomePage.this,FindTrain.class));
			//Toast.makeText(MainActivity.this,"Finding Train",Toast.LENGTH_SHORT).show();
			break;
			
		case R.id.viewgeneratedticket:
			Toast.makeText(HomePage.this,"Showing Generated Tickets", Toast.LENGTH_SHORT).show();
			break;
			
		default:
				break;
		}
	
		
	}
}
