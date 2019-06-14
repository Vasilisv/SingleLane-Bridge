import java.util.concurrent.locks.ReentrantLock;

class Bridge {
	private final ReentrantLock lock = new ReentrantLock();

	// Ena kokkino(Thread) mpainei stin gefira
	void redEnter(int id) throws InterruptedException {
		Thread.sleep(Main.Delay); // xronos gia na diasxisei thn gefyra
		id = id + 1; // to id pou xrhsimopoiei thn redenter auksanetai
		Main.time++;
		// An einai locked shmainei oti yparxei car sthn gefyra opote tha mpei deutero
		// kai tha ginei sygkroush!
		if (lock.isLocked()) {
			System.out.println("                               Red Car " + id + " Passing at Time " + Main.time);
			System.out.println("--------------------CARS CRASHED! (BOTH ON BRIDGE)---------------------");
			Thread.currentThread().interrupt();
			System.exit(0);
		}
		lock.lock();
		System.out.println("                               Red Car " + id + " Passing at Time " + Main.time);
	}

	// Ena kokkino mpainei apo tin gefira
	void redExit(int id) throws InterruptedException {
		id = id + 1;

		Main.time++;
		System.out.println("                                                                    Red Car " + id
				+ " Passed at Time " + Main.time);
	}

	// Ena mple(Thread) mpainei stin gefira
	void blueEnter(int id) throws InterruptedException {
		Thread.sleep(Main.Delay); // xronos gia na diasxisei thn gefyra
		id = id + 1;
		Main.time++;
		if (lock.isLocked()) {
			System.out.println("                               Blue Car " + id + " Passing at Time" + Main.time);
			System.out.println("--------------------CARS CRASHED! (BOTH ON BRIDGE)---------------------");
			Thread.currentThread().interrupt();
			System.exit(0);
		}
		System.out.println("                               Blue Car " + id + " Passing at Time" + Main.time);
		lock.lock();
	}

	// Ena mple vgainei apo tin gefira
	void blueExit(int id) throws InterruptedException {
		id = id + 1;
		Main.time++;
		System.out.println("Blue Car " + id + " Passed at Time" + Main.time);
	}

}