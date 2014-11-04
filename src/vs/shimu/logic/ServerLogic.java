package vs.shimu.logic;

import java.awt.Color;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
//import java.util.Random;
import java.util.Stack;

import vs.shimu.counter.TimeCounter;
import vs.shimu.entity.*;
import vs.shimu.entity.hostiles.Hostile;
import vs.shimu.entity.projectiles.Projectile;
import vs.shimu.server.InputPackage;
import vs.shimu.util.Dimension;

public class ServerLogic implements LogicInterface, Runnable {
	private Dimension display;

	private float eSpeed = 0;

	private TimeCounter tc;

	private ArrayList<InputPackage> input;
	private ArrayList<Player> players;
	private ArrayList<Projectile> bullets;
	private ArrayList<Hostile> enemies;
	private ArrayList<Entity> toRemove;

	private HashMap<Socket, Player> playerMap;

	private Stack<Color> colorStack;

	public ServerLogic(Dimension display) {
		this.display = display;
		tc = new TimeCounter(0, 15, 30);

		players = new ArrayList<Player>();
		playerMap = new HashMap<Socket, Player>();
		bullets = new ArrayList<Projectile>();
		enemies = new ArrayList<Hostile>();
		toRemove = new ArrayList<Entity>();

		colorStack = new Stack<Color>();
		fillColorStack();

		//createEnemies(8, eSpeed);
	}

	@Override
	public void run() {
		if (players.size() != 0)
			update();
		try {
			Thread.sleep(50);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	/**
	 * The main update function that makes calls to all others.
	 */
	public void update() {
		tc.addTo(1);
		updatePlayers();
		updateProjectiles();
		updateEnemies();
		removeDeads();
		if (enemies.size() < 3) {
			//createEnemies(5, eSpeed);
			eSpeed += 1;
		}
	}

	private void fillColorStack() {
		colorStack.push(new Color(156,0,0)); //Cherry
		colorStack.push(new Color(0,127,255)); //Azure
		colorStack.push(new Color(223,255,0)); //Chartreuse
		colorStack.push(new Color(0,127,0)); //Office
		colorStack.push(new Color(255,102,0)); //Safety
		colorStack.push(new Color(226,0,127)); //Cerise
		colorStack.push(new Color(0,204,204)); //Robinegg
		colorStack.push(new Color(127,127,127)); //Gery
	}

	public void join(Socket s) {
		Player p = new Player(getNextAvailableColor());
		players.add(p);
		playerMap.put(s, p);
	}

	public void leave(Socket s){
		Player p = playerMap.get(s);
		players.remove(p);
		playerMap.remove(s);
	}

	private Color getNextAvailableColor() {
		if(colorStack.empty())
			return new Color(127,127,127); //Gery
		return colorStack.pop();
	}

	private void updatePlayers() {
		Iterator<InputPackage> it = input.iterator();
		for (Player player : players) {
			InputPackage in = it.next();
			if(in == null)
				continue;
			onePlayer(player, in);
		}
	}

	private void onePlayer(Player player, InputPackage in) {
		if (!player.isAlive())
			return;
		int x = 0;
		int y = 0;
		if (in.getKey(1)) {
			x -= 1;
		}
		if (in.getKey(2)) {
			x += 1;
		}
		if (in.getKey(3)) {
			y -= 1;
		}
		if (in.getKey(4)) {
			y += 1;
		}
		if (!(x == 0 && y == 0))
			player.project(player.getSpeed(), Math.atan2(x, y));
		player.setAngle(Math.atan2(in.getMouse(0) - player.getX(), in.getMouse(1)
				- player.getY()));
		player.updateCrosshair();
		if (in.getMouse(2) != 0 && bullets.size() < 6)
			bullets.add(new Projectile(player, 6));
	}

	private void updateProjectiles() {
		for (Projectile bullet : bullets) {
			oneProjectile(bullet);
		}
	}

	private void oneProjectile(Projectile bullet) {
		int it = bullet.getSpeed() / 2;
		float d = bullet.getSpeed() / it;
		for (int i = 0; i < it; i++) {
			bullet.project(d);
			for (Hostile e : enemies) {
				if (bullet.intersects(e)) {
					e.dealDamage(bullet.getDamage(), bullet.getOwner());
					toRemove.add(bullet);
					return;
				}
			}
		}
		if (bullet.outsideScreen(display)) {
			toRemove.add(bullet);
		}
	}

	private void updateEnemies() {
		for (Hostile enemy : enemies) {
			if (!enemy.isAlive()) {
				toRemove.add(enemy);
				continue;
			}
			enemy.update(players);
			checkCollisions(enemy);
		}
	}

	private void removeDeads() {
		for (Entity death : toRemove) {
			if (death instanceof Projectile) {
				bullets.remove(death);
			} else if (death instanceof EntityPlus) {
				EntityPlus corpse = ((Hostile) death);
				enemies.remove(death);
				corpse.getKiller().addPoints(corpse.getPoints());
			}
		}
		toRemove.clear();
	}

	private void checkCollisions(Entity e1) {
		for (Entity e2 : enemies) {
			if (e1 != e2 && e1.intersects(e2)) {
				double d = e1.distanceTo(e2);
				double a = e1.angleFacing(e2);
				e1.project(8 - d, -a);
			}
		}

		for (Player player : players) {
			if (!player.isAlive())
				break;
			for (Entity enemy : enemies) {
				if (enemy.intersects(player)) {
					player.setAlive(false);
				}
			}
		}
	}

	public ArrayList<Player> getPlayers() {
		return players;
	}

	public Player getPlayer(Socket socket) {
		return playerMap.get(socket);
	}

	public ArrayList<Hostile> getEnemies() {
		return enemies;
	}

	public ArrayList<Projectile> getProjectiles() {
		return bullets;
	}

	public int getTime() {
		return tc.getCount();
	}

}
