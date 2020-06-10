/*package com.example.accelerometerfilter;

import java.util.ArrayList;

import org.achartengine.ChartFactory;
import org.achartengine.chart.PointStyle;
import org.achartengine.model.XYMultipleSeriesDataset;
import org.achartengine.model.XYSeries;
import org.achartengine.renderer.XYMultipleSeriesRenderer;
import org.achartengine.renderer.XYSeriesRenderer;

import com.example.accelerometerfilter.AccelData;
import com.example.accelerometerfilter.R;

import android.app.Activity;
import android.content.Context;
import android.graphics.Color;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
//import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.CheckBox;
//import android.widget.CompoundButton;
//import android.widget.CompoundButton.OnCheckedChangeListener;
//import android.widget.TextView;
import android.widget.LinearLayout;
import android.widget.Toast;


public class MainActivity extends Activity implements SensorEventListener,
OnClickListener {
	private SensorManager sensorManager;
	private RadioButton btnAccel, btnLinearAccel;
	private RadioGroup radioAccel;
	private Button btnStart, btnStop;
	private CheckBox checkLowPass, checkHighPass;
	private boolean started = false;
	private boolean bAccel = false;
	private boolean bLinAccel = false;
	private boolean LPFilter = false;
	private boolean HPFilter = false;
	private ArrayList<AccelData> sensorData;
	private LinearLayout layout;
	private View mChart;
	float a = 0.1f;
	float beta = 0.8f;

	@Override
	public void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	
		layout = (LinearLayout) findViewById(R.id.chart_container);
		sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
		sensorData = new ArrayList<AccelData>();
	
		btnStart = (Button) findViewById(R.id.btnStart);
		btnStop = (Button) findViewById(R.id.btnStop);	
	
		addListenerOnRadioButton();
		addListenerOnCheckBox();
	
		
		btnStart.setOnClickListener(this);
		btnStop.setOnClickListener(this);

		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
}

@Override
protected void onResume()
{
	super.onResume();
}

@Override
protected void onPause() 	
{
	super.onPause();
	if (started == true) 
	{
		sensorManager.unregisterListener(this);
	}
}

@Override
public void onAccuracyChanged(Sensor sensor, int accuracy) 
{

}

@Override
public void onSensorChanged(SensorEvent event) 
{
	if (started) 
	{
		double x = event.values[0];
		double y = event.values[1];
		double z = event.values[2];
		double mLowPassX = x;
		double mLowPassY = y;
		double mLowPassZ = z;
		double gravity[] ={0,0,0};
		double linear_acceleration[] = {0,0,0};
	
		if (LPFilter == true)
		{
			mLowPassX = lowPass(x, mLowPassX);
			mLowPassY = lowPass(y, mLowPassY);
			mLowPassZ = lowPass(z, mLowPassZ);
			
			x = mLowPassX;
			y = mLowPassY;
			z = mLowPassZ;
		}
		
		if (HPFilter == true)
		{
			gravity[0] = beta * gravity[0] + (1 - beta) * x;
			gravity[1] = beta * gravity[1] + (1 - beta) * y;
			gravity[2] = beta * gravity[2] + (1 - beta) * z;
			
			linear_acceleration[0] = x - gravity[0];
			linear_acceleration[1] = y - gravity[1];
			linear_acceleration[2] = z - gravity[2];
		}
	

		long timestamp = System.currentTimeMillis();
		AccelData data = new AccelData(timestamp, x, y, z);
		sensorData.add(data);
	}

}

private double lowPass(double current, double last)
{
	return last * (1.0f - a) + current * a;
}

private double highPass(double current, double last)
{
	return 0.0;
}

public void addListenerOnCheckBox()
{
	checkLowPass= (CheckBox)findViewById(R.id.LowPass);
	checkHighPass  =  (CheckBox)findViewById(R.id.HighPass);
	
	checkLowPass.setOnClickListener(new OnClickListener() 
	{
 		@Override
		public void onClick(View v) 
 		{
 			LPFilter = checkLowPass.isChecked();
 			Toast.makeText(MainActivity.this,
    				"low"+LPFilter, Toast.LENGTH_SHORT).show();
 
		}
 
	});
	
	checkHighPass.setOnClickListener(new OnClickListener()
	{
		public void onClick(View v) 
 		{
 			HPFilter = checkHighPass.isChecked();;
 			Toast.makeText(MainActivity.this,
    				"High"+HPFilter, Toast.LENGTH_SHORT).show();

		}
	});
}

public void addListenerOnRadioButton() 
{
	 
	radioAccel = (RadioGroup) findViewById(R.id.radioAccel);
	
	btnAccel = (RadioButton) findViewById(R.id.Accelerometer);
	btnLinearAccel = (RadioButton) findViewById(R.id.LinearAcc);
	
	
	btnAccel.setOnClickListener(new OnClickListener() 
	{
 		@Override
		public void onClick(View v) 
 		{
 			//mText.setText("accelerometer");
 			bAccel = btnAccel.isChecked();
 			
 			Toast.makeText(MainActivity.this,
    				"accel"+bAccel, Toast.LENGTH_SHORT).show();
 
		}
 
	});
	
	btnLinearAccel.setOnClickListener(new OnClickListener()
	{
		public void onClick(View v) 
 		{ 			
 			bLinAccel = btnLinearAccel.isChecked();
 			Toast.makeText(MainActivity.this,
    				"Linear"+bLinAccel, Toast.LENGTH_SHORT).show();

		}
	});
 
  }

@Override
public void onClick(View v) {
	switch (v.getId()) {
	case R.id.btnStart:
		btnStart.setEnabled(false);
		btnStop.setEnabled(true);

		sensorData = new ArrayList<AccelData>();
		// save prev data if available
		//check the status of the radiobuttons and checkboxes
		
		
		started = true;
		if (bAccel == true)
		{
			Sensor accel = sensorManager
				.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
			sensorManager.registerListener(this, accel,
					SensorManager.SENSOR_DELAY_FASTEST);
		}
		
		else
		{
			Sensor accel = sensorManager
					.getDefaultSensor(Sensor.TYPE_LINEAR_ACCELERATION);	
			
			sensorManager.registerListener(this, accel,
					SensorManager.SENSOR_DELAY_FASTEST);
		}
		
		break;
	case R.id.btnStop:
		btnStart.setEnabled(true);
		btnStop.setEnabled(false);
	
		started = false;
		sensorManager.unregisterListener(this);
		layout.removeAllViews();
		openChart();

		// show data in chart
		break;
	
	default:
		break;
	}

}

private void openChart() {
if (sensorData != null || sensorData.size() > 0) {
	long t = sensorData.get(0).getTimestamp();
	XYMultipleSeriesDataset dataset = new XYMultipleSeriesDataset();

	XYSeries xSeries = new XYSeries("X");
	XYSeries ySeries = new XYSeries("Y");
	XYSeries zSeries = new XYSeries("Z");

	for (AccelData data : sensorData) {
		xSeries.add(data.getTimestamp() - t, data.getX());
		ySeries.add(data.getTimestamp() - t, data.getY());
		zSeries.add(data.getTimestamp() - t, data.getZ());
	}

	dataset.addSeries(xSeries);
	dataset.addSeries(ySeries);
	dataset.addSeries(zSeries);

	XYSeriesRenderer xRenderer = new XYSeriesRenderer();
	xRenderer.setColor(Color.RED);
	xRenderer.setPointStyle(PointStyle.CIRCLE);
	xRenderer.setFillPoints(true);
	xRenderer.setLineWidth(1);
	xRenderer.setDisplayChartValues(false);

	XYSeriesRenderer yRenderer = new XYSeriesRenderer();
	yRenderer.setColor(Color.GREEN);
	yRenderer.setPointStyle(PointStyle.CIRCLE);
	yRenderer.setFillPoints(true);
	yRenderer.setLineWidth(1);
	yRenderer.setDisplayChartValues(false);

	XYSeriesRenderer zRenderer = new XYSeriesRenderer();
	zRenderer.setColor(Color.BLUE);
	zRenderer.setPointStyle(PointStyle.CIRCLE);
	zRenderer.setFillPoints(true);
	zRenderer.setLineWidth(1);
	zRenderer.setDisplayChartValues(false);

	XYMultipleSeriesRenderer multiRenderer = new XYMultipleSeriesRenderer();
	multiRenderer.setXLabels(0);
	multiRenderer.setLabelsColor(Color.RED);
	multiRenderer.setChartTitle("t vs (x,y,z)");
	multiRenderer.setXTitle("Sensor Data");
	multiRenderer.setYTitle("Values of Acceleration");
	multiRenderer.setZoomButtonsVisible(true);
	
	for (int i = 0; i < sensorData.size(); i++) 
	{
		
		multiRenderer.addXTextLabel(i + 1, ""
				+ (sensorData.get(i).getTimestamp() - t));
	}
	for (int i = 0; i < 12; i++) 
	{
		multiRenderer.addYTextLabel(i + 1, ""+i);
	}

	multiRenderer.addSeriesRenderer(xRenderer);
	multiRenderer.addSeriesRenderer(yRenderer);
	multiRenderer.addSeriesRenderer(zRenderer);

	// Getting a reference to LinearLayout of the MainActivity Layout
	

	// Creating a Line Chart
	mChart = ChartFactory.getLineChartView(getBaseContext(), dataset,
			multiRenderer);

	// Adding the Line Chart to the LinearLayout
	layout.addView(mChart);

}
}

}
*/