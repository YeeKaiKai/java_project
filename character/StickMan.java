package character;


import java.awt.Image;
import java.awt.Rectangle;
import java.io.IOException;

import javax.swing.ImageIcon;

import map.Map;

public class StickMan implements Runnable {
	
	public final static int WIDTH = 32, HEIGHT = 32; // size of the stickMan
	public final static int initialX = 200, initialY = 200; // initial coordinate of stickMan
	public final static int regularSpeedX = 3, regularSpeedY = 1;
	
	private int x, y; // coordinate of the stickMan
	private int speedX, speedY; // speed that sitckMan moves
	private GameFrame gf; 
	private Image stickMan = new ImageIcon("stickMan.png").getImage();
	private boolean left = false, right = false, up = false, down = false; // if the stickMan moves to that direction
	private boolean jumping = false; // if the stickMAN is jumping
	
	private Map map;
	
	public StickMan(GameFrame gf) throws IOException {
		
		this.gf = gf;
		setStickManX(initialX);
		setStickManY(initialY);
		setSpeedX(regularSpeedX);
		setSpeedY(regularSpeedY);
		this.gravity();
		new Thread(this).start();
		
//		int a = (int)map.mapList.map1_barrier[0][0].get(0);
//		System.out.println(a);
		
	}
	
	public void setStickManX(int xx) {
		
		x = xx;
		
	}
	
	public void setStickManY(int yy) {
		
		y = yy;
		
	}
	
	public void setSpeedX(int sx) {
		
		speedX = sx;
		
	}
	
	public void setSpeedY(int sy) {
		
		speedY = sy;
		
	}
	
	public int getStickManX() {
		
		return x;
		
	}
	
	public int getStickManY() {
		
		return y;
		
	}
	
	public int getSpeedX() {
		
		return speedX;
		
	}
	
	public int getSpeedY() {
		
		return speedY;
		
	}
		
	public Image getStickMan() {
		
		return stickMan;
		
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
				if(hit("left")) {
					speedX = 0;
				}
				if(this.x >= 0) {
					x -= speedX;
				}
				speedX = regularSpeedX;
			}
			if(right) {
				if(hit("right")) {
					speedX = 0;
				}
				if(this.x + WIDTH <= GameFrame.WIDTH) {
					x += speedX;
				}
				speedX = regularSpeedX;
			} 
			if(up) {
				if(hit("down") && !jumping) { // if the stickMan doesn't stand on the ground, jump will be invalid 
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
		
		jumping = true;
		for(int i = 0; i < 32; i++) {
			if (hit("up")) {
				speedY = 0;
			}
			y -= speedY;
			try {
				Thread.sleep(7);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			speedY = regularSpeedY;
		}
		jumping = false;
		
	}
	
	/**
	 * if stickMan touches other object, it will be blocked by it.
	 */
	public boolean hit(String direction) {
		
//		Rectangle stickManRec = new Rectangle(x, y, StickMan.WIDTH, StickMan.HEIGHT);
//		for (int i = 0; i < map.mapList.map1_barrier[0].length; i++) {
//			Rectangle object = null;
//			int objectX = (int)map.mapList.map1_barrier[0][i].get(0);
//			int objectY = (int)map.mapList.map1_barrier[0][i].get(1); 
//			if (direction.equals("left")) {
//				object = new Rectangle(objectX + 2, objectY, Map.boxSize, Map.boxSize);
//			} else if (direction.equals("right")) {
//				object = new Rectangle(objectX - 2, objectY, Map.boxSize, Map.boxSize); 
//			} else if (direction.equals("up")) {
//				object = new Rectangle(objectX, objectY + 1, Map.boxSize, Map.boxSize);
//			} else if (direction.equals("down")) {
//				object = new Rectangle(objectX, objectY - 1, Map.boxSize, Map.boxSize);
//			}
//			if (stickManRec.intersects(object)) {
//				return true;
//			}
//		}
		if (direction.equals("down") && y >= 400) {
			return true;
		}
		return false;
		
	}
	
	/**
	 * stickMan will be effected by the gravity if it doesn't stand on the ground
	 */
	public void gravity() {
		
		new Thread() {
			public void run() {
				while(true) {
					if (!jumping && !hit("down")) { // if stickMan isn't jumping and floating, it should be falling
						y += speedY;
					}
					try {
						Thread.sleep(7);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}.start();
		
	}
	
}
