
public class AsexuatCell extends Cell {

	public AsexuatCell(long timeToStarve, long timeToDie, long __initTimeToStarve) {
		super(timeToStarve, timeToDie, __initTimeToStarve);
		System.out.println("Asexuat Cell " + this.hashCode() + " created");
	}

	@Override
	protected void mate() {
		System.out.println("Asexuat Cell " + this.hashCode() + " created a new cell");
		Main_LifeGame.startNewCell(new AsexuatCell(0, (long)(1 + Math.random() * 5), (long)(1 + Math.random() * 5)));
		this.timeToStarve = 0;
		this.setTimeFedToZero();
	}

	@Override
	protected boolean sayYes() {
		return false;
	}
	
	
}
