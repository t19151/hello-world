package com.bn.sample10_1;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class MyGameView extends SurfaceView implements SurfaceHolder.Callback  //實現生命週期回調接口
{
	Sample10_1Activity activity;
	BallGoThread bgt;
	GameViewDrawThread gvdt;
	Paint paint;//畫筆
	Paint paint1;
	Paint paint2;
	
	int ballSize=140;//球的尺寸
	int dLength=5;//標準步進
	int ballX;
	int ballY;
	int dx=2;
	int dy=2;
	
	public MyGameView(Sample10_1Activity activity) {
		super(activity);
		this.activity = activity;
		this.getHolder().addCallback(this);//設置生命週期回調接口的實現者
		
		paint = new Paint();//創建畫筆
		paint.setStrokeWidth(40);
		paint.setARGB(255, 102, 56, 24);
		paint.setAntiAlias(true);//打開抗鋸齒
		
		paint1=new Paint();
		paint1.setColor(Color.RED);
		paint1.setStyle(Style.STROKE);
		paint1.setStrokeWidth(45);
		paint1.setAntiAlias(true);
		
		paint2=new Paint();
		paint2.setColor(Color.BLUE);
		paint2.setStyle(Style.FILL);
		paint2.setAntiAlias(true);
		
		bgt=new BallGoThread(this);
		gvdt=new GameViewDrawThread(this);
	}

	@Override
	public void draw(Canvas canvas) {
		super.draw(canvas);
		canvas.drawLine(0, 0, 550, 0, paint);
		canvas.drawLine(0, 0, 0, 980, paint);
		canvas.drawLine(540, 10,540, 980, paint);
		canvas.drawLine(10, 960, 540, 960, paint);
		
		
		canvas.drawCircle(ballX+73, ballY+71, 40, paint1);
		canvas.drawCircle(ballX+73, ballY+71, 38, paint2);
	}

	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		
	}

	public void surfaceCreated(SurfaceHolder holder) {//創建時被調用
		Canvas canvas = holder.lockCanvas();//獲取畫布
		try{
			synchronized(holder){
				draw(canvas);//繪製
			}			
		}
		catch(Exception e){
			e.printStackTrace();
		}
		finally{
			if(canvas != null){
				holder.unlockCanvasAndPost(canvas);
			}
		}
		//啟動球定時根據重力移動的線程
		bgt.start();
		//啟動定時重新繪製畫面的線程
		gvdt.start();
	}

	public void surfaceDestroyed(SurfaceHolder arg0) {/*銷毀時被調用*/}
}