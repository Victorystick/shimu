package vs.shimu.state;

import java.awt.image.BufferedImage;

import vs.shimu.Controller;
import vs.shimu.Main;
import vs.shimu.display.Display;

public class MenuScreen extends State {
	
	public MenuScreen(Main main, Display display, Controller control) {
		super(main, display, control);
	}

	@Override
	public void update() {
		boolean[] keys = control.getKeys();
		if(keys[0]){
			System.out.println("Escape Pressed: Popping");
			pop();
			return;
		}
		if(display.isVisible()) {
			System.out.println("RENDERING");
			render();
		} else {
			pop();
		}
	}

	@Override
	public void render() {
		BufferedImage img = new BufferedImage(display.getWidth(), display.getHeight(), BufferedImage.TYPE_INT_RGB);
		
		display.update(img);
	}

}
