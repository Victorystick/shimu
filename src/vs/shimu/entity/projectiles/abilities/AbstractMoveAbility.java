package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.EntityPlus;
import vs.shimu.entity.projectiles.Projectile;

public abstract class AbstractMoveAbility extends AbstractAbility {
	
	public AbstractMoveAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void collision(EntityPlus e) {
		return;
	}

	public abstract void move();

	@Override
	public Color getColor() {
		return Color.BLUE;
	}

	@Override
	public void render(Graphics2D g) {
		return;
	}

}
