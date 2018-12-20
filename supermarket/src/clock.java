import java.awt.Font;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.swing.*;


public class clock extends JPanel {
	private JLabel label = new JLabel("");

	public clock() {
		label.setFont(new Font("", Font.PLAIN, 24));
		this.add(label);

		class ClockThread extends Thread {
			Date date = new Date();

			Calendar calendar = new GregorianCalendar();

			public ClockThread() {
				calendar.setTime(date);
			}

			public void run() {
				while (true) {
					SimpleDateFormat dateFormat = new SimpleDateFormat(
							"yyyy-MM-dd HH:mm:ss");
					calendar.set(Calendar.SECOND,
							calendar.get(Calendar.SECOND) + 1);
					label.setText(dateFormat.format(calendar.getTime()));
					try {
						Thread.sleep(1000);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		}
		new ClockThread().start();
	}
}