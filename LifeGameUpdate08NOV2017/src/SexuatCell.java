
public class SexuatCell extends Cell{

	public SexuatCell(long timeToStarve, long timeToDie, long __initTimeToStarve) {
		super(timeToStarve, timeToDie, __initTimeToStarve);
		System.out.println("Sexuat Cell " + this.hashCode() + " created");
	}

	@Override
	protected void mate() {
		for(Cell _everyCell : Main_LifeGame.getCells())
		{
			if(this.equals(_everyCell))
				continue; //avoid this cell
			if(_everyCell.sayYes()){
				this.setTimeFedToZero();
				((SexuatCell) _everyCell).setTimeFedToZero();
				Main_LifeGame.startNewCell(new SexuatCell(0, (long)(1 + Math.random() * 5), (long)(1 + Math.random() * 5)));
				System.out.println("Sexuat Cell " + this.hashCode() + " combined with cell " + _everyCell.hashCode() + " and started a new cell");
				break;
			}
		}
		
	}

	@Override
	protected boolean sayYes() {
		return (this.timeFed >= 10);
	}
}