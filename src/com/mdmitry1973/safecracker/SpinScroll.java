package com.mdmitry1973.safecracker;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.AttributeSet;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;


public class SpinScroll extends View {
	
	private GestureDetector mDetector;
	
	private int mCurrentValue;
	private float mScrollValue;
	
	private Rect mControlRect;
	private Paint mControlPaint;
	private Paint mTextPaint;


	public SpinScroll(Context context, AttributeSet attrs) {
		super(context, attrs);
		
		mCurrentValue = 0;
		mScrollValue = 0;
		mDetector =  new GestureDetector(context, new SpinScrollGestureDetector(this));
		
		mControlRect = new Rect(0, 0, 100, 100);
		mControlPaint = new Paint();
		mTextPaint = new Paint();
		
		mControlPaint.setARGB(255, 200, 200, 200);
		mTextPaint.setARGB(255, 0, 0, 0);
		mTextPaint.setTextSize(100);
	}
	
	public void setValue(int currentValue)
	{
		mCurrentValue = currentValue;
	}
	
	public int getValue()
	{
		return mCurrentValue;
	}
	
	protected void onDraw(Canvas canvas) {
		   super.onDraw(canvas);
		 
		   canvas.getClipBounds(mControlRect);		   
		   canvas.drawRect(mControlRect, mControlPaint);		   
		   canvas.drawText("" + mCurrentValue, 20, (mControlRect.height()/2 + mTextPaint.getTextSize()/2) - mScrollValue, mTextPaint);
	}
	
	public boolean onScroll(MotionEvent e1, MotionEvent e2, float distanceX, float distanceY)
	{
		mScrollValue = mScrollValue + distanceY;
		
		Log.i("onScroll", "onScroll mScrollValue=" + mScrollValue);
		
		if (Math.abs(mScrollValue) > mControlRect.height())//90)
		{
			if (mScrollValue > 0)
			{
				mCurrentValue++;
			}
			else
			{
				mCurrentValue--;				
			}
			
			if (mCurrentValue > 9)
			{
				mCurrentValue = 0;
			}
			
			if (mCurrentValue < 0)
			{
				mCurrentValue = 9;
			}
			
			mScrollValue = 0;
			
			Log.i("onScroll", "onScroll mCurrentValue=" + mCurrentValue);
			
			invalidate();			
		}
		else
		if (distanceY != 0)
		{
			invalidate();				
		}
		
		return true;
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		  
		  boolean result = mDetector.onTouchEvent(event);
		
		   return result;
	}

}
