package com.android.whale_alarm;

import android.app.AlertDialog;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.PowerManager;
import android.util.*;
import android.widget.Toast;

public class alarm_wakeup extends BroadcastReceiver
{
	@Override
	public void onReceive(Context context, Intent intent) 
	{
		Log.e("testing", "hit it!!!!!!");
		
		PowerManager.WakeLock sCpuWakeLock;
		
       
        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
		
		sCpuWakeLock = pm.newWakeLock(
	               PowerManager.PARTIAL_WAKE_LOCK |
		                 PowerManager.ACQUIRE_CAUSES_WAKEUP |
		               PowerManager.ON_AFTER_RELEASE, "blah");
        sCpuWakeLock.acquire();
		
		Intent alarmView = new Intent(context, alarm_on.class);
		intent.putExtra("go_off", true);
		
		alarmView.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		context.startActivity(alarmView);
	
		sCpuWakeLock.release();
		sCpuWakeLock = null;
	}
}