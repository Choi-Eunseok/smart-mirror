import java.awt.*;
import java.awt.image.BufferedImage;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Main extends JFrame {

	JLabel label1, label2, label3;
	JLabel[] yearLabel = new JLabel[2];
	JLabel[] weatherLabel = new JLabel[8];
	JLabel[] weatherILabel = new JLabel[8];
	JLabel[] weatherHLabel = new JLabel[8];
	JLabel temp, humi;
	JLabel stick1, stick2;
	JLabel thermometer;
	
	ClockThread ct;
	RSS_weather rw;
	Serial_temp_humi s;

	int x = 60;
	int y = 475;

	public Main() {
		super();
		JPanel panel1 = new JPanel();
		panel1.setLayout(null);
		panel1.setBackground(Color.BLACK);
		panel1.setForeground(Color.WHITE);
		add(panel1);
		ct = new ClockThread(this);
		rw = new RSS_weather(this);
		s = new Serial_temp_humi(this);
		label1 = new JLabel("");
		label1.setForeground(Color.WHITE);
		label1.setFont(new Font("Arial", Font.PLAIN, 70));
		label1.setBounds(200, 20, 1000, 60);
		label2 = new JLabel("");
		label2.setForeground(Color.WHITE);
		label2.setFont(new Font("Arial", Font.PLAIN, 250));
		label2.setBounds(20, 100, 1000, 200);
		label3 = new JLabel("");
		label3.setForeground(Color.WHITE);
		label3.setFont(new Font("Arial", Font.PLAIN, 70));
		label3.setBounds(640, 225, 100, 70);
		for (int i = 0; i < 2; i++) {
			yearLabel[i] = new JLabel("");
			yearLabel[i].setForeground(Color.WHITE);
			yearLabel[i].setFont(new Font("Arial", Font.PLAIN, 15));
			panel1.add(yearLabel[i]);
		}
		for (int i = 0; i < 8; i++) {
			weatherILabel[i] = new JLabel("");
			weatherILabel[i].setBounds(x + 85 * i, y, 50, 50);
			panel1.add(weatherILabel[i]);
			weatherHLabel[i] = new JLabel("");
			weatherHLabel[i].setFont(new Font("Arial", Font.PLAIN, 18));
			weatherHLabel[i].setForeground(Color.WHITE);
			weatherHLabel[i].setBounds(x - 5 + 85 * i, y - 44, 70, 30);
			panel1.add(weatherHLabel[i]);
			weatherLabel[i] = new JLabel("");
			weatherLabel[i].setFont(new Font("Arial", Font.PLAIN, 20));
			weatherLabel[i].setForeground(Color.WHITE);
			panel1.add(weatherLabel[i]);
		}
		temp = new JLabel("");
		temp.setForeground(Color.WHITE);
		temp.setFont(new Font("Arial", Font.PLAIN, 70));
		temp.setBounds(x + 80, y+510, 200, 60);
		humi = new JLabel("");
		humi.setForeground(Color.WHITE);
		humi.setFont(new Font("Arial", Font.PLAIN, 70));
		humi.setBounds(x + 430, y+510, 200, 60);
		ImageIcon t = new ImageIcon(".\\Icon\\temp.png");
		Image image1 = t.getImage();
		stick1 = new JLabel(new ImageIcon(image1));
		stick1.setBounds(x+50, y+210, 200, 400);
		ImageIcon T = new ImageIcon(".\\Icon\\ht.png");
		Image image2 = T.getImage();
		thermometer = new JLabel(new ImageIcon(image2));
		thermometer.setBounds(x-25, y+160, 700, 700);
		ImageIcon h = new ImageIcon(".\\Icon\\humi.png");
		Image image3 = h.getImage();
		stick2 = new JLabel(new ImageIcon(image3));
		stick2.setBounds(x+375, y+305, 250, 400);
		panel1.add(label1);
		panel1.add(label2);
		panel1.add(label3);
		panel1.add(temp);
		panel1.add(humi);
		panel1.add(thermometer);
		panel1.add(stick1);
		panel1.add(stick2);
		getContentPane().setBackground(Color.BLACK);
		getContentPane().setForeground(Color.WHITE);
		setUndecorated(true);
		setSize(768, 1366);
		setVisible(true);

	}

	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.setColor(Color.WHITE);
		g.fillRect(x - 15, y - 60, 680, 190);
		g.setColor(Color.BLACK);
		g.fillRect(x - 10, y - 55, 670, 180);
		g.setColor(Color.WHITE);
		for (int i = 0; i < 8; i++) {
			g.drawLine(x - 15 + 85 * i, y - 45, x - 15 + 85 * i, y + 115);
		}
	}

	public static void main(String[] args) {
		new Main();
	}

}
