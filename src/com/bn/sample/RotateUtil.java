package com.bn.sample10_1;
/*
 * 該類為靜態工具類，提供靜態方法來計算
 * 小球應該的運動方向
 */
public class RotateUtil
{
	public static int[] getDirectionDot(double[] values)
	{
		//虛擬一個重力向量
		double[] gVector={0,0,-100,1};
		
		//yaw軸恢復
		gVector[0]=values[0];
		
		//pitch軸恢復
		gVector[1]=values[1];	
		
		//roll軸恢復
		gVector[2]=values[2];
		
		double mapX=gVector[0];
		double mapY=gVector[1];		
		
		int[] result={(int)mapX,(int)mapY};
		return result;
	}	
}