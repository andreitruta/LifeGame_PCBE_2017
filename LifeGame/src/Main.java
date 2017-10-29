
public class Main {

	static int foodResources = 12;
	

	public static void main(String argv[]) {
		long timeFull = 1000;
		long timeStarve = 5000;

		Thread t1 = new Thread(new AsexuatCell(timeFull, timeStarve));
		t1.start();
		/*Thread t2 = new Thread(new AsexuatCell(timeFull, timeStarve));
		t2.start();
*/
	}

	public static boolean eatFood(long timeStarve) {
		//long timeStart = System.currentTimeMillis();
		if (foodResources > 0)
			foodResources--;
		else
			return false;
		/*if (System.currentTimeMillis() - timeStart > timeStarve) {
			foodResources++;
			return false;
		}*/
		return true;

	}

	public synchronized  static void increaseResources() {
		foodResources += (int) (Math.random() * 5 + 1);
	}

}
