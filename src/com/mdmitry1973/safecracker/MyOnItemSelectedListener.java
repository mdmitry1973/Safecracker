package com.mdmitry1973.safecracker;

import android.view.View;
import android.widget.AdapterView;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;

public class MyOnItemSelectedListener implements OnItemSelectedListener {
	
	private TextView m_t;


	public MyOnItemSelectedListener(TextView t) {
		m_t = t;
	}

	public void onItemSelected(AdapterView<?> parent,
	        View view, int pos, long id) {
		String str = parent.getItemAtPosition(pos).toString();
		
		m_t.setText(str);
	}

	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}
	
	

}

