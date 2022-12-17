package final_project;


import java.awt.Image;

import javax.swing.ImageIcon;


public class StickMan implements Runnable {
	
	public final static int WIDTH = 100, HEIGHT = 100; // size of the stickMan
	public final static int initialX = 200, initialY = 200; // initial coordinate of stickMan
	
	private int x, y; // coordinate of the stickMan
	private int speedX = 5, speedY = 1; // speed that sitckMan moves
	private GameFrame gf; 
	private Image stickMan = new ImageIcon("stickMan.png").getImage();
	private boolean left = false, right = false, up = false, down = false; // if the stickMan moves to that direction
	private boolean onGround = true; // if the stickMan stands on the ground
	
	public StickMan(GameFrame gf) {
		
		this.gf = gf;
		setStickManX(initialX);
		setStickManY(initialY);
		new Thread(this).start();
		
	}
	
	public void setStickManX(int x) {
		
		this.x = x;
		
	}
	
	public void setStickManY(int y) {
		
		this.y = y;
		
	}
	
	public int getStickManX() {
		
		return this.x;
		
	}
	
	public int getStickManY() {
		
		return this.y;
		
	}
		
	public Image getStickMan() {
		
		return this.stickMan;
		
	}
	
	/**
	 * the stickMan is moving to left
	 */
	public void moveLeft() {
		
		left = true;
		
	}
	
	/**
	 * the stickMan is moving to right
	 */
	public void moveRight() {
		
		right = true;
		
	}
	
	/**
	 * the stickMan tries to jump
	 */
	public void moveUp() {
		
		up = true;
		
	}
	
	/**
	 * the stickMan stop moving to left
	 */
	public void stopMoveLeft() {
		
		left = false;

	}
	
	/**
	 * the stickMan stop moving to right
	 */
	public void stopMoveRight() {
		
		right = false;

	}
	
	/**
	 * the stickMan stop trying to jump
	 */
	public void stopMoveUp() {
		
		up = false;
		
	}
	
	/**
	 * movement of the stickMan
	 */
	public void run() {
		
		while(true) {
			if(left) {
				if(this.x >= 0) {
					this.x -= this.speedX;
				}
			}
			if(right) {
				if(this.x + WIDTH <= GameFrame.WIDTH) {
					this.x += this.speedX;
				}
			} 
			if(up) {
				if(onGround) { // if stickMan doesn't stand on the ground, jump will be invalid. 
					onGround = false;
					new Thread() {
						public void run() {
							jump();
						}
					}.start();
				}
			}
			try {
				Thread.sleep(15);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
	}
	
	public void jump() {
		for(int i = 0; i < 100; i++) {
			this.y -= this.speedY;
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		for(int i = 0; i < 100; i++) {
			this.y += this.speedY;
			try {
				Thread.sleep(2);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		onGround = true;
	}
	
}
