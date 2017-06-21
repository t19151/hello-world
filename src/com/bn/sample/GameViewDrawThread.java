package com.bn.sample10_1;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

//定時重新繪製畫面的線程
public class GameViewDrawThread extends Thread{
	boolean flag = true;
	boolean pauseFlag=false;
	int sleepSpan = 50;
	MyGameView gameView;
	SurfaceHolder surfaceHolder;
	public GameViewDrawThread(MyGameView gameView){
		this.gameView = gameView;
		this.surfaceHolder = gameView.getHolder();
	}
	public void run(){
		Canvas c;
        while (this.flag) {
            c = null;
            if(!pauseFlag)
            {
            	try {
	            	// 鎖定整個畫布，在內存要求比較高的情況下，建議參數不要為null
	                c = this.surfaceHolder.lockCanvas(null);
	                synchronized (this.surfaceHolder) {
	                	gameView.draw(c);//繪製
	                }
	            } finally {
	                if (c != null) {
	                	//並釋放鎖
	                    this.surfaceHolder.unlockCanvasAndPost(c);
	                }
	            }
            }
	            
            try{
            	Thread.sleep(sleepSpan);//睡眠指定毫秒數
            }
            catch(Exception e){
            	e.printStackTrace();//打印堆棧信息
            }
        }
	}
}
