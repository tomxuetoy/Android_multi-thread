package com.example.threadtrial;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText edittext1, edittext2;
	volatile boolean bThreadRun = false;

	MyThread1 myThread1;
	MyThread2 myThread2;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edittext1 = (EditText) findViewById(R.id.EditText1);
		edittext2 = (EditText) findViewById(R.id.EditText2);

		edittext1.setText("this is thread1");
		edittext2.setText("this is thread2");
	}

	@Override
	protected void onStart() {
		super.onStart();
		
		bThreadRun = true;
		myThread1 = new MyThread1();
		myThread1.start();
		myThread2 = new MyThread2();
		myThread2.start();
	}

	@Override
	protected void onPause() {
		super.onPause();
		bThreadRun = false;
		// myThread.stop();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	private Handler mHandler1 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 1:
				updateEditText1();
				break;
			}
		};
	};
	
	private Handler mHandler2 = new Handler() {
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 2:
				updateEditText2();
				break;
			}
		};
	};

	public void updateEditText1() {
		edittext1.append("thread1\n");
	}
	
	public void updateEditText2() {
		edittext2.append("thread2\n");
	}

	public class MyThread1 extends Thread {
		public MyThread1() {
		}

		@Override
		public void start() {
			super.start();
		}

		@Override
		public void run() {
			while (bThreadRun) {
				try {
					sleep(500);
					Message message = new Message();
					message.what = 1;
					mHandler1.sendMessage(message);
				} catch (InterruptedException ex) {
				}
			}
		}
	}
	
	public class MyThread2 extends Thread {
		public MyThread2() {
		}

		@Override
		public void start() {
			super.start();
		}

		@Override
		public void run() {
			while (bThreadRun) {
				try {
					sleep(500);
					Message message = new Message();
					message.what = 2;
					mHandler2.sendMessage(message);
				} catch (InterruptedException ex) {
				}
			}
		}
	}
}
