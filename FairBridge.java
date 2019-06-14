
class FairBridge extends Bridge {

	private int nred = 0; // ta kokkina pou pernane tin gefira
	private int nblue = 0; // ta mple pernane tin gefira
	private int waitblue = 0; // ta mple pou perimenoun na perasoun tin gefira
	private int waitred = 0; // ta kokkina pou perimenoun na perasoun tin gefira
	private boolean blueturn = true;

	// To kokkino(Thread) perimenei an ena mple diasxizei idi thn gefyra alliws an
	// einai adeia h gefyra pernaei auto
	synchronized void redEnter(int id) throws InterruptedException {
		Main.time++;
		id = id + 1;
		++waitred;
		while (nblue > 0 || nred == Main.CarsCanPass || (waitblue > 0 && blueturn))
			wait();
		--waitred;
		++nred;
		System.out.println("                               Red Car " + id + " Passing at Time " + Main.time);
		Thread.sleep(Main.Delay);
	}

	// Molis to kokkino diasxisei thn gefyra xipnaei ta alla nhmata gia na
	// synexistei h diadikasia ths prospelashs ths gefyras

	synchronized void redExit(int id) {
		Main.time++;
		id = id + 1;
		--nred;
		blueturn = true;
		if (nred == 0)
			notifyAll();
		System.out.println("                                                                    Red Car " + id
				+ " Passed at Time " + Main.time);
	}

	// To mple(Thread) perimenei an ena kokkino diasxizei idi thn gefyra alliws an
	// einai adeia h gefyra pernaei auto
	synchronized void blueEnter(int id) throws InterruptedException {
		Main.time++;
		id = id + 1;
		++waitblue;
		while (nred > 0 || nblue == Main.CarsCanPass || (waitred > 0 && !blueturn))
			wait();
		--waitblue;
		++nblue;
		System.out.println("                               Blue Car " + id + " Passing at Time" + Main.time);
		Thread.sleep(Main.Delay);
	}

	// Molis to mple diasxisei thn gefyra xipnaei ta alla nhmata gia na synexistei h
	// diadikasia ths prospelashs ths gefyras
	synchronized void blueExit(int id) {
		Main.time++;
		id = id + 1;
		--nblue;
		blueturn = false;
		if (nblue == 0)
			notifyAll();
		System.out.println("Blue Car " + id + " Passed at Time" + Main.time);
	}
}