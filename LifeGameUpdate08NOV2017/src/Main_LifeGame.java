import java.util.LinkedList;
import java.util.concurrent.Semaphore;

public class Main_LifeGame {

	static int foodResources = 555;
	static Semaphore mutex;
	private static LinkedList<Cell> allCells;

	public static void main(String argv[]) {
		//long timeFull = 1000;
		//long timeStarve = 5000;
		mutex = new Semaphore(1);
		allCells = new LinkedList<Cell>();
		Cell c1 = new AsexuatCell((long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1));
		allCells.add(c1);
		c1.start();
		Cell c2 = new AsexuatCell((long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1));
		allCells.add(c2);
		c2.start();
		Cell c3 = new SexuatCell((long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1));
		allCells.add(c3);
		c3.start();
		Cell c4 = new SexuatCell((long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1), (long)(Math.random() * 5 + 1));
		allCells.add(c4);
		c4.start();
		while(!allCells.isEmpty()){
			System.out.println("Cells alive still: " + allCells.size());
			if((Math.random() * 2 ) > 1){
				System.out.println("Cells are fed!!");
				Main_LifeGame.increaseResources();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println("All is dead!! :( ");
		/*Thread t2 = new Thread(new AsexuatCell(timeFull, timeStarve));
		t2.start();
*/
	}

	public static boolean eatFood(Cell cellWhichEats)
	{
		//long timeStart = System.currentTimeMillis();
		if (foodResources > 0){
			foodResources--;
			System.out.println("Cell " + cellWhichEats.hashCode() + " eats. --> New food supplies = " + Main_LifeGame.foodResources);
		}
		else
			return false;
		/*if (System.currentTimeMillis() - timeStart > timeStarve) {
			foodResources++;
			return false;
		}*/
		return true;

	}

	public synchronized  static void increaseResources()
	{
		foodResources += (int)(1 + Math.random() * 4);
	}

	public static void startNewCell(final Cell argCell){
		allCells.add(argCell);
		argCell.start();
	}
	
	public static void removeFromList(final Cell cellToRemove)
	{
		allCells.remove(cellToRemove);
	}
	
	public static LinkedList<Cell> getCells()
	{
		return Main_LifeGame.allCells;
	}
}
