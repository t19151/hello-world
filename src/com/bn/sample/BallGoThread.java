package com.bn.sample10_1;

//球定時根據重力移動的線程
public class BallGoThread extends Thread
{
	MyGameView mgv;
	boolean flag=true;
	
	public BallGoThread(MyGameView mgv)
	{
		this.mgv=mgv;
	}
	
	public void run()
	{
		while(flag)
		{
			//計算球的新位置
			int dx=mgv.dx;
			int dy=mgv.dy;			
			mgv.ballX=mgv.ballX-dx;
			mgv.ballY=mgv.ballY+dy;
			
			//判斷X方向是否碰壁，若碰壁則恢復
			if(mgv.ballX<0||mgv.ballX>mgv.getWidth()-mgv.ballSize)
			{
				mgv.ballX=mgv.ballX+dx;
			}
			//判斷Y方向是否碰壁，若碰壁則恢復
			if(mgv.ballY<0||mgv.ballY>mgv.getHeight()-mgv.ballSize)
			{
				mgv.ballY=mgv.ballY-dy;
			}
			
			try
			{
				Thread.sleep(50);
			}
			catch(Exception e)
			{
				e.printStackTrace();
			}
		}
	}
}
