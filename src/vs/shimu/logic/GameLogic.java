package vs.shimu.logic;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Random;

import vs.shimu.util.Dimension;

import vs.shimu.counter.Counter;
import vs.shimu.counter.PointsCounter;
import vs.shimu.counter.RatioCounter;
import vs.shimu.counter.SimpleCounter;
import vs.shimu.counter.TimeCounter;
import vs.shimu.drawable.Crosshair;
import vs.shimu.entity.*;
import vs.shimu.entity.hostiles.*;
import vs.shimu.entity.projectiles.Projectile;

public class GameLogic implements LogicInterface {

	/**
	 * Low numbers, 2-3 gives mostly red.
	 * 4-5 gives about 50-50 blue and red.
	 * >5 will start give yellow.
	 * Higer will increase number of yellows.
	 * 
	 * MUST BE 2 OR HIGHER!
	 */
	private static final int STARTDIFFICULTY = 50;

	public static final int NUMBULLETS = 6;

	private Dimension display;
	private boolean[] keys;
	private int[] mouse;

	private float eSpeed = 0;

	private Player player;
	private Crosshair crosshair;
	private Map<Entity, List<Projectile>> bullets;
	private List<Hostile> enemies;
	private List<Counter> counters;
	public static Boss boss;
	private int bossLvl;

	private Counter bossLvlCounter;

	private static Map<Entity, List<Entity>> toAdd;
	private List<Entity> toRemove;

	public GameLogic(Dimension display, boolean[] keys, int[] mouse) {
		this.display = display;
		this.keys = keys;
		this.mouse = mouse;
		bossLvl = 0;
		player = new Player(Color.red);
		crosshair = player.getCrosshair();
		bullets = new HashMap<Entity, List<Projectile>>();
		enemies = new ArrayList<Hostile>();
		toAdd = new HashMap<Entity, List<Entity>>();
		toRemove = new ArrayList<Entity>();
		createEnemies(50, eSpeed);

		counters = new ArrayList<Counter>();

		Counter time = new TimeCounter(0, 0, 15);
		PointsCounter points = new PointsCounter(player, 0, 35);
		RatioCounter rc = new RatioCounter(0, 55, points, time);
		bossLvlCounter = new SimpleCounter(0, 65, "Level", 0);

		counters.add(time);
		counters.add(points);
		counters.add(rc);
		counters.add(bossLvlCounter);
		player.setCounters(points, rc);
		spawnboss();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#update()
	 */
	public void update() {
		updatePlayer();
		updateProjectiles();
		updateEnemies();
		removeDeads();
		addNew();
		if (enemies.size() < 25) { 
			createEnemies(10, eSpeed); eSpeed += 0.5;
		}

		if (player.isAlive()) {
			counters.get(0).addTo(1);
		}
		if (!boss.isAlive()) {
			spawnboss();
		}
		// System.out.println(boss.getHealth());
	}

	private void createEnemies(int amount, float speed) {
		Random r = new Random();
		int height = 0;
		int width = 0;

		int hSpeed = 0;
		for (int i = 0; i < amount; i++) {
			height = r.nextInt(7) + 6;
			width = r.nextInt(7) + 6;

			int x, y;
			do {
				x = r.nextInt(display.getWidth());
				y = r.nextInt(display.getHeight()); // TODO Redo enemy spawn
			} while (Math.sqrt((player.getX() - x) * (player.getX() - x)
					+ (player.getY() - y) * (player.getY() - y)) < 200);

			hSpeed = (int) Math.abs((height + width) / 2 + speed - 6);
			Hostile enemy;

			HashSet<EntityPlus> players = new HashSet<EntityPlus>();
			players.add(player);

			int type = r.nextInt(STARTDIFFICULTY/2) % (STARTDIFFICULTY-bossLvl > 1 ? STARTDIFFICULTY-bossLvl : 1);
			switch (type) {
			case 0: enemy = new ShootingHostile(height, width, x, y, hSpeed, 1);
					players.addAll(enemies); 
					enemy.setTarget(player); break;
			case 1: enemy = new Turret(x, y, hSpeed, 10); 
					players.addAll(enemies); 
					enemy.setTarget(player); break;
			default: enemy = new Hostile(height, width, x, y, hSpeed, 5); break;
			}

			enemy.setTargets(players);
			enemies.add(enemy);
		}
	}

	private void updatePlayer() {
		if (!player.isAlive())
			return;
		int x = 0;
		int y = 0;
		if (keys[1]) {
			x -= 1;
		}
		if (keys[2]) {
			x += 1;
		}
		if (keys[3]) {
			y -= 1;
		}
		if (keys[4]) {
			y += 1;
		}
		if (!(x == 0 && y == 0))
			player.project(player.getSpeed(), Math.atan2(x, y));
		player.setAngle(Math.atan2(mouse[0] - player.getX(), mouse[1]
				- player.getY()));
		crosshair.update();

		if (mouse[2] != 0 && getProjectiles(player).size() < NUMBULLETS + bossLvl) {// snålt!
			addEntity(player, player.fireWeapon1(getEnemies()));
		}

		if (mouse[3] != 0 && getProjectiles(player).size() < NUMBULLETS + 2 + bossLvl) {
			addEntity(player, player.fireWeapon2(getEnemies()));
		}
		// System.out.println(player.getAngle());
		player.removeDoneBuffs();
	}

	private void updateProjectiles() {
		for (List<Projectile> l : bullets.values()) {
			for (Projectile bullet : l) {
				projectileCheck(bullet);
			}
		}
	}

	private void projectileCheck(Projectile bullet) {
		bullet.move();
		if (bullet.outsideScreen(display) || !bullet.isAlive()) {
			toRemove.add(bullet);
		}
	}

	private void updateEnemies() {
		for (Hostile enemy : enemies) {
			if (!enemy.isAlive()) {
				toRemove.add(enemy);
				continue;
			}
			enemy.update();
			if (enemy instanceof Shooter) {
				List<Projectile> shots = bullets.get(enemy);
				if ( (shots != null && shots.size()< 10) || shots == null ) {
					Projectile enemyProjectile = ((Shooter) enemy).shoot();
					if (enemyProjectile != null) {
						addEntity(enemy, enemyProjectile);
					}
				}
			}
			checkCollisions(enemy);
		}
	}

	private void removeDeads() {
		for (Entity death : toRemove) {
			if (death instanceof Projectile) {
				getProjectiles(((Projectile) death).getOwner()).remove(death);
			} else if (death instanceof Hostile) {
				EntityPlus corpse = (Hostile) death;
				enemies.remove(death);
				corpse.getKiller().addPoints(corpse.getPoints());
			}
		}
		toRemove.clear();
	}

	private void addNew() {
		for (Entity parent : toAdd.keySet()) {
			List<Entity> l = toAdd.get(parent);
			for (Entity alive : l) {
				if (alive instanceof Projectile) {
					getProjectiles(parent).add((Projectile) alive);
				}
			}
			l.clear();
		}

	}

	private void checkCollisions(Entity e1) {
		for (Entity e2 : enemies) {
			if (e1 != e2 && e1.intersects(e2)) {
				double d = e1.distanceTo(e2);
				double a = e1.angleFacing(e2);
				e1.project(8 - d, -a);
			}
		}

		if (!player.isAlive())
			return;
		for (Entity enemy : enemies) {
			if (enemy.intersects(player)) {
				player.setAlive(false);
			}
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getPlayer()
	 */
	public Entity getPlayer() {
		return player;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getPlayers()
	 */
	public List<Player> getPlayers() {
		ArrayList<Player> players = new ArrayList<Player>();
		players.add(player);
		return players;
		// return epCounters.keySet();
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getChrosshair()
	 */
	public Crosshair getChrosshair() {
		return crosshair;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getEnemies()
	 */
	public Collection<Hostile> getEnemies() {
		return enemies;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getProjectiles()
	 */
	public Collection<Projectile> getProjectiles() {
		List<Projectile> all = new LinkedList<Projectile>();
		for (Entity parent : bullets.keySet()) {
			all.addAll(getProjectiles(parent));
		}
		return all;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getProjectiles()
	 */
	public Collection<Projectile> getProjectiles(Entity e) {
		if (bullets.containsKey(e)) {
			return bullets.get(e);
		} else {
			ArrayList<Projectile> l = new ArrayList<Projectile>();
			bullets.put(e, l);
			return l;
		}
	}

	public void addEntity(Entity parent, Entity child) {
		if (toAdd.containsKey(parent)) {
			toAdd.get(parent).add(child);
		} else {
			ArrayList<Entity> l = new ArrayList<Entity>();
			l.add(child);
			toAdd.put(parent, l);
		}
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see vs.shimu.logic.LogicInterface#getCounters()
	 */
	public Collection<Counter> getCounters() {
		return counters;
	}

	@Override
	public void join(Socket socket) {
		// TODO Auto-generated method stub

	}

	@Override
	public void leave(Socket socket) {
		// TODO Auto-generated method stub

	}

	public void spawnboss() {
		bossLvl++;
		bossLvlCounter.addTo(1);

		int x, y;
		do {
			Random r = new Random();
			x = r.nextInt(display.getWidth());
			y = r.nextInt(display.getHeight()); // TODO Redo enemy spawn
		} while (Math.sqrt((player.getX() - x) * (player.getX() - x)
				+ (player.getY() - y) * (player.getY() - y)) < 200);

		// x = 300;
		// y= 200;

		boss = new Boss(x, y, bossLvl);
		boss.addTarget(player);
		enemies.add(boss);
	}

}
