package character;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyListener extends KeyAdapter {
	
	private GameFrame gf;
	
	public KeyListener(GameFrame gf) {
		
		this.gf = gf;
		
	}
	
	public void keyPressed(KeyEvent e) {
		
		int code = e.getKeyCode();
		switch(code) {
		case KeyEvent.VK_LEFT:
			gf.getStickMan().moveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			gf.getStickMan().moveRight();
			break;
		case KeyEvent.VK_UP:
			gf.getStickMan().moveUp();
			break;
		}
		
	}
	
	public void keyReleased(KeyEvent e) {
		
		int code = e.getKeyCode();
		switch(code) {
		case KeyEvent.VK_LEFT:
			gf.getStickMan().stopMoveLeft();
			break;
		case KeyEvent.VK_RIGHT:
			gf.getStickMan().stopMoveRight();
			break;	
		case KeyEvent.VK_UP:
			gf.getStickMan().stopMoveUp();
			break;	
		}
		
	}
}
