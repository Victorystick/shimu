package vs.shimu.state;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import vs.shimu.Controller;
import vs.shimu.counter.Counter;
import vs.shimu.entity.Entity;
import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.logic.*;
import vs.shimu.util.Dimension;
import vs.shimu.display.Display;

public class GameScreen extends State {
	private GameLogic logic;

	public GameScreen(StateHandler main, Display display, Controller control) {
		super(main, display, control);
		logic = new GameLogic(new Dimension(display.getWidth(), display
				.getHeight()), control.getKeys(), control.getMouse());
	}

	@Override
	public void update() {
		boolean[] keys = control.getKeys();
		if (keys[0]) {
			pop();
			System.out.println("Escape pressed: Popping");
			return;
		}
		if (keys[5]) {
			logic = new GameLogic(new Dimension(display.getWidth(), display
					.getHeight()), control.getKeys(), control.getMouse());
			return;
		}
		logic.update();
		if (!display.isVisible())
			pop();
		render();
	}

	@Override
	public void render() {
		BufferedImage img = new BufferedImage(display.getWidth(), display
				.getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		
		logic.getPlayers().get(0).render(g);
		for (Entity enemy : logic.getEnemies()) {
			enemy.render(g);
		}
		for (Projectile bullet : logic.getProjectiles()) {
			bullet.render(g);
		}
		logic.getPlayers().get(0).getCrosshair().render(g);
		for (Counter c : logic.getCounters()) {
			c.render(g);
		}

		g.dispose();
		display.update(img);
	}

}
