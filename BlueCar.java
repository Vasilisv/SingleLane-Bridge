
public class BlueCar implements Runnable {

	Bridge control;
	public int id;

//o kataskeuastis dexetai ena antikeimeno bridge kai to id enos car

	public BlueCar(Bridge b, int id) {
		this.id = id;
		control = b;
	}

	public void run() {
		try {
			int currentid = id + 1; // aukshsh tou id gia ta cars pou ftanoun
			Main.time++;
			System.out.println("                                                                    Blue Car "
					+ currentid + " Arrived at Time" + Main.time);

			control.blueEnter(this.id); // to mple tha mpei sthn gefyra
			control.blueExit(this.id); // to mple tha vgei ap thn gefyra

		} catch (InterruptedException e) {
		}
	}

}