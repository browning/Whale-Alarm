package com.android.whale_alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TimePicker;
import com.android.whale_alarm.alarm;;

public class alarm_on extends Activity {

	private alarm_on this_view = null;
	private MediaPlayer mp;
	@Override
	public void onDestroy()
	{
		if(mp.isPlaying())
			mp.stop();
		mp.release();
		super.onDestroy();
	}
	
	@Override
	public void onStop()
	{
		if(!isFinishing())
		{
			if(mp.isPlaying())
				mp.stop();
			mp.release();
		}
		super.onStop();
		
	}
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
             
        setContentView(R.layout.main_on);
        TimePicker tp = (TimePicker)findViewById(R.id.timewidget);
        tp.setIs24HourView(true);
        this_view = this;
       
        Button set = (Button)findViewById(R.id.setbutton);
        
        mp = MediaPlayer.create(getApplicationContext(), R.raw.whale);
        mp.setLooping(true);
        mp.start();
        
        set.setOnClickListener(new View.OnClickListener() {
   
			@Override
			public void onClick(View v) {
				finish();	
			}
		});
    }
}
