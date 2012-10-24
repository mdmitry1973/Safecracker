package com.mdmitry1973.safecracker;

import android.os.Bundle;
import android.os.Debug;
import android.os.Environment;
import android.app.Activity;
import android.app.Dialog;
import android.util.Log;
import java.util.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.widget.*;
import android.view.*;

public class MainActivity extends Activity {
	
	private int countSteps;
	
	private int r_num[];

	private ListView listResult;
	private LazyAdapter listArrayAdapter;
	
	private Integer[] listNumbersItems;
	
	private ListView[] listNumbers;
	private CircularArrayAdapter[] listNumbersArrayAdapter;
	
	private Vector<String> dataString;
    private Vector<Integer> data_image_1;
    private Vector<Integer> data_image_2;
    
    private int numberDigites;
    
    private static final boolean DEBUG = false;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
       
        if (DEBUG) 
        {
        	Debug.startMethodTracing("sampletrace");
        	Log.d("sampletrace", Environment.getExternalStorageDirectory().toString());
        }
        
        numberDigites = 4;
        
        setTitle(R.string.app_name);
        
        try{
        
        	listResult = (ListView) findViewById(R.id.listViewResult);  
        	
        	dataString = new Vector<String>();
            data_image_1 = new Vector<Integer>();
            data_image_2 = new Vector<Integer>();
        	listArrayAdapter = new LazyAdapter(this, dataString, data_image_1, data_image_2);
        	listResult.setAdapter(listArrayAdapter);
        
        }
    	catch(Exception exp)
    	{
    		//Log.i("Exception", "Exception " + exp.toString());    		
    	}
        
        try{
        	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(listResult.getContext(), 
        			R.array.num_spinner_array, android.R.layout.simple_spinner_item);
        	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);        
        }
    	catch(Exception exp)
    	{
    		//Log.i("Exception", "Exception " + exp.toString());    		
    	}
        
        listNumbersItems = new Integer [10];
        
        for(int i = 0; i < 10; i++)
        {
        	listNumbersItems[i] = i;
        }
        
        
        ResetNum();
    }
    
    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
       super.onWindowFocusChanged(hasFocus);
       
       LinearLayout mainLayout = (LinearLayout) findViewById(R.id.linearLayoutMain); 
       LinearLayout pickLayout = (LinearLayout) findViewById(R.id.linearLayout1); 
       Button buttonsExit = (Button) findViewById(R.id.buttonExit); 
       
       int heightMainLayout = mainLayout.getHeight();
       int heightButtonLayout = buttonsExit.getHeight();
       int heightPickers = pickLayout.getLayoutParams().height;
       
       if (heightButtonLayout == 0)
       {
    	   heightButtonLayout = 50;
       }
       
       listResult.getLayoutParams().height = heightMainLayout - heightPickers - heightButtonLayout;
       
       mainLayout.requestLayout();
       mainLayout.invalidate();
    }

/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    */
    
    private void SetupLayout()
    {
    	int i;
    	int width = 0;
    	Random generator = new Random();
    
    	LinearLayout linearLayout1 = (LinearLayout)findViewById(R.id.linearLayout1);
    	
    	linearLayout1.removeAllViews();
    	
    	Display display = getWindowManager().getDefaultDisplay();
    	width = display.getWidth();

    	width = width / numberDigites;
		 
    	listNumbers = new ListView [numberDigites];
    	listNumbersArrayAdapter = new CircularArrayAdapter [numberDigites];
		 
    	for(i = 0; i < numberDigites; i++)
    	{
		 	ListView listNumber = new ListView(this);
		 
		    listNumber.setBackgroundResource(R.drawable.ic_item_number_background);
		    listNumber.setDividerHeight(0);		    
		    listNumber.setChoiceMode(ListView.CHOICE_MODE_SINGLE);
		    listNumber.setScrollingCacheEnabled(false);
		    listNumber.setFooterDividersEnabled(false);
		    listNumber.setHeaderDividersEnabled(false);
		    listNumber.setVerticalScrollBarEnabled(false);
		    listNumber.setHorizontalScrollBarEnabled(false);
		  
		    listNumbers[i] = listNumber;
		    
		    listNumbersArrayAdapter[i] = new CircularArrayAdapter(listNumbers[i].getContext(), android.R.layout.simple_list_item_1, listNumbersItems);
		    listNumbers[i].setAdapter(listNumbersArrayAdapter[i]);
		    listNumbersArrayAdapter[i].notifyDataSetChanged();         	
		    
		    linearLayout1.addView(listNumber, new ListView.LayoutParams(width, ViewGroup.LayoutParams.FILL_PARENT));
    	}
    	
    	countSteps = 0;
    	
    	r_num = new int [numberDigites];
    	
    	r_num[0] = generator.nextInt(9);
    	r_num[0] = generator.nextInt(9);
    	r_num[0] = generator.nextInt(9);
        
    	for(i = 0; i < numberDigites; i++)
    	{
    		if (i > 0)
    		{
    			boolean  found = false;
    			
    			do
    			{
    				found = false;
    				
    				r_num[i] = generator.nextInt(9);
    				
	    			for(int n = 0; n < i; n++)
	    			{
	    				if (r_num[n] == r_num[i])
	    				{
	    					found = true;
	    				}
	    			}
    			}
    			while(found);		
    		}
    		else
    		{
    			r_num[i] = generator.nextInt(9);
    		}
    	}
    	
    	for(i = 0; i < numberDigites; i++)
    	{
    		listNumbers[i].setSelection(i);
    	}
    }
 
    private void ResetNum()
    {	
		 final Dialog dialogChooseLevel = new Dialog(this);

    	 dialogChooseLevel.setContentView(R.layout.start_page);
    	 dialogChooseLevel.setTitle(R.string.Chooselevel);
    	
    	 Button buttonBeginner		= (Button) dialogChooseLevel.findViewById(R.id.Beginner);
    	 Button buttonIntermediate	= (Button) dialogChooseLevel.findViewById(R.id.Intermediate);
    	 Button buttonExpert		= (Button) dialogChooseLevel.findViewById(R.id.Expert);
    	
    	 
    	 buttonBeginner.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v) {
	        		
	        		numberDigites = 4;
	        		dialogChooseLevel.cancel();
	        		SetupLayout();
	            }
	        });
    	 
    	 buttonIntermediate.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v) {
	        		
	        		numberDigites = 5;
	        		dialogChooseLevel.cancel();
	        		SetupLayout();
	            }
	        });
    	 
    	 buttonExpert.setOnClickListener(new View.OnClickListener() {
	        	public void onClick(View v) {
	        		
	        		numberDigites = 6;
	        		dialogChooseLevel.cancel();
	        		SetupLayout();
	            }
	        });
    	
    	 dialogChooseLevel.show();
    }
    
    public void clickTry(View view) {
        
    	long num_temp[];
    	int count_full_com = 0;
    	int count_part_com = 0;
    	boolean hasDub = false; 
    	Resources res = getResources();
    	
    	num_temp = new long [numberDigites];
    	
    	for(int i = 0; i < numberDigites; i++)
    	{
    		num_temp[i] = listNumbers[i].pointToPosition(listNumbers[i].getHeight()/2, listNumbers[i].getWidth()/2);
    		
    		if (num_temp[i] > 9)
    		{
    			int tt = (int) (num_temp[i]/10);
    			
    			num_temp[i] = num_temp[i] - (tt *10);
    			
    		}
    		//Log.i("num_temp", "num_temp[" + i + "]=" + num_temp[i]);
    	}
    	
    	for(int i = 0; i < numberDigites - 1; i++)
    	{
    		for(int n = i + 1; n < numberDigites; n++)
        	{
    			if (num_temp[i] == num_temp[n])
    			{
    				hasDub = true;
    				break;
    			}    			
        	}    		
    	}
    	
    	if (hasDub)
    	{
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle(R.string.Alert);
    		alertDialog.setMessage(res.getString(R.string.AlertMsg));
    		alertDialog.setButton( AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
    		   public void onClick(DialogInterface dialog, int which) {
    			   dialog.cancel();
    		   }
    		});
    	
    		alertDialog.show();
    		return;
    	}
    	
    	for(int i = 0; i < numberDigites; i++)
    	{
    		for(int n = 0; n < numberDigites; n++)
        	{
    			if (r_num[i] == num_temp[n])
    			{
    				if (i == n)
        			{
    					count_full_com++;    					
        			}
    				else
    				{
    					count_part_com++;    					
    				}    				
    			}
        	}    	
    	}
    	
    	String strItem = new String();
    	
    	countSteps++;
    	
    	strItem = String.format("%d ", countSteps);
    	
    	for(int i = 0; i < numberDigites; i++)
		{
			strItem = strItem + String.format("%d", num_temp[i]);
		}
    	
    	dataString.insertElementAt(strItem, 0);
        data_image_1.insertElementAt(count_full_com, 0);
        data_image_2.insertElementAt(count_part_com, 0);
        
        //Log.i("dataString", "dataString" + strItem + "count_full_com=" + count_full_com + "count_part_com=" + count_part_com);  
    	
    	if (count_full_com == numberDigites)
    	{
    		strItem = res.getString(R.string.AlertMsg2);
    		for(int i = 0; i < numberDigites; i++)
    		{
    			strItem = strItem + String.format("%d", r_num[i]);
    		}
    		dataString.insertElementAt(strItem, 0);
            data_image_1.insertElementAt(-1, 0);
            data_image_2.insertElementAt(-1, 0);
    		listArrayAdapter.notifyDataSetChanged();   
    		
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle(R.string.TheEnd);
    		alertDialog.setMessage(strItem);
    		alertDialog.setButton( AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
    		   public void onClick(DialogInterface dialog, int which) {
    			   dialog.cancel();
    		   }
    		});
    		//alertDialog.setIcon(R.drawable.icon);
    		alertDialog.show();
    		return;
    	}
    
    	listArrayAdapter.notifyDataSetChanged();   
    }
    
    public void clickReset(View view) 
    {
    	ResetNum();
    	
    	dataString.clear();
        data_image_1.clear();
        data_image_2.clear();
    	listArrayAdapter.notifyDataSetChanged();
    }
    
    public void clickExit(View view) 
    {
    	finish();
    	
    	if (DEBUG) 
        {
    		Debug.stopMethodTracing();
        }
    }
}
