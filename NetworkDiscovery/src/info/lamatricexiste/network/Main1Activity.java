package info.lamatricexiste.network;

import info.lamatricexiste.network.Network.HostBean;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;
import java.net.UnknownHostException;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.Surface;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Main1Activity extends Activity   {
	private float mLastX, mLastY, mLastZ;
	private boolean mInitialized;
	private SensorManager mSensorManager;
    private Sensor mAccelerometer;
    private Sensor gyro;
    private final float NOISE = (float) 2.0;
    private HostBean host;
		Socket s = null;
		boolean a=false;
boolean c=false;
	BufferedWriter out = null;
boolean svout;
	     EditText textOut;
	 	TextView textIn;
	 	 Button buttonSend;
	 	Button buttonMarche ;
	 	 Button buttonArret ;
	 	Button buttonTrans;
	 	Button buttonRot;
	 	
	 	//ok
	     /** Called when the activity is first created. */
	     @Override
	     public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.main2);
	         mInitialized = false;
	         mSensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);
	     	mSensorManager.registerListener(mySensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER), SensorManager.SENSOR_DELAY_GAME);
	 		mSensorManager.registerListener(mySensorEventListener, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION), SensorManager.SENSOR_DELAY_GAME);	
	        //
	      
	        buttonMarche = (Button)findViewById(R.id.marche);
	        buttonArret = (Button)findViewById(R.id.arret);
			buttonMarche.setEnabled(true);
			buttonTrans=(Button)findViewById(R.id.Trans);
			buttonRot=(Button)findViewById(R.id.Rot);

svout=true;
	        buttonArret.setEnabled(false);
	        buttonMarche.setEnabled(true);
	        buttonTrans.setEnabled(false);
	        buttonRot.setEnabled(false);


	         buttonMarche.setOnClickListener(buttonMarcheOnClickListener);
	         buttonArret.setOnClickListener(buttonArretOnClickListener);

	         buttonTrans.setOnClickListener(buttonTransOnClickListener);
	         buttonRot.setOnClickListener(buttonRotOnClickListener);

	         	     }
	     
	     Button.OnClickListener buttonTransOnClickListener
	     = new Button.OnClickListener(){

	 		@Override
	 		public void onClick(View arg0) {
	 			// TODO Auto-generated method stub
	 			
	 			
	 				
	 		
				buttonTrans.setEnabled(false);
				buttonRot.setEnabled(true);
		 		svout=true;

				
	 				
	 				
	 				
	 			
	 		}};
	 		
	 		
	 		
	 		 

	 		Button.OnClickListener buttonRotOnClickListener
	 		= new Button.OnClickListener(){

	 			@Override
	 			public void onClick(View arg0) {
	 				// TODO Auto-generated method stub



	 				 
	 					buttonTrans.setEnabled(true);
	 					buttonRot.setEnabled(false);
	 					svout=false;

	 				


	 			}

};

	 		
	 		
	 		
	 		
	 		
	 		
	 		Button.OnClickListener buttonMarcheOnClickListener
		     = new Button.OnClickListener(){

		 		@Override
		 		public void onClick(View arg0) {
		 			// TODO Auto-generated method stub
		 			if (s!=null) s=null;
		 			Intent intent = getIntent();
			         Bundle extras = intent.getExtras();
			         host = new HostBean();
		             
			         if (extras != null) {
			             if (intent.hasExtra(HostBean.EXTRA)) {
			                 host = intent.getParcelableExtra(HostBean.EXTRA);
			             } else {
			         host.ipAddress = extras.getString(HostBean.EXTRA_HOST);
		             if (extras==null)              Toast.makeText(getApplicationContext(), "null", Toast.LENGTH_SHORT).show();
		             
		             
			             }}
						


		 			try {if(s!=null) out.close();
						s = new Socket(host.ipAddress, 8888);
						if (s==null){
							s.close();}
							
					//		
if (s!=null)
						 a=true;
					} catch (UnknownHostException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					if (s==null){
					Toast.makeText(Main1Activity.this,
	                        "Serveur introvable", Toast.LENGTH_SHORT).show();}
					
					if(s!=null) {
						buttonMarche.setEnabled(false);
						buttonArret.setEnabled(true);
						 buttonTrans.setEnabled(false);
					        buttonRot.setEnabled(true);

						svout=true;
						}
		 		}};
	 		
		 		
		 		
		 		
		 		
		 		
		 		Button.OnClickListener buttonArretOnClickListener
			     = new Button.OnClickListener(){

			 		
			 		public void onClick(View arg0) {
a=false;

	buttonMarche.setEnabled(true);
	buttonArret.setEnabled(false);
	 buttonTrans.setEnabled(false);
     buttonRot.setEnabled(false);

		        


			 			// TODO Auto-generated method stub
			 		}};
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
		 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		
	 		

	 		protected void onPause() {
	 	         super.onPause();
					try {
						if (s!=null)
						{out.write( "OnPause");
						out.flush();}

					    mSensorManager.unregisterListener(mySensorEventListener);

				
						s.close();
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					
}
	 		


	 		protected void onResume() {
	 		    super.onResume();
	 		  }
	 		
	 		
	 		private SensorEventListener mySensorEventListener = new SensorEventListener(){

		 		public void onAccuracyChanged(Sensor sensor, int accuracy) {
		 			// can be safely ignored for this demo
		 		}
	 			public void onSensorChanged(SensorEvent event) {
	 	        
	 				Sensor sensor = event.sensor;
 
	 	 			if (a==true){

	 				
	 	        if (sensor.getType() == Sensor.TYPE_ACCELEROMETER
	 	        		) {
if (svout==true)	{  				

	 	 			TextView tvX= (TextView)findViewById(R.id.x_axis);
	 	 			TextView tvY= (TextView)findViewById(R.id.y_axis);
	 	 			TextView tvZ= (TextView)findViewById(R.id.z_axis);
	 	 			float x = event.values[0];
	 	 			float y = event.values[1];
	 	 			float z = event.values[2];
	 	 			if (!mInitialized) {
	 	 				mLastX = x;
	 	 				mLastY = y;
	 	 				mLastZ = z;
	 	 				
	 	 				mInitialized = true;
	 	 			} else {
	 	 				float deltaX = event.values[0];
	 	 				float deltaY =event.values[1];
	 	 				float deltaZ = event.values[2];
	 	 				
	 	 				mLastX = x;
	 	 				mLastY = y;
	 	 				mLastZ = z;
	 	 				tvX.setText(Float.toString(deltaX));
	 	 				tvY.setText(Float.toString(deltaY));
	 	 				tvZ.setText(Float.toString(deltaZ));
	 if(c==true){
	 					try {
	 						if (s!=null)s = new Socket(host.ipAddress, 8888);
	 						
	 					} catch (UnknownHostException e3) {
	 						// TODO Auto-generated catch block
	 						e3.printStackTrace();
	 					} catch (IOException e3) {
	 						// TODO Auto-generated catch block
	 						e3.printStackTrace();
	 					}
	 }
	 						
	 						
	 				
	 					
	 	 			
	 						try {if (s==null)
	 							s.close();
	 						else 
	 						{	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));}
	 						} catch (IOException e2) {
	 							// TODO Auto-generated catch block
	 											}
	 					
	 					try {if (s==null){
	 						s.close();	 				 
	 					}else
	 						{out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));}
	 					} catch (IOException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					}
	 	 				try { if (s==null)
	 						s.close(); String messagee ="X ["+tvX.getText().toString()+"]"+ " Y ["+tvY.getText().toString()+"]"+" Z ["+tvZ.getText().toString()+"]";

	 				    int i=0;
	 				    if (tvX.getText().toString().length()<6)
	 				    {i=6-tvX.getText().toString().length();
	 				    for (int j=0;j<i;j++)
	 				    {
	 				    	 tvX.setText(tvX.getText().toString()+"0");
	 				    }		
	 				    i=0;}
	 				    
	 				    
	 				    else 
	 				    if (tvY.getText().toString().length()<6)
	 				    {i=6-tvY.getText().toString().length();
	 				    for (int v=0;v<i;v++)
	 				    {
	 				    	 tvY.setText(tvY.getText().toString()+"0");
	 				    }		
	 				    i=0; }
	 				    
	 				    else 
	 					    if (tvZ.getText().toString().length()<6)
	 					    {i=6-tvZ.getText().toString().length();
	 					    for (int b=0;b<i;b++)
	 					    {
	 					    	 tvZ.setText(tvZ.getText().toString()+"0");
	 					    }		
	 					    i=0; }

	 	String message =tvX.getText().toString().substring(0, 6)+tvY.getText().toString().substring(0, 6)+tvZ.getText().toString().substring(0, 6);
	 						out.write( message);
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 				try {if (s==null)
	 						s.close();
	 	 				else
	 	 					if (out.equals(null) )out.write("4.07017.89124.2999");
	 						out.flush();out.close();
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 				
	 	 				//close connection
	 	 				try {if (out==null)
	 						s.close();c=true;
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 			}}}
	 	        
	 	        else  if (sensor.getType() == Sensor.TYPE_ORIENTATION
	 	        		) {
if (svout==false)	{  				

	 	 			TextView tvX= (TextView)findViewById(R.id.x_axis);
	 	 			TextView tvY= (TextView)findViewById(R.id.y_axis);
	 	 			TextView tvZ= (TextView)findViewById(R.id.z_axis);
	 	 			float x = event.values[0];
	 	 			float y = event.values[1];
	 	 			float z = event.values[2];
	 	 			if (!mInitialized) {
	 	 				mLastX = x;
	 	 				mLastY = y;
	 	 				mLastZ = z;
	 	 				
	 	 				mInitialized = true;
	 	 			} else {
	 	 				float deltaX = event.values[0];
	 	 				float deltaY =event.values[1];
	 	 				float deltaZ = event.values[2];
	 	 				
	 	 				mLastX = x;
	 	 				mLastY = y;
	 	 				mLastZ = z;
	 	 				tvX.setText(Float.toString(deltaX));
	 	 				tvY.setText(Float.toString(deltaY));
	 	 				tvZ.setText(Float.toString(deltaZ));
	 if(c==true){
	 					try {
	 						if (s!=null)s = new Socket(host.ipAddress, 8888);
	 						
	 					} catch (UnknownHostException e3) {
	 						// TODO Auto-generated catch block
	 						e3.printStackTrace();
	 					} catch (IOException e3) {
	 						// TODO Auto-generated catch block
	 						e3.printStackTrace();
	 					}
	 }
	 						
	 						
	 				
	 					
	 	 			
	 						try {if (s==null)
	 							s.close();
	 						else 
	 						{	BufferedReader in = new BufferedReader(new InputStreamReader(s.getInputStream()));}
	 						} catch (IOException e2) {
	 							// TODO Auto-generated catch block
	 											}
	 					
	 					try {if (s==null){
	 						s.close();	 				 
	 					}else
	 						{out = new BufferedWriter(new OutputStreamWriter(s.getOutputStream()));}
	 					} catch (IOException e1) {
	 						// TODO Auto-generated catch block
	 						e1.printStackTrace();
	 					}
	 	 				try { if (s==null)
	 						s.close(); String messagee ="X ["+tvX.getText().toString()+"]"+ " Y ["+tvY.getText().toString()+"]"+" Z ["+tvZ.getText().toString()+"]";

	 				    int i=0;
	 				    if (tvX.getText().toString().length()<6)
	 				    {i=6-tvX.getText().toString().length();
	 				    for (int j=0;j<i;j++)
	 				    {
	 				    	 tvX.setText(tvX.getText().toString()+"0");
	 				    }		
	 				    i=0;}
	 				    
	 				    
	 				    else 
	 				    if (tvY.getText().toString().length()<6)
	 				    {i=6-tvY.getText().toString().length();
	 				    for (int v=0;v<i;v++)
	 				    {
	 				    	 tvY.setText(tvY.getText().toString()+"0");
	 				    }		
	 				    i=0; }
	 				    
	 				    else 
	 					    if (tvZ.getText().toString().length()<6)
	 					    {i=6-tvZ.getText().toString().length();
	 					    for (int b=0;b<i;b++)
	 					    {
	 					    	 tvZ.setText(tvZ.getText().toString()+"0");
	 					    }		
	 					    i=0; }

	 	String message =tvX.getText().toString().substring(0, 6)+tvY.getText().toString().substring(0, 6)+tvZ.getText().toString().substring(0, 6);
	 						out.write( message);
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 				try {if (s==null)
	 						s.close();
	 	 				else
	 	 					if (out.equals(null) )out.write("4.07017.89124.2999");
	 						out.flush();out.close();
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 				
	 	 				//close connection
	 	 				try {if (out==null)
	 						s.close();c=true;
	 					} catch (IOException e) {
	 						// TODO Auto-generated catch block
	 						e.printStackTrace();
	 					}
	 	 			}}}
	 	 			
	 	 			
	 	 			
	 	 			}}};
	 	
	 }
