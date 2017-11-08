
abstract class Cell extends Thread {

	
	protected long timeToStarve;
	protected long __initTimeToStarve;
	protected long timeToDie;
	
	protected long timeFed = 0;
	
	protected boolean isRunning = true;
	
	public Cell(final long timeToStarve, final long timeToDie, final long __initTimeToStarve)
	{
		this.timeToStarve = timeToStarve;
		this.__initTimeToStarve = __initTimeToStarve;
		this.timeToDie = timeToDie;
	}
	
	@Override
	public void run() 
	{
		while(isRunning){
			
			if(this.timeToStarve == 0){
				try
				{
					Main_LifeGame.mutex.acquire();
					if(Main_LifeGame.eatFood(this)){
						this.timeToStarve = this.__initTimeToStarve;
						this.timeFed++;
						if(this.timeFed >= 10)
						{
							this.mate();
						}
					}
					else{
					
						this.timeToDie--;
						if(this.timeToDie == 0){
							this.die();
						}

					}
					Main_LifeGame.mutex.release();
				} 
				catch (Exception e)
				{
					this.timeToDie--;
					if(this.timeToDie == 0){
						this.die();
					}
				}
		
			}
			else
			{
				this.timeToStarve--;
			}
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				/* Do nothing */
			}
		}
	}
	
	protected void die()
	{
		System.out.println("Cell " + this.hashCode() + " died");
		this.isRunning = false;
		Main_LifeGame.increaseResources();
		Main_LifeGame.removeFromList(this);
		this.interrupt();
	}
	
	public void setTimeFedToZero()
	{
		this.timeFed = 0;
	}

	
	protected abstract void mate();
	protected abstract boolean sayYes();
	
}