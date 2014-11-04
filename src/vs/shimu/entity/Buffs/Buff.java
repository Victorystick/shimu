package vs.shimu.entity.Buffs;

import java.awt.Graphics2D;

public interface Buff {
	
	public int getSpeedBuff(int currentSpeed);
	
	public int getDamageBuff(int currentDamage);
	
	public void continousBuff();
	
	public int getPointsBuff(int currentPoints);
	
	public void render(Graphics2D g);
	
	public boolean done();

}
