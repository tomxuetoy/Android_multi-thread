import java.util.*;

public class TestInterrupt {
	public static void main(String[] args) {
		MyThread t = new MyThread();
		t.start();
		try {
			Thread.sleep(1000);
		} catch (InterruptedException i) {
		}
		t.interrupt();
	}
}

class MyThread extends Thread {
	public void run() {
		while (true) {
			try {
				System.out.println("------" + new Date() + "-----");
				Thread.sleep(100);
			} catch (InterruptedException i) {
				return;
			}
		}
	}
}