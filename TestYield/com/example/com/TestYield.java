public class TestYield {
	public static void main(String[] args) {
		MyThread3 t1 = new MyThread3("t1");
		MyThread3 t2 = new MyThread3("t2");
		t1.start();
		t2.start();
	}
}

class MyThread3 extends Thread {
	MyThread3(String s) {
		super(s);
	}

	public void run() {
		for (int i = 1; i <= 30; i++) {
			System.out.println(getName() + ": " + i);
			if (i % 10 == 0) {
				yield();
			}
		}
	}
}

/*
Result:
t1: 1
t2: 1
t1: 2
t2: 2
t1: 3
t2: 3
t1: 4
t2: 4
t2: 5
t2: 6
t1: 5
t2: 7
t1: 6
t2: 8
t1: 7
t2: 9
t2: 10
t1: 8
t1: 9
t1: 10
t2: 11
t1: 11
t2: 12
t2: 13
t2: 14
t2: 15
t2: 16
t2: 17
t2: 18
t2: 19
t1: 12
t2: 20
t1: 13
t1: 14
t2: 21
t1: 15
t1: 16
t1: 17
t1: 18
t1: 19
t1: 20
t2: 22
t2: 23
t2: 24
t2: 25
t2: 26
t2: 27
t1: 21
t2: 28
t1: 22
t1: 23
t2: 29
t1: 24
t2: 30
t1: 25
t1: 26
t1: 27
t1: 28
t1: 29
t1: 30
*/