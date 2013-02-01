package com.example.stopwatch;

import android.os.Bundle;
import android.os.SystemClock;
import android.app.Activity;
import android.text.format.DateFormat;
import android.view.Menu;
import android.view.GestureDetector;
import android.view.GestureDetector.SimpleOnGestureListener;
import android.view.MotionEvent;
import android.view.Window;
import android.widget.Chronometer;
import android.widget.Chronometer.OnChronometerTickListener;

public class Stopwatch extends Activity {
	boolean started = false;
	boolean reset = false;
	GestureDetector g;
	Chronometer c;
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_stopwatch);
        g = new GestureDetector(this.getApplicationContext(), simpleOnGestureListener);
        c = (Chronometer) findViewById(R.id.timer);
        c.setText("00:00:00");
        
        c.setOnChronometerTickListener(new OnChronometerTickListener(){
			public void onChronometerTick(Chronometer arg0) {
				long t = SystemClock.elapsedRealtime() - arg0.getBase();
		        arg0.setText(DateFormat.format("kk:mm:ss", t));
			}
        	
        });
        
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_stopwatch, menu);
        return true;
    }
    
    
    public boolean dispatchTouchEvent(MotionEvent mEvent){
		super.dispatchTouchEvent(mEvent);
		return g.onTouchEvent(mEvent);
	}
    
    SimpleOnGestureListener simpleOnGestureListener
	= new SimpleOnGestureListener(){
		public boolean onFling(MotionEvent e1, MotionEvent e2, float vX, float vY){
			return super.onFling(e1,e2,vX,vY);
		}

		public boolean onDoubleTapEvent(MotionEvent e){
			return super.onDoubleTapEvent(e);
		}

		public boolean onSingleTapConfirmed(MotionEvent e){
			if(started){
				started = false;
				reset = true;
				c.stop();
			}else if(reset){
				reset = false;
				c.setText("00:00:00");
			}else if (!started){
				started = true;
				c.setBase(SystemClock.elapsedRealtime());
				c.start();
			}
			return super.onSingleTapConfirmed(e);
		}

		public void onLongPress(MotionEvent e){
		}

	};
	
}
