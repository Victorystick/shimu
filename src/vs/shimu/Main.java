package vs.shimu;

import java.util.Stack;

import vs.shimu.display.Display;
import vs.shimu.state.*;

public class Main implements Runnable, StateHandler {
	private Stack<State> ss;
	private Display display;
	private Controller control;
	public Main() {
		ss = new Stack<State>();
		control = new Controller();
		display = new Display(control, 800, 500);

		// stateStack.push(new MenuScreen(this, display, control));
		ss.push(new GameScreen(this, display, control));
	}

	@Override
	public void run() {
		while (!ss.isEmpty()) {
			ss.peek().update();
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

	/**
	 * Pops the stateStack removing the top state.
	 */
	public void popState() {
		ss.pop();
	}

	/**
	 * Starts the game.
	 * 
	 * @param args
	 */
	public static void main(String[] args) {
		Main main = new Main();
		new Thread(main).start();
	}

	
	@Override
	public void pushState(State gs) {
		ss.push(gs);
	}
}
