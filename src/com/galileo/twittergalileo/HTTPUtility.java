package com.galileo.twittergalileo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.HttpEntity;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicHttpResponse;
import org.apache.http.protocol.BasicHttpContext;
import org.apache.http.protocol.HttpContext;
import org.json.JSONException;
import org.json.JSONObject;

public class HTTPUtility {
	
	public JSONObject readTwitts(String query) {
		ArrayList<NameValuePair> arreglo = new ArrayList<NameValuePair>();
		JSONObject json = null;
		String respuesta = readURL("http://search.twitter.com/search.json?q="+query,arreglo);
		try {
			json = new JSONObject(respuesta);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return json;
	}

	private String readURL(String url, ArrayList<NameValuePair> parametros) {
		String hilera="";
		HttpContext hc = new BasicHttpContext();
		DefaultHttpClient dhc = new DefaultHttpClient();
		
		HttpPost post = new HttpPost(url);
		BasicHttpResponse response;
		
		try {
			if (parametros != null) {
				post.setEntity((HttpEntity) new UrlEncodedFormEntity(parametros));
			}
			response = (BasicHttpResponse) dhc.execute(post,hc);
			InputStream is;
			is = response.getEntity().getContent();
			BufferedReader br = new BufferedReader(new InputStreamReader(is));
			StringBuilder respuesta = new StringBuilder();
			String linea;
			while ((linea = br.readLine()) != null) {
				respuesta.append(linea);
			}
			br.close();
			is.close();
			hilera = respuesta.toString();
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return hilera;
	}

}
