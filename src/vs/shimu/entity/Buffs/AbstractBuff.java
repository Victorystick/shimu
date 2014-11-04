package vs.shimu.entity.Buffs;

import java.awt.Graphics2D;


public abstract class AbstractBuff implements Buff {
	
	@Override
	public void continousBuff() {
		return;
	}

	@Override
	public int getDamageBuff(int currentDamage) {
		return currentDamage;
	}

	@Override
	public int getPointsBuff(int currentPoints) {
		return currentPoints;
	}

	@Override
	public int getSpeedBuff(int currentSpeed) {
		return currentSpeed;
	}

	@Override
	public void render(Graphics2D g) {
	}
	
	@Override
	public boolean done() {
		return false;
	}
}
