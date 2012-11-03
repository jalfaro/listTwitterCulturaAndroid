package com.galileo.twittergalileo;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.support.v4.app.NavUtils;

public class MainActivity extends Activity {
	Button btnSearch;
	EditText txtBuscar;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnSearch = (Button)findViewById(R.id.btnSearch);
		txtBuscar = (EditText)findViewById(R.id.txtSearch);
		btnSearch.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getBaseContext(),DisplayTwittActivity.class);
				i.putExtra("query", txtBuscar.getText().toString());
				startActivity(i);
				/*
				HTTPUtility http= new HTTPUtility();
				Log.d("DEBUG",http.readTwitts(txtBuscar.getText().toString()).toString());
				Toast.makeText(getBaseContext(), http.readTwitts(txtBuscar.getText().toString()).toString(), Toast.LENGTH_LONG).show();
				*/
			}});
	}


    
}
