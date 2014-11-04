package vs.shimu;

import java.util.ArrayList;

import vs.shimu.entity.projectiles.ProjectileType;

public class Armory {
	
	ArrayList<ProjectileType> weapons;
	int position;
	
	public Armory() {
		position = 0;
		weapons = new ArrayList<ProjectileType>();
	}
	
	public Armory(ProjectileType[] initWeapons) {
		position = 0;
		weapons = new ArrayList<ProjectileType>();
		for (ProjectileType pt : initWeapons) {
			weapons.add(pt);
		}
	}

	public ProjectileType getWeapon(int number) {
		number = number % weapons.size();
		position = number;
		return weapons.get(number);
	}
	
	public void addWeapon(ProjectileType type) {
		weapons.add(type);
	}
	
	public ProjectileType next() {
		position = (position+1) % weapons.size();
		return weapons.get(position);
	}
	
	public ProjectileType last() {
		
		position = (position > 0) ? position-1 : weapons.size()-1;
		return weapons.get(position);
	}
	
	public ProjectileType peekNext() {
		return weapons.get((position+1) % weapons.size());
	}
	
	public ProjectileType peekLast() {
		return weapons.get((position > 0) ? position-1 : weapons.size()-1);
	}
}
