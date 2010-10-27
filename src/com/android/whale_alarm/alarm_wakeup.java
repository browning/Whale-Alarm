package com.android.whale_alarm;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.*;
import android.widget.Toast;

public class alarm_wakeup extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Log.e("testing", "hit it!!!!!!");
		Intent alarmView = new Intent(context, alarm_on.class);
		intent.putExtra("go_off", true);
		
		alarmView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alarmView);
	}
}