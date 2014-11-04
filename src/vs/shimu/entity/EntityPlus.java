package vs.shimu.entity;

import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import vs.shimu.entity.Buffs.Buff;

public class EntityPlus extends Entity {
	protected boolean alive;
	protected int damage;
	protected EntityPlus killer;
	protected Set<Buff> buffs;

	public EntityPlus(int width, int height, int x, int y, int speed,
			float angle) {
		super(width, height, x
				, y, speed, angle);
		alive = true;
		buffs = new HashSet<Buff>();
	}

	public boolean isAlive() {
		return alive;
	}
	
	public int getSpeed() {
		int temp = super.getSpeed();
		for (Buff b : buffs) {
			temp = b.getSpeedBuff(temp);
		}
		return (temp < 0) ? 0 : temp;
		
	}

	public void setAlive(boolean lives) {
		alive = lives;
	}

	public int getDamage() {
		int temp = damage;
		for (Buff b : buffs) {
			temp = b.getDamageBuff(temp);
		}
		return temp;
	}

	public void addPoints(int p) {
	}

	public EntityPlus getKiller() {
		return killer;
	}

	public int getPoints() {
		int temp = 0;
		for (Buff b : buffs) {
			temp = b.getPointsBuff(temp);
		}
		return temp;
	}
	
	public void dealDamage(int damage, EntityPlus dealer) {
		setAlive(false);
		killer = dealer;
	}
	
	public void render(Graphics2D g) {
		super.render(g);
		for (Buff b : buffs) {
			b.render(g);
		}
	}
	
	public void addBuff(Buff buff) {
		if (buff != null) {
			buffs.add(buff);
		}
	}
	
	public void removeBuff(Buff buff) {
		buffs.remove(buff);
	}
	
	public void clearBuffs() {
		buffs.clear();
	}
	
	public void removeDoneBuffs() {
		List<Buff> todo = new ArrayList<Buff>(buffs.size());
		for (Buff b : buffs) {
			if (b.done()) {
				todo.add(b);
			}
		}
		for (Buff b : todo) {
			removeBuff(b);
		}
	}
}
