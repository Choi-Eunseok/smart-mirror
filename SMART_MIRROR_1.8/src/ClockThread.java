import java.util.Date;

public class ClockThread extends Thread {
	
	Main m;
	String time;
	
	public ClockThread(Main m) {
		this.m = m;
		start();
	}
	
	public void run() {
		while(true) {
			time = "" + new Date();
			String da[] = time.split(" ");
			String t[] = da[3].split(":");
			m.label1.setText(da[0] + " " + da[1] + " " + da[2]);
			m.label2.setText(t[0] + ":" + t[1]);
			m.label3.setText("." + t[2]);
		}
	}
}
