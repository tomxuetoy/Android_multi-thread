public class TestJoin implements Runnable {
	static int b = 100;

	public void run() {
		try {
			System.out.println("m1");
			b--;
			System.out.println("m1" + " Thread="
					+ Thread.currentThread().getName() + " " + this + " "
					+ "b=:" + b);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String args[]) throws Exception {
		TestJoin ss = new TestJoin();
		Thread tt = new Thread(ss);
		Thread tt1 = new Thread(ss);
		tt.start();
		// Tom Xue: 串行执行
//		tt.join(); // 合并线程
		tt1.start();
	}
}
/*
m1
m1 Thread=Thread-0 TestJoin@8dc8569 b=:99
m1
m1 Thread=Thread-1 TestJoin@8dc8569 b=:98
----------------if comment out tt.join()----------------
m1
m1 Thread=Thread-1 TestJoin@7150bd4d b=:99
m1
m1 Thread=Thread-0 TestJoin@7150bd4d b=:98

m1
m1 Thread=Thread-0 TestJoin@7150bd4d b=:99
m1
m1 Thread=Thread-1 TestJoin@7150bd4d b=:98

m1
m1
m1 Thread=Thread-1 TestJoin@7150bd4d b=:99
m1 Thread=Thread-0 TestJoin@7150bd4d b=:98

m1
m1
m1 Thread=Thread-0 TestJoin@6bbc4459 b=:98
m1 Thread=Thread-1 TestJoin@6bbc4459 b=:98

 */