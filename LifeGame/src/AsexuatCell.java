
public class AsexuatCell extends Cell {

	public AsexuatCell(long timeFull, long timeStarve) {
		super(timeFull, timeStarve);
	}

	@Override
	public void run() {
		while (true) {
			long timeStart;

			if (eat() == false)
				return;

			timeStart = System.currentTimeMillis();

			while (System.currentTimeMillis() - timeStart < timeFull) {
				if (timesEat >= 10) {
					reproduce();

				}

			}

		}
	}

	@Override
	public void reproduce() {
		Cell bornCell = new AsexuatCell(timeFull, timeStarve);
		Thread t = new Thread(bornCell);
		t.start();
		timesEat = 0;
		System.out.println("TIME: " + System.currentTimeMillis() + " Celula " + this.hashCode() + "s-a divizat in: "
				+ bornCell.hashCode());

		// System.out.println("RESURSE!!!" + Main.foodResources);
	}
}
