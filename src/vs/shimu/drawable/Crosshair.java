package vs.shimu.drawable;

import java.awt.Color;
import java.awt.Graphics2D;

import vs.shimu.entity.Entity;

/**
 * A crosshair, loved by players and feared by hostiles.
 * @author victorystick
 */
public class Crosshair implements Drawable {
	private float x, y;
	private int s;
	private Entity player;
	private Color color;

	/**
	 * The player whos crosshair it is.
	 * @param player
	 */
	public Crosshair(Entity player) {
		s = 3;
		this.player = player;
		color = player.getColor();
	}

	public void update() {
		x = (float) (player.getX() + 30 * Math.sin(player.getAngle()));
		y = (float) (player.getY() + 30 * Math.cos(player.getAngle()));
	}

	@Override
	public Color getColor() {
		return color;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.drawRect(Math.round(x-1.5f), Math.round(y-1.5f), s, s);
	}
}
