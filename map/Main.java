package map;

import java.io.IOException;

import javax.swing.JFrame;

public class Main {
	public static void main(String[] args) throws IOException {
		JFrame f = new JFrame();
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		f.setResizable(false);	//�վ㬰���i�H��j�M�վ�����j�p
		f.setTitle("�j��");
		f.setSize(1024, 576);
		
		Map mapPanel = new Map();
		f.add(mapPanel);
		//f.pack();
		
		f.setLocationRelativeTo(null);	//���e���m��
		f.setVisible(true);
		
	}
	
}
