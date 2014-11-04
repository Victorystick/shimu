package vs.shimu.display;

import java.awt.GradientPaint;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JLabel;
import javax.swing.JPanel;

import vs.shimu.entity.Player;

//TODO Everything
public class PlayerPanel extends JPanel {
	/**
	 * 
	 */
	private static final long serialVersionUID = 4223140220958937391L;
	private Player player;
	private int height;
	JPanel csq;
	
	public PlayerPanel(Player player) {
		this.player = player;
		height = 20;
		
		BufferedImage img = new BufferedImage(height-1, height-1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		
		  GradientPaint gradient1 = new GradientPaint(0,0,player.getColor(),height/2,height/2,player.getColor().brighter(),false);
		  g.setPaint(gradient1);
		  g.fillRect(99,99,199,119);
		csq.paint(g);
		
		JLabel nlb = new JLabel(player.toString());
		this.add(nlb);
	}
	
	public void update(int time) {
		//g.paint();
	}
	
	private void drawPColor() {
		BufferedImage img = new BufferedImage(height-1, height-1, BufferedImage.TYPE_INT_RGB);
		Graphics2D g = img.createGraphics();
		
		  GradientPaint gradient1 = new GradientPaint(0,0,player.getColor(),height/2,height/2,player.getColor().brighter(),false);
		  g.setPaint(gradient1);
		  g.fillRect(99,99,199,119);
		csq.paint(g);
		
	}
}
