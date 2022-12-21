package map;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Map extends JPanel {
	private int originalTileSize = 16;	//圖片原始大小為16*16
	private int scale = 2;	//放大倍數
	private int tileSize = originalTileSize * scale;	//32, 32*18(1024*576)
	private int screenLength = 32;
	private int screenWidth = 18;
	public final static int boxSize = 32; // yeekai : constant boxSize
	
//	Thread mapThread = new Thread(this);
	
	public MapList mapList = new MapList(); // yeekai : change (protected) to public
	BufferedImage img1;
	
	public Map() throws IOException{
		this.setPreferredSize(new Dimension(1024,576));
		this.setBackground(Color.WHITE);
		this.setDoubleBuffered(true);
		setPicture();

//		mapThread.start();
	}
	
	public void setPicture() throws IOException{
		img1 = ImageIO.read(new File("src/磚頭.png"));
	}
	
	public void drawMap(Graphics g) {
		int x = 0;
		int y = 0;
		int col = 0;
		int row = 0;
		int barrierIndex = 0;
		
		while(col < screenLength && row < screenWidth) {
			if(mapList.map1[row][col] == 1) {
				g.drawImage(img1, x, y, tileSize, tileSize, null);	
				mapList.map1_barrier[0][barrierIndex] = new ArrayList();
				mapList.map1_barrier[0][barrierIndex].add(x);
				mapList.map1_barrier[0][barrierIndex].add(y);
				//use mapList.map1_barrier[0][barrierIndex].get(0) to store x
				//use mapList.map1_barrier[0][barrierIndex].get(1) to store y
				barrierIndex++;
			}
			if(col != screenLength - 1) {
				col++;
				x = x + tileSize;
			}
			else {
				row++;
				col = 0;
				y = y + tileSize;
				x = 0;
			}
//			System.out.println("x:"+x+" y:"+y+"  row:"+row+" col:"+col);
			//print map1_barrier
//			for(int i = 0;i<barrierIndex;i++) {
//				System.out.println(mapList.map1_barrier[0][i]+" ");
//			}
		}
	}
	
//	@Override
//	public void run() {
//		while(mapThread != null) {
//			
//			update();
//			repaint();
//		}
//	}
//	
//	//或許可以用來更新數據之類的
//	public void update() {
//		
//	}
//	
//	public void paint(Graphics g) {
//		System.out.println("2");
//		super.paint(g);
//		drawMap(g);
//		//g.dispose();
//	}


}
