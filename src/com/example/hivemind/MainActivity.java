package com.example.hivemind;

import android.os.Bundle;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Handler;
import android.os.Message;
import android.text.method.ScrollingMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.view.Menu;

/*
 * Main Activity for HiveMind.
 * HiveMind only has one activity and makes calls to CouchDB with a RestService.
 */
public class MainActivity extends Activity {
	TextView text_query = null;
	RestService restServiceGet;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main); // Set the activity layout
		
		// Create new rest service for get (RestService.GET is global)
		restServiceGet = new RestService(mHandlerGet, this, "http://192.168.0.102:5984/", RestService.GET);
		restServiceGet.addHeader("Content-Type","application/json"); // Search for JSON objects
		restServiceGet.addParam("couchdb", "Welcome"); // Must contain this key-value pair
		
		// Text Query
        text_query = (TextView) findViewById(R.id.text_query); // Setup textbox (by ID) for response
        text_query.setMovementMethod(new ScrollingMovementMethod()); // Set style for update
        
        // Create Button for Querying CouchDB
        Button button_query = (Button) findViewById(R.id.button_query); // Setup button (by ID) for input
    	button_query.setOnClickListener(new View.OnClickListener() {
    		// When clicked...
    		public void onClick(View view) {
    			try {
					restServiceGet.execute(); // ...executes the RestService for HTTP GET
				} catch (Exception e) {
					e.printStackTrace();
				}
    		}
    	});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	// Overridden handler to process incoming response. Response string is attached as msg.obj.
    @SuppressLint("HandlerLeak")
	private final Handler mHandlerGet = new Handler() {
    	@Override
    	public void handleMessage(Message msg) {
    		text_query.setText((String) msg.obj);
    	}		
    };

}
