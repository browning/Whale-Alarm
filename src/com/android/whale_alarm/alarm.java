package com.android.whale_alarm;

import java.util.Calendar;
import java.util.GregorianCalendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.AlertDialog;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.*;
import android.view.*;
import android.graphics.*;
import android.graphics.drawable.Drawable;

public class alarm extends Activity {
	
	
	private alarm this_view = null;
	
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             
        setContentView(R.layout.main);
        TimePicker tp = (TimePicker)findViewById(R.id.timewidget);
        tp.setIs24HourView(true);
        SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
		int h = sp.getInt("hour", -1);
		int m = sp.getInt("min", -1);
		if( h != -1)
			tp.setCurrentHour(h);
		if( m != -1)
			tp.setCurrentMinute(m);
		
        this_view = this;
       
        Button set = (Button)findViewById(R.id.setbutton);
        set.setOnClickListener(new View.OnClickListener() {
   
			@Override
			public void onClick(View v) {
				TimePicker tp = (TimePicker)findViewById(R.id.timewidget);
				int hour = tp.getCurrentHour();
				int min = tp.getCurrentMinute();
				
				SharedPreferences sp = getPreferences(Context.MODE_PRIVATE);
				SharedPreferences.Editor ed = sp.edit();
				ed.putInt("hour", hour);
				ed.putInt("min", min);
				ed.commit();
				
				Calendar c = Calendar.getInstance();
				c.set(Calendar.HOUR_OF_DAY, hour);
				c.set(Calendar.MINUTE, min);
				
				Calendar now = Calendar.getInstance();
				if( c.getTimeInMillis() < now.getTimeInMillis())
					c.add(Calendar.DATE, 1);
				
				Intent intent = new Intent(alarm.this, alarm_wakeup.class);
				PendingIntent alarmIntent = PendingIntent.getBroadcast(alarm.this, 0, intent, 0);
				
				AlarmManager am = (AlarmManager)this_view.getSystemService(ALARM_SERVICE);
				am.set(AlarmManager.RTC_WAKEUP,
					    c.getTimeInMillis()  /* now.getTimeInMillis() + 5000*/,
						alarmIntent);
			}
		});
    }
}