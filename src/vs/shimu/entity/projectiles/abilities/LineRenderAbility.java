package vs.shimu.entity.projectiles.abilities;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.logic.GameLogic;

public class LineRenderAbility extends AbstractRenderAbility {
	
	int angle;

	public LineRenderAbility(Projectile owner) {
		super(owner);
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(owner.getColor());
		double angle = owner.getAngle();
		float x = owner.getX();
		float y = owner.getY();
		g.drawLine(Math.round(x), Math.round(y), Math.round((float)(x - (3 * Math.sin(angle)))), Math.round((float) (y - (3 * Math.cos(angle)))));
		
		g.setColor(Color.green);
		g.drawRect(Math.round((float)(x + (owner.distanceTo(GameLogic.boss) * Math.sin(owner.angleFacing(GameLogic.boss))))),
				Math.round((float)(y + (owner.distanceTo(GameLogic.boss) * Math.cos(owner.angleFacing(GameLogic.boss))))), 1, 1);
	}

}
