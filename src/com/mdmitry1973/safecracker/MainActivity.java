package com.mdmitry1973.safecracker;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

import java.util.*;
import android.app.AlertDialog;
import android.content.DialogInterface;
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

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        r_num = new int [4];
        
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
        
        listNumbersItems[0] = 0;
        listNumbersItems[1] = 1;
        listNumbersItems[2] = 2;
        listNumbersItems[3] = 3;
        listNumbersItems[4] = 4;
        listNumbersItems[5] = 5;
        listNumbersItems[6] = 6;
        listNumbersItems[7] = 7;
        listNumbersItems[8] = 8;
        listNumbersItems[9] = 9;
        
        listNumbers = new ListView [4];
        
        listNumbers[0] = (ListView) findViewById(R.id.ListView1);
        listNumbers[1] = (ListView) findViewById(R.id.ListView2);
        listNumbers[2] = (ListView) findViewById(R.id.ListView3);
        listNumbers[3] = (ListView) findViewById(R.id.ListView4);
        
        listNumbersArrayAdapter = new CircularArrayAdapter [4];
       
        for(int i = 0; i < 4; i++)
        {
        	listNumbersArrayAdapter[i] = new CircularArrayAdapter(listNumbers[i].getContext(), android.R.layout.simple_list_item_1, listNumbersItems);
            listNumbers[i].setAdapter(listNumbersArrayAdapter[i]);
            listNumbersArrayAdapter[i].notifyDataSetChanged();         	
        }
      
        ResetNum();
    }
/*
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    */
 
    private void ResetNum()
    {
    	int i;
    	Random generator = new Random();
    	
    	countSteps = 0;
    	
    	r_num[0] = generator.nextInt(9);
    	r_num[0] = generator.nextInt(9);
    	r_num[0] = generator.nextInt(9);
        
    	for(i = 0; i < 4; i++)
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
    	
    	for(i = 0; i < 4; i++)
    	{
    		listNumbers[i].setSelection(i);
    	}
    }
    
    public void clickTry(View view) {
        
    	long num_temp[];
    	int count_full_com = 0;
    	int count_part_com = 0;
    	boolean hasDub = false; 
    	
    	num_temp = new long [4];
    	
    	for(int i = 0; i < 4; i++)
    	{
    		num_temp[i] = listNumbers[i].pointToPosition(listNumbers[i].getHeight()/2, listNumbers[i].getWidth()/2);
    		
    		if (num_temp[i] > 9)
    		{
    			int tt = (int) (num_temp[i]/10);
    			
    			num_temp[i] = num_temp[i] - (tt *10);
    			
    		}
    		//Log.i("num_temp", "num_temp[" + i + "]=" + num_temp[i]);
    	}
    	
    	for(int i = 0; i < 3; i++)
    	{
    		for(int n = i + 1; n < 4; n++)
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
    		alertDialog.setTitle("Alert");
    		alertDialog.setMessage("You need have different numbers.");
    		alertDialog.setButton( AlertDialog.BUTTON_NEUTRAL, "OK", new DialogInterface.OnClickListener() {
    		   public void onClick(DialogInterface dialog, int which) {
    			   dialog.cancel();
    		   }
    		});
    		//alertDialog.setIcon(R.drawable.icon);
    		alertDialog.show();
    		return;
    	}
    	
    	for(int i = 0; i < 4; i++)
    	{
    		for(int n = 0; n < 4; n++)
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
    	
    	strItem = String.format("%d %d%d%d%d", countSteps, num_temp[0], num_temp[1], num_temp[2], num_temp[3]);//, count_full_com, count_part_com);//, r_num[0], r_num[1], r_num[2], r_num[3]);
    	
    	dataString.insertElementAt(strItem, 0);
        data_image_1.insertElementAt(count_full_com, 0);
        data_image_2.insertElementAt(count_part_com, 0);
        
        //Log.i("dataString", "dataString" + strItem + "count_full_com=" + count_full_com + "count_part_com=" + count_part_com);  
    	
    	if (count_full_com == 4)
    	{
    		strItem = String.format("The end game. You found right number %d%d%d%d", r_num[0], r_num[1], r_num[2], r_num[3]);
    		dataString.insertElementAt(strItem, 0);
            data_image_1.insertElementAt(-1, 0);
            data_image_2.insertElementAt(-1, 0);
    		listArrayAdapter.notifyDataSetChanged();   
    		
    		AlertDialog alertDialog = new AlertDialog.Builder(this).create();
    		alertDialog.setTitle("Alert");
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
    
    public void clickReset(View view) {
    	
    	ResetNum();
    	
    	dataString.clear();
        data_image_1.clear();
        data_image_2.clear();
    	listArrayAdapter.notifyDataSetChanged();
    }
    
    public void clickExit(View view) {
    	
    	finish();
    }
}
