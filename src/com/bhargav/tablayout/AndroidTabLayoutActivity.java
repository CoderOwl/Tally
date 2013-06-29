package com.bhargav.tablayout;

import android.os.Bundle;
import android.app.TabActivity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TabHost;
import android.widget.TabHost.TabSpec;

@SuppressWarnings("deprecation")
public class AndroidTabLayoutActivity extends TabActivity {
	MenuItem DeleteAll;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_android_tab_layout);
				
		
		TabHost tabHost = getTabHost();
        
        // Tab for Photos
        final TabSpec transactionspec = tabHost.newTabSpec("Transaction");
        // setting Title and Icon for the Tab
        transactionspec.setIndicator("Transaction");
        Intent transactionIntent = new Intent(this, transaction.class);
        transactionspec.setContent(transactionIntent);
         
        // Tab for Songs
        final TabSpec Summaryspec = tabHost.newTabSpec("Summary");       
        Summaryspec.setIndicator("Summary");
        Intent SummaryIntent = new Intent(this, summary.class);
        Summaryspec.setContent(SummaryIntent);
         
        // Tab for Videos
        final TabSpec detailsspec = tabHost.newTabSpec("Details");
        detailsspec.setIndicator("Details");
        Intent detailsIntent = new Intent(this, details.class);
        detailsspec.setContent(detailsIntent);
         
        // Adding all TabSpec to TabHost
        tabHost.addTab(transactionspec); // Adding photos tab
        tabHost.addTab(Summaryspec); // Adding songs tab
        tabHost.addTab(detailsspec); // Adding videos tab
		
		
	}
	
	
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item)
	{
		switch(item.getItemId())
		{
		case R.id.Exit:
			finish();
			return true;
		default:
			return super.onOptionsItemSelected(item);
		}

	}

}
