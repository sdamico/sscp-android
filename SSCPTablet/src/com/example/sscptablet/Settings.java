package com.example.sscptablet;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.support.v4.app.NavUtils;

public class Settings extends Activity {
	public final static String IP_ADDRESS = "com.example.sscptablet.MESSAGE";
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        getActionBar().setDisplayHomeAsUpEnabled(true);
        EditText editText = (EditText) findViewById(R.id.ip_address);
    	editText.setText("10.0.0.1");
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_settings, menu);
        return true;
    }

    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tcpConnect(View view) {
    	Intent intent = new Intent(this, Dashboard.class);
    	EditText editText = (EditText) findViewById(R.id.ip_address);
    	String ipAddress = editText.getText().toString();
    	intent.putExtra(IP_ADDRESS, ipAddress);
    	startActivity(intent);
    }
}
