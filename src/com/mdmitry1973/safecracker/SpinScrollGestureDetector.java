package com.mdmitry1973.safecracker;

import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SpinScrollGestureDetector extends GestureDetector.SimpleOnGestureListener  {
	
	private SpinScroll mSpinScroll;
	
	public SpinScrollGestureDetector(SpinScroll spinScroll)
	{
		mSpinScroll = spinScroll;
	}
	
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		Log.i("onScroll", "onScroll distanceX=" + distanceX + "distanceY=" + distanceY);
		Log.i("onScroll", "onScroll e1=" + e1.getAction() + "e2=" + e2.getAction());
		
		mSpinScroll.onScroll(e1, e2, distanceX, distanceY);
		
		return super.onScroll(e1, e2, distanceX, distanceY);		
	}
	
	public boolean onSingleTapUp(MotionEvent ev)
	{
		Log.i("onSingleTapUp", "onSingleTapUp" + ev.getAction());
		return super.onSingleTapUp(ev);
	}
	
	public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
		 
		Log.i("onFling", "onFling velocityX=" + velocityX + "velocityY=" + velocityY);
		Log.i("onFling", "onFling e1=" + e1.getAction() + "e2=" + e2.getAction());
		
		//move more in Y axe
		if (Math.abs(velocityY) > Math.abs(velocityX))
		{
						
		}
		
		return super.onFling( e1,  e2,  velocityX,  velocityY);
	 }

	public boolean onDown(MotionEvent e) {
		
		Log.i("onDown", "onDown");
	    return true;//super.onDown( e);
	}
}
