package vs.shimu.entity;

import java.awt.Color;
import java.awt.Graphics2D;
import java.util.Collection;

import vs.shimu.drawable.Drawable;

public class Entity implements Drawable {
	protected int w, h;
	protected float x, y;
	protected int speed;
	protected double angle;

	protected Color color;

	public Entity(int width, int height, float x, float y, int speed,
			double angle) {
		w = width;
		h = height;
		this.x = x;
		this.y = y;
		this.speed = speed;
		this.angle = angle;
		color = Color.white;
	}

	public Entity(float x, float y) {
		w = 1;
		h = 1;
		this.x = x;
		this.y = y;
		this.speed = 0;
		this.angle = 0;
		color = Color.white;
	}

	/**
	 * Creates an entity with the <code>width</code> and <code>height</code>
	 * supplied, setting x-position, y-position, speed and angle to zero.
	 * 
	 * @param width
	 * @param height
	 */
	public Entity(int width, int height) {
		this(width, height, 0, 0, 0, 0);
	}

	/**
	 * Moves the entity
	 * 
	 * @param dx
	 *            - x-distance
	 * @param dy
	 *            - y-distance
	 */
	@Deprecated
	public void translate(int dx, int dy) {
		x += dx;
		y += dy;
	}

	public void setPosition(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Moves the Entity along it's current path, the distance equal to it's
	 * speed and the direction according to it's angle.
	 */
	public void project() {
		// project(speed, angle);
		x += getSpeed() * Math.sin(angle);
		y += getSpeed() * Math.cos(angle);
	}

	/**
	 * Projects the entity along it's movement vector by the distance supplied.
	 * 
	 * @param d
	 *            - distance to project the entity
	 */
	public void project(double d) {
		project(d, angle);
	}

	public void project(double r, double a) {
		x += r * Math.sin(a);
		y += r * Math.cos(a);
	}

	public boolean intersects(Entity entity) {
		if ((getRX() < (entity.getRX() + entity.w) && entity.getRX() < (getRX() + w))
				&& (getRY() < (entity.getRY() + entity.h) && entity.getRY() < (getRY() + h))) {
			return true;
		}
		return false;
	}

	public double angleFacing(Entity entity) {
		if (entity != null) {
			return Math.atan2(entity.getX() - x, entity.getY() - y);
		}
		return 0;
	}

	public double distanceTo(Entity entity) {
		return Math.hypot(x - entity.getX(), y - entity.getY());
	}

	public void setAngle(double angle) {
		this.angle = angle;
	}

	public double getAngle() {
		return angle;
	}

	/**
	 * @return The width of the entity.
	 */
	public int getWidth() {
		return w;
	}

	/**
	 * @return The width of the entity.
	 */
	public int getHeight() {
		return h;
	}

	/**
	 * @return The logic-x of the entity.
	 */
	public float getX() {
		return x;
	}

	/**
	 * @return The logic-y of the entity.
	 */
	public float getY() {
		return y;
	}

	public void setX(float newX) {
		x = newX;
	}

	public void setY(float newY) {
		y = newY;
	}

	/**
	 * @return The render-x of the entity. Is equal to x - (w / 2);
	 */
	private int getRX() {
		return Math.round(x - (w / 2f));
	}

	/**
	 * @return The render-y of the entity. Is equal to y - (h / 2);
	 */
	private int getRY() {
		return Math.round(y - (h / 2f));
	}

	/**
	 * @return The Color of the entity.
	 */
	public Color getColor() {
		return color;
	}

	/**
	 * @return The speed of the entity.
	 */
	public int getSpeed() {
		return speed;
	}

	@Override
	public void render(Graphics2D g) {
		g.setColor(color);
		g.fillRect(getRX(), getRY(), w, h);
	}
	
	public Entity closest(Collection<? extends Entity> es) {
		Entity closestEntity = null;
		double distToClosest = Double.MAX_VALUE;
		for (Entity e : es) {
			if (closestEntity == null || distToClosest > distanceTo(e)) {
				closestEntity = e;
				distToClosest = distanceTo(e);
			}
		}
		return closestEntity;
	}

}
