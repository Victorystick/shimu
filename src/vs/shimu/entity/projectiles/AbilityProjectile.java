package vs.shimu.entity.projectiles;

import java.awt.Graphics2D;
import java.util.Set;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.abilities.ProjectileAbility;

public class AbilityProjectile extends Projectile {

	protected Set<ProjectileAbility> abilities;

	public AbilityProjectile(EntityPlus owner, int speed) {
		super(owner, speed);
	}
	
	public void move() {
		for(ProjectileAbility a : abilities) {
			a.move();
		}
		return;
	}
	
	public void collide() {
		for (EntityPlus e : hitable) {
			if (e.intersects(this)) { //Check if enemy occupies any point in set
				for(ProjectileAbility a : abilities) {
					a.collision(e);
				}
				return;
			}
		}
	}
	
	public void setAbilities(Set<ProjectileAbility> newAbilities) {
		abilities = newAbilities;
	}
	
	public Set<ProjectileAbility> getAbilities() {
		return abilities;
	}
	
	public void render(Graphics2D g) {
		for(ProjectileAbility a : abilities) {
			a.render(g);
		}
	}

}
