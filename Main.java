import java.sql.Time;
import java.util.Scanner;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

public class Main {
	public static int CarsCanPass;
	public static int Delay;
	public static volatile int time = 0;

	public static void main(String[] args) throws InterruptedException {
		Thread red[];
		Thread blue[];
		Bridge b;
		int MaxReds;
		int MaxBlues;
		int maxcars;
		String Fair;
		String Safe;
		int fArrival;

		System.out.println("Synolikos arithmos RED autokinhtwn pou ftanoun se kathe pleura: ");
		Scanner scanner1 = new Scanner(System.in);
		MaxReds = scanner1.nextInt();

		System.out.println("Synolikos arithmos BLUE autokinhtwn pou ftanoun se kathe pleura: ");
		Scanner scanner2 = new Scanner(System.in);
		MaxBlues = scanner2.nextInt();

		System.out.println("Syxnothta afikshs autokinhtwn : (MS)");
		Scanner scanner3 = new Scanner(System.in);
		fArrival = scanner3.nextInt();

		System.out.println("Xronos pou apaiteitai gia thn dieleush autokinhtou: ");
		Scanner scanner5 = new Scanner(System.in);
		Delay = scanner5.nextInt();

		System.out.println("Posa autokinhta ths idias pleuras mporoun na perasoun kathe fora: ");
		Scanner scanner6 = new Scanner(System.in);
		CarsCanPass = scanner6.nextInt();

		System.out.println("Safe? (YES/NO) ");
		Scanner scanner7 = new Scanner(System.in);
		Safe = scanner7.nextLine();

		System.out.println("Fair? (YES/NO) ");
		Scanner scanner8 = new Scanner(System.in);
		Fair = scanner8.nextLine();

		red = new Thread[MaxReds];
		blue = new Thread[MaxBlues];
		System.out.println("Left Side                             Bridge                             Right Side");

//Anloga me ta dedomena pou tha dwsei o xrhsths sto plhktrologio epilegei to senario, 
//dhladh dimiourgei to antistoixo antikeimeno bridge

		if (Fair.equals("YES") && Safe.equals("YES")) {
			if (MaxBlues != MaxReds) {
				b = new FairBridge2();
			} else {
				b = new FairBridge();

			}
		} else if (Safe.equals("YES")) {
			b = new SafeBridge();
		} else {
			b = new Bridge();
		}
//vriskoume to megalitero synoliko arithmo autokinhtw an den einai idios kai gia ta 2 cars

		if (MaxReds >= MaxBlues)
			maxcars = MaxReds;
		else
			maxcars = MaxBlues;

//Dhmiourgoume ta nhmata kai ta ksekiname

		for (int i = 0; i < MaxReds; i++) {
			red[i] = new Thread(new RedCar(b, i));
		}
		for (int i = 0; i < MaxBlues; i++) {
			blue[i] = new Thread(new BlueCar(b, i));
		}

		for (int i = 0; i < maxcars; i++) {
			if (i < MaxReds) {
				red[i].start();
			}

			if (i < MaxBlues) {
				blue[i].start();
			}

			Thread.sleep(fArrival); // syxnothta afikshs autokinhtwn pou exei dwthei ap to xrhsth
		}

//termatismos nhmatwn
		for (int i = 0; i < MaxReds; i++) {
			red[i].join();
		}

		for (int i = 0; i < MaxBlues; i++) {
			blue[i].join();
		}

	}
}
