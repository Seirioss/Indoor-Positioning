package com.seirioss.map;

import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class SensorData implements SensorEventListener {

    private double walk;
    private static final double LengthPerPix = 3.621;

   
	@Override
	public void onSensorChanged(SensorEvent event) {
		// TODO Auto-generated method stub
		if (event.sensor.getType()==Sensor.TYPE_STEP_COUNTER) {
			walk = (event.values[0] * LengthPerPix);
		}
		Intent stepIntent = new Intent("");
	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
		
	}
	

}
