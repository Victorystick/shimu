package vs.shimu.entity.Buffs;

public class SpeedDebuff extends TimedBuff {

	private int slow;
	
	public SpeedDebuff(int amount, int time) {
		super(time);
		slow = amount;
	}

	public int getSpeedBuff(int currentSpeed) {
		return currentSpeed-slow;
	}
	
	public boolean equals(Object o) {
		return (o instanceof SpeedDebuff && ((SpeedDebuff) o).getAmount() == this.getAmount());
	}

	private int getAmount() {
		return slow;
	}

	@Override
	public void cancel() {
		slow = 0;
		super.cancel();
	}

}
