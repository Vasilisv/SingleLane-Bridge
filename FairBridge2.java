
class FairBridge2 extends Bridge {
	// 4o senario
	private int nred = 0;// ta kokkina pou pernane tin gefira
	private int nblue = 0;// ta mple pou pernane tin gefira
	private int waitblue = 0; // ta mple perimenoun na perasoun tin gefira

	int waitred = 0;// ta kokkina perimenoun na perasoun tin gefira

	private boolean flag = true;

	// To kokkino(Thread) perimenei an ena mple diasxizei idi thn gefyra alliws an
	// einai adeia h gefyra pernaei auto

	synchronized void redEnter(int id) throws InterruptedException {
		++waitred;
		while (nblue > 0 || nred == Main.CarsCanPass || (waitblue > 0 && flag))
			wait();
		--waitred;
		++nred;
		Main.time++;
		id = id + 1;
		System.out.println("                               Red Car " + id + " Passing at Time " + Main.time);
		Thread.sleep(Main.Delay);
	}

	// Molis to kokkino diasxisei thn gefyra xipnaei ta alla nhmata gia na
	// synexistei h diadikasia ths prospelashs ths gefyras

	synchronized void redExit(int id) {
		--nred;
		if (waitred > waitblue)
			flag = false;
		else
			flag = true;
		if (nred == 0)
			notifyAll();
		Main.time++;
		id = id + 1;
		System.out.println("                                                                    Red Car " + id
				+ " Passed at Time " + Main.time);

	}

	// To mple(Thread) perimenei an ena kokkino diasxizei idi thn gefyra alliws an
	// einai adeia h gefyra pernaei auto
	synchronized void blueEnter(int id) throws InterruptedException {
		++waitblue;
		while (nred > 0 || nblue == Main.CarsCanPass || (waitred > 0 && !flag))
			wait();
		--waitblue;
		++nblue;
		Main.time++;
		id = id + 1;
		System.out.println("                               Blue Car " + id + " Passing at Time" + Main.time);
		Thread.sleep(Main.Delay);
	}

	// Molis to mple diasxisei thn gefyra xipnaei ta alla nhmata gia na synexistei h
	// diadikasia ths prospelashs ths gefyras

	synchronized void blueExit(int id) {
		--nblue;
		if (waitblue > waitred)
			flag = true;
		else
			flag = false;
		if (nblue == 0)
			notifyAll();

		id = id + 1;
		System.out.println("Blue Car " + id + " Passed at Time" + Main.time);

	}
}
