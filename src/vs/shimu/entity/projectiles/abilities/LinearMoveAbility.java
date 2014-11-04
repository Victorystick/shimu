package vs.shimu.entity.projectiles.abilities;

import vs.shimu.entity.projectiles.Projectile;

public class LinearMoveAbility extends AbstractMoveAbility {

	public LinearMoveAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void move() {
		int it = owner.getSpeed() / 2;
		float d = 0;
		if (it != 0) {
			d = owner.getSpeed() / it;
		}
		for (int i = 0; i < it; i++) {
			owner.project(d);
			owner.collide();
		}
	}

}
