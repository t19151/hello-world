package com.bn.sample10_1;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class Sample10_1Activity extends Activity {
	
	//SensorManager對象引用
	SensorManager mySensorManager;	
	Sensor sensor;
	
	static int bx;
	static int by;
	
	MyGameView msv;
	
	private SensorEventListener mel=new SensorEventListener(){

		@Override
		public void onAccuracyChanged(Sensor sensor, int accuracy) {}

		@Override
		public void onSensorChanged(SensorEvent event) {
			float []values=event.values;
			int directionDotXY[]=RotateUtil.getDirectionDot
			(
					new double[]{values[0],values[1],values[2]}
		    );
			//標準化xy位移量
			double mLength=directionDotXY[0]*directionDotXY[0]+directionDotXY[1]*directionDotXY[1];
			mLength=Math.sqrt(mLength);
			msv.dx=(int)((directionDotXY[0]/mLength)*msv.dLength);
			msv.dy=(int)((directionDotXY[1]/mLength)*msv.dLength);
		}
		
	};
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        
        //全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);		
		
		//獲得SensorManager對象
        mySensorManager = (SensorManager)getSystemService(SENSOR_SERVICE);	
        sensor=mySensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
        msv = new MyGameView(this);
        this.setContentView(msv);       
        
    }
    
	@Override
	protected void onResume() {						//重寫onResume方法
		mySensorManager.registerListener(mel, sensor, SensorManager.SENSOR_DELAY_UI);
		msv.gvdt.pauseFlag=false;
		msv.ballX=bx;
		msv.ballY=by;
		super.onResume();
	}
	
	@Override
	protected void onPause() {									//重寫onPause方法
		mySensorManager.unregisterListener(mel);	//取消註冊監聽器
		msv.gvdt.pauseFlag=true;
		bx=msv.ballX;
		by=msv.ballY;
		super.onPause();
	}
}