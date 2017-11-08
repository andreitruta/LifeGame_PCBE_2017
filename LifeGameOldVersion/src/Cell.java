
public abstract class Cell implements Runnable {

	protected long timeFull; // timp dupa care se face foame
	protected long timeStarve;// timp permis pentru a sta nemancat
	protected int timesEat;

	public Cell(long timeFull, long timeStarve) {

		System.out.println("TIME: " + System.currentTimeMillis() + " Celula " + this.hashCode() + " s-a NASCUT!");
		this.timeFull = timeFull;
		this.timeStarve = timeStarve;
	}

	public boolean eat() {
		long timeStart = System.currentTimeMillis();
		int hasEat = 0;
		while (System.currentTimeMillis() - timeStart < timeStarve) {
			if (Main.eatFood(timeStarve) == true) {
				hasEat = 1;
				break;
			}
		}

		if (hasEat == 0) {
			kill();
			return false;
		}

		timesEat++;
		System.out.println("TIME: " + System.currentTimeMillis() + " Celula " + this.hashCode() + " a MANCAT!");
		return true;
	}

	public void kill() {
		Main.increaseResources();
		System.out.println("TIME: " + System.currentTimeMillis() + " Celula " + this.hashCode() + " a MURIT!");
		System.out.println("RESURSE: " + Main.foodResources);
		// Thread.currentThread().interrupt();

	}

	public abstract void run();

	public abstract void reproduce();
}
