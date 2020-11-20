package com.fasalmbt.covid19;

import androidx.appcompat.app.AppCompatActivity; 

import android.os.Bundle; 
import android.widget.TextView; 
import android.widget.Toast; 

import com.android.volley.Request; 
import com.android.volley.RequestQueue; 
import com.android.volley.Response; 
import com.android.volley.VolleyError; 
import com.android.volley.toolbox.StringRequest; 
import com.android.volley.toolbox.Volley; 

import org.json.JSONException; 
import org.json.JSONObject; 

public class MainActivity 
	extends AppCompatActivity {
	TextView total_cases,today_cases,recovered,today_recovered,deaths,today_deaths,active,critical,countries;

	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{ 
		super.onCreate(savedInstanceState); 
		setContentView(R.layout.activity_main); 

		total_cases = findViewById(R.id.total_cases);
		today_cases = findViewById(R.id.today_cases);
		recovered = findViewById(R.id.recovered);
		today_recovered = findViewById(R.id.today_recovered);
		deaths = findViewById(R.id.deaths);
		today_deaths = findViewById(R.id.today_deaths);
		active = findViewById(R.id.active);
		critical = findViewById(R.id.critical);
		countries = findViewById(R.id.countries);

		fetch_api_data();
	} 

	private void fetch_api_data()
	{
		String url = "https://disease.sh/v3/covid-19/all";

		StringRequest request = new StringRequest(Request.Method.GET, url, new Response.Listener<String>() {
					@Override
					public void onResponse(String response) 
					{ 

						try { 

							JSONObject jsonObject = new JSONObject(response.toString());
							countries.setText(jsonObject.getString("affectedCountries"));
							total_cases.setText(jsonObject.getString("cases"));
							today_cases.setText(jsonObject.getString("todayCases"));
							recovered.setText(jsonObject.getString("recovered"));
							today_recovered.setText(jsonObject.getString("todayRecovered"));
							deaths.setText(jsonObject.getString("deaths"));
							today_deaths.setText(jsonObject.getString("todayDeaths"));
							active.setText(jsonObject.getString("active"));
							critical.setText(jsonObject.getString("critical"));

						} 
						catch (JSONException e) { 
							e.printStackTrace(); 
						} 
					} 
				}, 
				new Response.ErrorListener() { 
					@Override
					public void onErrorResponse(VolleyError error)
					{
						Toast.makeText(
								MainActivity.this,
								"പോയി നെറ്റ് കണക്റ്റ് ചെയ്യണം മിസ്റ്റർ",
								Toast.LENGTH_SHORT)
							.show();
					} 
				});
		RequestQueue requestQueue = Volley.newRequestQueue(this);
		requestQueue.add(request); 
	} 
} 
