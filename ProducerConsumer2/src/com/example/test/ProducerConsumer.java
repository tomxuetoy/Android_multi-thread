package com.example.test;

public class ProducerConsumer {
	public static void main(String[] args) {
		SyncStack ss = new SyncStack();
		Producer p = new Producer(ss);
		Consumer c = new Consumer(ss);
		new Thread(p).start();
		new Thread(c).start();
	}
}

/*
 * 窝头类
 */
class WoTou {
	int Id;

	WoTou(int Id) {
		this.Id = Id;
	}

	// override
	public String toString() {
		return "WoTou " + Id;
	}
}

/*
 * 放窝头的筐，最多放6个窝头
 */
class SyncStack {
	int index = 0;
	WoTou[] arrWT = new WoTou[6];

	// 把窝头放在筐里
	public synchronized void Push(WoTou wt) {
		while (index == arrWT.length) {
			System.out.println("basket is full\n" );
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		arrWT[index] = wt;  // 追逐的游戏
		index++;
	}

	// 从筐里拿窝头
	public synchronized WoTou Pop() {
		while (index == 0) {
			System.out.println("basket is empty\n" );
			try {
				this.wait();
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		this.notify();
		index--;
		return arrWT[index];  // 追逐的游戏
	}
}

/*
 * 生产者: push wrapper
 */
class Producer implements Runnable {
	SyncStack ss = null;

	Producer(SyncStack ss) {
		this.ss = ss;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			WoTou wt = new WoTou(i);
			ss.Push(wt);
			// will call wt.toString
			System.out.println("生产了：" + wt);
			try {
				Thread.sleep((int) (Math.random() * 200));	// change 200 to 1000, frequent empty
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}

/*
 * 消费者: pop wapper
 */
class Consumer implements Runnable {
	SyncStack ss = null;

	Consumer(SyncStack ss) {
		this.ss = ss;
	}

	@Override
	public void run() {
		for (int i = 0; i < 20; i++) {
			WoTou wt = ss.Pop();
			// will call wt.toString
			System.out.println("消费了：" + wt);
			try {
				Thread.sleep((int) (Math.random() * 1000));	// change 1000 to 200, frequent empty
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}
}