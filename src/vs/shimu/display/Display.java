package vs.shimu.display;

import java.awt.*;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import vs.shimu.Controller;

public class Display {
	private int height, width;
	private JFrame frame;
//	private JPanel gamePanel;
//	private JPanel scorePanel;
	private JPanel panel;

	public Display(Controller control, int width, int height) {
		this.width = width;
		this.height = height;

		frame = new JFrame("Shimu");
		frame.setResizable(false);
		frame.addKeyListener(control);
		frame.addMouseMotionListener(control);
		frame.addMouseListener(control);

//		Container cp = frame.getContentPane();
//		JPanel window = new JPanel();
//
//		cp.add(window);
//
//		window.setLayout(new BorderLayout());
//
//		gamePanel = new JPanel();
//		gamePanel.setPreferredSize(new Dimension(width, height));
//		window.add(gamePanel, BorderLayout.CENTER);
//
//		scorePanel = new JPanel();
//		scorePanel.setPreferredSize(new Dimension(200, height));
//		window.add(scorePanel, BorderLayout.EAST);
		
		panel = new JPanel();
		panel.setPreferredSize(new Dimension(width, height));
		frame.add(panel);

		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}

	public void update(BufferedImage img) {
		Graphics2D g = (Graphics2D) panel.getGraphics();
		g.drawImage(img, 0, 0, null);
		g.dispose();
	}
	
//	public void addPlayerPanel(Player player){
//		scorePanel.add(new PlayerPanel(player));
//	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}
	
	public JPanel getScreen(){
//		return gamePanel;
		return panel;
	}

	/**
	 * Visibility check for rendering.
	 * 
	 * @return whether the frame is visible or not.
	 */
	public boolean isVisible() {
		return frame.isVisible();
	}
	
	public void pack(){
		frame.pack();
	}

}
