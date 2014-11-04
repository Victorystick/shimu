package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.Buffs.Poison;
import vs.shimu.entity.Buffs.SeverePoison;
import vs.shimu.entity.hostiles.Hostile;
import vs.shimu.entity.projectiles.Projectile;

public class PoisonAbility extends AbstractCollisionAbility {

	public PoisonAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void collision(EntityPlus e) {
		if (e instanceof Hostile) {
			e.addBuff(new SeverePoison((Hostile) e, 1, 1, owner.getOwner()));
		}
	}
}
