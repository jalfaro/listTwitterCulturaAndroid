package com.galileo.twittergalileo;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

public class DisplayTwittActivity extends Activity {
	HTTPUtility http;
	JSONObject json;
	ArrayList<Twitt> arregloTwitt;
	ListView lista;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.listatwitt_layout);
		String hilera = getIntent().getStringExtra("query");
		http= new HTTPUtility();
		arregloTwitt = new ArrayList<Twitt>();
		json = http.readTwitts(hilera);
		try {
			Twitt t;
			JSONArray arr = json.getJSONArray("results");
			for (int i=0; i< arr.length();i++) {
				t = new Twitt();
				t.setUser(arr.getJSONObject(i).getString("from_user"));
				t.setUrlImage(arr.getJSONObject(i).getString("profile_image_url"));
				t.setComment(arr.getJSONObject(i).getString("text"));
				arregloTwitt.add(t);
			}
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Log.d("DEBUG",arregloTwitt.size()+"");
		lista = (ListView) findViewById(R.id.listaTwitt);
		lista.setAdapter(new TwittAdapter(this,R.layout.row_layout,arregloTwitt));		
	}

}
