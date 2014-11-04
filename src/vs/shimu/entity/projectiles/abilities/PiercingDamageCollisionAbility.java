package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.Projectile;

public class PiercingDamageCollisionAbility extends AbstractCollisionAbility {

	public PiercingDamageCollisionAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void collision(EntityPlus e) {
		int damage = e.getDamage();
		e.dealDamage(owner.getDamage(), owner.getOwner());
		owner.setDamage(owner.getDamage()-(damage-e.getDamage())); //If damage is same (damage don't depend on health) then the projectile will terminate immediately
		owner.setAlive(owner.getDamage() > 0);
		return;
	}

}
