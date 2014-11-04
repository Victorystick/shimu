package vs.shimu.entity.projectiles.abilities;

import vs.shimu.drawable.Drawable;
import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.Projectile;

public interface ProjectileAbility extends Drawable, Cloneable {
	
	public void move();
	public void collision(EntityPlus e);
	public void changeContext(Projectile c);
	public ProjectileAbility duplicate();
}
