package com.mdmitry1973.safecracker;

import java.util.Vector;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class LazyAdapter extends BaseAdapter {

	 	private Activity activity;
	 	private Vector<String> data;
	    private Vector<Integer> data_image_1;
	    private Vector<Integer> data_image_2;
	    private static LayoutInflater inflater=null;
	    
	    public LazyAdapter(Activity a, Vector<String> d, Vector<Integer> d1, Vector<Integer> d2) {
	        activity = a;
	        data=d;
	        data_image_1 = d1;
	        data_image_2 = d2;
	        inflater = (LayoutInflater)activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	    }

	    public int getCount() {
	        return data.size();
	    }

	    public Object getItem(int position) {
	        return position;
	    }

	    public long getItemId(int position) {
	        return position;
	    }
	    
	    public View getView(int position, View convertView, ViewGroup parent) {
	        View vi=convertView;
	        if(convertView==null)
	            vi = inflater.inflate(R.layout.item, null);

	        TextView text=(TextView)vi.findViewById(R.id.text);
	        ImageView image1=(ImageView)vi.findViewById(R.id.image_1);
	        ImageView image2=(ImageView)vi.findViewById(R.id.image_2);
	        text.setText(data.get(position));
	        
	        image1.setVisibility(View.VISIBLE);
	        image2.setVisibility(View.VISIBLE);
	    
	        if (data_image_1.get(position) == 1)
	        	image1.setImageResource(R.drawable.coin_gold_1);
	        else
	        if (data_image_1.get(position) == 2)
	        	image1.setImageResource(R.drawable.coin_gold_2);
	        else
	        if (data_image_1.get(position) == 3)
	        	image1.setImageResource(R.drawable.coin_gold_3);
	        else
	        if (data_image_1.get(position) == 4)
	        	image1.setImageResource(R.drawable.coin_gold_4);
	        else
	        if (data_image_1.get(position) == 5)
	        	image1.setImageResource(R.drawable.coin_gold_5);
	        else
	        if (data_image_1.get(position) == 6)
	        	image1.setImageResource(R.drawable.coin_gold_6);
	        else
	        {
	        	image1.setVisibility(View.INVISIBLE);
	        }
	        
	        if (data_image_2.get(position) == 1)
	        	image2.setImageResource(R.drawable.coin_silver_1);
	        else
	        if (data_image_2.get(position) == 2)
	        	image2.setImageResource(R.drawable.coin_silver_2);
	        else
	        if (data_image_2.get(position) == 3)
	        	image2.setImageResource(R.drawable.coin_silver_3);
	        else
	        if (data_image_2.get(position) == 4)
	        	image2.setImageResource(R.drawable.coin_silver_4);
	        else
	        if (data_image_2.get(position) == 5)
	 	        image2.setImageResource(R.drawable.coin_silver_5);
	 	    else
	 	    if (data_image_2.get(position) == 6)
	 		    image2.setImageResource(R.drawable.coin_silver_6);
	 		else
	        {
	        	image2.setVisibility(View.INVISIBLE);
	        }
	        
	        //Log.i("dataString", "dataString" + strItem + "count_full_com=" + count_full_com + "count_part_com=" + count_part_com);  
	    	
	        
	        return vi;
	    }

}
