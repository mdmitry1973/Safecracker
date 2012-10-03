package com.mdmitry1973.safecracker;

import android.content.Context;
import android.widget.ArrayAdapter;

class CircularArrayAdapter extends ArrayAdapter<Integer>
{   

        public static final int HALF_MAX_VALUE = Integer.MAX_VALUE/2;
        public final int MIDDLE;
        private Integer[] objects;

        public CircularArrayAdapter(Context context, int textViewResourceId, Integer[] objects)
        {
            super(context, textViewResourceId, objects);
            this.objects = objects;
            MIDDLE = HALF_MAX_VALUE - HALF_MAX_VALUE % objects.length;
        }

        @Override
        public int getCount()
        {
            return Integer.MAX_VALUE;
        }

        @Override
        public Integer getItem(int position) 
        {
            return objects[position % objects.length];
        }
 }
