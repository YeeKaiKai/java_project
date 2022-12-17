package final_project;


import java.awt.Graphics;
import java.awt.image.BufferedImage;
import javax.swing.JFrame;

public class GameFrame extends JFrame {
	
	public final static int WIDTH = 1024;
	public final static int HEIGHT = 576;
	
	private StickMan stickMan;
	
	public GameFrame() throws Exception {
		
		stickMan = new StickMan(this);
		new Thread() {
			public void run() {
				while(true) {
					repaint();
				}
			}
		}.start();
		this.initFrame();
		
	}
	
	public StickMan getStickMan() {
		return stickMan;
	}
	
	/**
	 * initial the frame
	 */
	public void initFrame() {
		
		this.setSize(WIDTH, HEIGHT);
		this.setTitle("Window");
		this.setResizable(false);
		this.setLocationRelativeTo(null); // Set the window at the center of the screen
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // After clicking close on frame, the program will terminate
		this.setVisible(true);
		
		KeyListener kl = new KeyListener(this);
		this.addKeyListener(kl);
		
	}
	
	public void paint(Graphics g) {
		
		BufferedImage bi = (BufferedImage)this.createImage(WIDTH, HEIGHT);
		Graphics big = bi.getGraphics();
		big.drawImage(stickMan.getStickMan(), stickMan.getStickManX(), stickMan.getStickManY(), StickMan.WIDTH, StickMan.HEIGHT, null);
		g.drawImage(bi, 0, 0, null);
		
	}
}
