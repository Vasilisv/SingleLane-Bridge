//import java.util.concurrent.locks.ReentrantLock;

class SafeBridge extends Bridge {

	private int nred  = 0; // ta kokkina pou pernane tin gefyra
	private int nblue = 0; // ta mple pou pernane tin gefyra  
	//public static ReentrantLock lock;
	
	// To kokkino(Thread) perimenei an ena mple diasxizei idi thn gefyra alliws an einai adeia h gefyra pernaei auto 
	synchronized void redEnter(int id) throws InterruptedException {
		while (nblue>0 || nred == Main.CarsCanPass ) wait();
		++nred;
		Main.time++;
		id=id+1;
		System.out.println("                               Red Car " + id +  " Passing at Time "+ Main.time);
		Thread.sleep(Main.Delay);
	}
    
	//Molis to kokkino diasxisei thn gefyra xipnaei ta alla nhmata gia na synexistei h diadikasia ths prospelashs ths gefyras 
	synchronized  void redExit(int id){
		--nred;
		if (nred==0)
			notifyAll();
		Main.time++;
		id=id+1;
		System.out.println("                                                                    Red Car " + id +  " Passed at Time "+Main.time);
		
		
	}
    
	
	// To mple(Thread) perimenei an ena kokkino diasxizei idi thn gefyra alliws an einai adeia h gefyra pernaei auto
	synchronized  void blueEnter(int id) throws InterruptedException {
		while (nred>0 || nblue == Main.CarsCanPass ) wait();
		++nblue;
		Main.time++;
		id=id+1;
		//lock.lock();
		System.out.println("                               Blue Car " + id +  " Passing at Time"+Main.time);
		Thread.sleep(Main.Delay);
	}

	//Molis to mple diasxisei thn gefyra xipnaei ta alla nhmata gia na synexistei h diadikasia ths prospelashs ths gefyras
	synchronized  void blueExit(int id){
		--nblue;
		if (nblue==0)
			notifyAll();
		Main.time++;
		id=id+1;
		 System.out.println("Blue Car " + id +  " Passed at Time"+ Main.time);
		 //lock.unlock();
	
	}
}