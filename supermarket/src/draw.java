import javax.swing.*;
import java.util.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.*;
import java.text.*;

public class draw {
	double a;

	double b;

	String c;

	public draw(double fnum, double all, String information) {// ���캯��
		a = fnum;// ���ָ����Ʒ�����۶�
		b = all;// ��õ��������۶�
		c = information;// ���ָ����Ʒ�ı��
		DrawJDialog frame = new DrawJDialog();
		frame.setSize(400, 300);
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	class DrawJDialog extends JDialog implements ActionListener// ���漰����ʵ��
	{
		JButton shut;

		{
			JTextArea one = new JTextArea();
			DrawPanel panel = new DrawPanel();
			shut = new JButton("�ر�");
			shut.addActionListener(this);
			double rate = 100 * a / b;
			DecimalFormat df = new DecimalFormat("########.00");
			rate = Double.parseDouble(df.format(rate));// ��ʽ��С����ʾ
			one.setText("" + c + "\n" + "ռ�������۶� " + rate + "%");
			panel.add(one);
			panel.add(shut);
			Container contentPane = getContentPane();
			contentPane.add(panel);
			setTitle("�������");
		}

		public void actionPerformed(ActionEvent evt) {
			Object source = evt.getSource();
			setVisible(false);
		}
	}

	class DrawPanel extends JPanel// ����ͼ
	{
		public void paintComponent(Graphics g) {
			g.clearRect(30, 40, 150, 150);
			double x1 = a;
			double x2 = b;
			int angle;
			super.paintComponent(g);
			angle = (int) (360 * x1 / x2);
			g.setColor(Color.blue);
			g.drawArc(30, 100, 150, 150, 0, angle);
			g.fillArc(30, 100, 150, 150, 0, angle);
			g.setColor(Color.GREEN);
			g.drawArc(30, 100, 150, 150, 0 + angle, 360 - angle);
			g.fillArc(30, 100, 150, 150, 0 + angle, 360 - angle);

		}
	}
}
