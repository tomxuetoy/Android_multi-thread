package com.example.testthread;

import com.example.threadtrial.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.EditText;

public class MainActivity extends Activity {
	private EditText edittext1, edittext2;
	volatile boolean bThreadRun = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		edittext1 = (EditText) findViewById(R.id.EditText1);
		edittext2 = (EditText) findViewById(R.id.EditText2);

		edittext1.setText("___thread1___\n");
		edittext2.setText("___thread2___\n");

		System.out.println("mainUI--->" + Thread.currentThread().getId());
		System.out
				.println("mainUI_name--->" + Thread.currentThread().getName());
		
		MyRunnable mr = new MyRunnable();

		new Thread(mr).start();
		new Thread(mr).start();
		new Thread(mr).start();
		new Thread(mr).start();
		new Thread(mr).start();
		new Thread(mr).start();
	}

	@Override
	protected void onStart() {
		super.onStart();
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

	class MyRunnable implements Runnable {
		Object o = new Object();
		int index = 0;

		public void run() {
			while (true) {
				synchronized (o) {
					System.out.println("Thread ID = "
							+ Thread.currentThread().getName() + " Index = "
							+ index++);
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
	}
}
