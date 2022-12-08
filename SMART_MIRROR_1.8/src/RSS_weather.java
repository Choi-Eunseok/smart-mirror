import java.awt.Image;
import java.io.*;
import java.net.*;

import javax.swing.ImageIcon;

public class RSS_weather extends Thread {

	Main m;

	int x = 60;
	int y = 475;
	int ch = 0;

	public RSS_weather(Main m) {
		this.m = m;
		start();
	}

	public static String readRSS(String urlAddress) {
		try {
			URL rssUrl = new URL(urlAddress);
			BufferedReader in = new BufferedReader(new InputStreamReader(rssUrl.openStream()));
			String sourceCode = "";
			String line;
			while ((line = in.readLine()) != null) {
				if (line.contains("<tm>")) {
					int firstPos = line.indexOf("<tm>");
					String tm = line.substring(firstPos);
					tm = tm.replace("<tm>", "");
					int lastPos = tm.indexOf("</tm>");
					tm = tm.substring(0, lastPos);
					sourceCode += tm + "/";
				}
				if (line.contains("<hour>")) {
					int firstPos = line.indexOf("<hour>");
					String hour = line.substring(firstPos);
					hour = hour.replace("<hour>", "");
					int lastPos = hour.indexOf("</hour>");
					hour = hour.substring(0, lastPos);
					sourceCode += hour + "/";
				}
				if (line.contains("<day>")) {
					int firstPos = line.indexOf("<day>");
					String day = line.substring(firstPos);
					day = day.replace("<day>", "");
					int lastPos = day.indexOf("</day>");
					day = day.substring(0, lastPos);
					sourceCode += day + "/";
				}
				if (line.contains("<wfEn>")) {
					int firstPos = line.indexOf("<wfEn>");
					String wf = line.substring(firstPos);
					wf = wf.replace("<wfEn>", "");
					int lastPos = wf.indexOf("</wfEn>");
					wf = wf.substring(0, lastPos);
					sourceCode += wf + "/";
				}
			}
			in.close();
			return sourceCode;
		} catch (MalformedURLException ue) {
			System.out.println("Malformed URL");
		} catch (IOException ioe) {
			System.out.println("Something went wrong reading the contents");
		}
		return null;
	}

	public void run() {
		while (true) {
			String w = readRSS("http://www.kma.go.kr/wid/queryDFSRSS.jsp?zone=2635055400");
			String hw[] = w.split("/");
			int tm = Integer.parseInt(hw[0].substring(0, 8));
			int a = Integer.parseInt(hw[2]);
			for (int i = 0; i < 8; i++) {
				if (Integer.parseInt(hw[3 * i + 2]) == a) {
					m.yearLabel[ch].setText("| " + String.valueOf(tm + a));
					m.yearLabel[ch].setBounds(x - 5 + 85 * i - 10, y - 85, 80, 15);
					a++;
					ch++;
				}
				if (ch == 2) {
					ch = 0;
				}
				m.weatherHLabel[i].setText(hw[3 * i + 1] + " Hour");
				ImageIcon image = new ImageIcon(new ImageIcon(".\\Icon\\" + hw[3 * (i + 1)] + ".png").getImage()
						.getScaledInstance(50, 50, Image.SCALE_SMOOTH));
				m.weatherILabel[i].setIcon(image);
				if (hw[3 * (i + 1)].contains(" ")) {
					String word[] = hw[3 * (i + 1)].split(" ");
					m.weatherLabel[i].setText("<html>" + word[0] + "<br>" + word[1] + "</html>");
					m.weatherLabel[i].setBounds(x - 5 + 85 * i, y + 60, 70, 50);
				} else {
					m.weatherLabel[i].setText(hw[3 * (i + 1)]);
					m.weatherLabel[i].setBounds(x - 5 + 85 * i, y + 50, 70, 50);
				}
			}
		}
	}
}
