package com.seirioss.map;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.hardware.Sensor;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class MainActivity extends ActionBarActivity implements OnTouchListener {
	

	private Button button;
	private ImageView imageView;
	private ImageView icon;
	private TextView textView;
	
	private SensorManager sensorManager;
	private SensorData sensorData= new SensorData();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    
        button = (Button)findViewById(R.id.buttonId);
        imageView = (ImageView)findViewById(R.id.imageViewId);
        icon = (ImageView)findViewById(R.id.icon);
        textView = (TextView)findViewById(R.id.textViewId);
       
        sensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
			Resources resources = getResources();
			imageView.setImageDrawable(resources.getDrawable(R.drawable.map));
			icon.setImageDrawable(resources.getDrawable(R.drawable.ic_launcher));
			
			sensorManager.registerListener(sensorData, sensorManager.getDefaultSensor(Sensor.TYPE_STEP_COUNTER), SensorManager.SENSOR_DELAY_UI);
			imageView.setOnTouchListener(MainActivity.this);
			BitmapDrawable bitmapDrawable = (BitmapDrawable) imageView.getDrawable();
			textView.setText("Height: " + bitmapDrawable.getBitmap().getHeight() + "\n" + "Width: " + bitmapDrawable.getBitmap().getWidth());
			}
		});
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

@Override
	protected void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
		sensorManager.unregisterListener(sensorData);
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) {
		// TODO Auto-generated method stub
		int X = (int)event.getX();
		int Y = (int)event.getY();
		Resources resources = getResources();
		BitmapDrawable bitmapDrawable = (BitmapDrawable) resources.getDrawable(R.drawable.ic_launcher);
		icon.setImageDrawable(bitmapDrawable);
		setLayout(icon, X, Y);
		textView.setText("Touch Position is: " + "\n" + "X=" + X + "   Y=" + Y);
		
		return false;
	}
	
	public static void setLayout(View view, int x, int y){
		MarginLayoutParams margin = new MarginLayoutParams(view.getLayoutParams());
		margin.setMargins(787, 1088, 787 + margin.width, 1088 + margin.height);
		RelativeLayout.LayoutParams layoutParams = new RelativeLayout.LayoutParams(margin);
		view.setLayoutParams(layoutParams);
	}
	
	public class StepReceiver extends BroadcastReceiver{

		@Override
		public void onReceive(Context context, Intent intent) {
			// TODO Auto-generated method stub
			if ("mobile.android.MYBROADCAST".equals(intent.getAction())) {
				String distance = "Distance: " + intent.getDoubleExtra("steps", 0);
				textView.setText(distance);
			}
		}
		
	}
	
}
