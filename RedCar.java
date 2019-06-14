
public class RedCar implements Runnable {
	Bridge control;
	public int id;

	// o kataskeuastis dexetai ena antikeimeno bridge kai to id enos car

	public RedCar(Bridge b, int id) {
		this.id = id;
		control = b;
	}

	public int getId() {
		return id;
	}

	public void run() {
		try {
			int currentid = id + 1; // aukshsh tou id gia ta cars pou ftanoun
			Main.time++;
			System.out.println("Red Car " + currentid + " Arrived at Time" + Main.time);

			control.redEnter(this.id); // to red tha mpei sthn gefyra
			control.redExit(this.id); // to red tha vgei ap thn gefyra

		} catch (InterruptedException e) {
		}
	}

}
