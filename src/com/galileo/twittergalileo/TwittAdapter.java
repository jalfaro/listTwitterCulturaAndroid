package com.galileo.twittergalileo;

import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TwittAdapter extends ArrayAdapter<Twitt> {
	Context ctx;
	int layout;
	ArrayList<Twitt> arreglo;
	
	public TwittAdapter(Context context, int textViewResourceId,
			List<Twitt> objects) {
		super(context, textViewResourceId, objects);
		ctx = context;
		layout = textViewResourceId;
		arreglo = (ArrayList<Twitt>) objects;
		// TODO Auto-generated constructor stub
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;
		Twitt t = arreglo.get(position);
		if (v == null) {
			LayoutInflater vi = (LayoutInflater)ctx.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = vi.inflate(layout, null);
		}
		if (t!= null) {
			TextView usuario, texto;
			ImageView imagen;
			usuario = (TextView) v.findViewById(R.id.txtUserName);
			texto = (TextView) v.findViewById(R.id.txtComment);
			imagen = (ImageView) v.findViewById(R.id.imgTwitter);
			usuario.setText(t.getUser());
			texto.setText(t.getComment());
			InputStream is;
			try {
				is = fetch(t.getUrlImage());
				Drawable drawable = Drawable.createFromStream(is, "src");
				imagen.setImageDrawable(drawable);
			} catch (MalformedURLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		return v;
	}

	private InputStream fetch(String urlString) throws MalformedURLException, IOException {
	    DefaultHttpClient httpClient = new DefaultHttpClient();
	    HttpGet request = new HttpGet(urlString);
	    HttpResponse response = httpClient.execute(request);
	    return response.getEntity().getContent();
	}
}
