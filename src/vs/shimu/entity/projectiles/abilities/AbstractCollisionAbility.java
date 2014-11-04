package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.projectiles.Projectile;

public abstract class AbstractCollisionAbility extends AbstractAbility {

	public AbstractCollisionAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void move() {
		return;
	}

	@Override
	public Color getColor() {
		return Color.GREEN;
	}

	@Override
	public void render(Graphics2D g) {
		return;
	}

}
