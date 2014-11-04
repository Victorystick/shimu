package vs.shimu.client;

import java.util.Stack;
import java.util.logging.Logger;

import vs.shimu.Controller;
import vs.shimu.display.Display;
import vs.shimu.state.GameScreen;
import vs.shimu.state.State;
import vs.shimu.state.StateHandler;

public class MainClient implements Runnable, StateHandler {
	private int width, height;

	private Stack<State> ss;
	private Display display;
	private Controller control;
	private Logger logger;

	public MainClient() {
		width = 600;
		height = 300;
		ss = new Stack<State>();
		control = new Controller();
		display = new Display(control, width, height);
		// ss.push(new ClientMenu(this, display, control));
		ss.push(new ClientScreen(this, display, control));
	}

	@Override
	public void run() {
		while (!ss.isEmpty()) {
			ss.peek().update();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				logger.severe("Interrupted Exception found!");
				for (StackTraceElement ste : e.getStackTrace()) {
					logger.severe(ste.toString());
				}
			}
		}
		System.exit(0);
	}

	public static void main(String[] args) {
		MainClient mc = new MainClient();
		new Thread(mc).start();
	}

	/**
	 * Pops the stateStack removing the top state.
	 */
	@Override
	public void popState() {
		ss.pop();
	}

	@Override
	public void pushState(State gs) {
		ss.push(gs);
	}
}
