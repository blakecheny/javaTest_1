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

	public draw(double fnum, double all, String information) {// 构造函数
		a = fnum;// 获得指定商品当销售额
		b = all;// 获得当天总销售额
		c = information;// 获得指定商品的编号
		DrawJDialog frame = new DrawJDialog();
		frame.setSize(400, 300);
		frame.setLocation(400, 300);
		frame.setVisible(true);
	}

	class DrawJDialog extends JDialog implements ActionListener// 界面及功能实现
	{
		JButton shut;

		{
			JTextArea one = new JTextArea();
			DrawPanel panel = new DrawPanel();
			shut = new JButton("关闭");
			shut.addActionListener(this);
			double rate = 100 * a / b;
			DecimalFormat df = new DecimalFormat("########.00");
			rate = Double.parseDouble(df.format(rate));// 格式化小数显示
			one.setText("" + c + "\n" + "占今天销售额 " + rate + "%");
			panel.add(one);
			panel.add(shut);
			Container contentPane = getContentPane();
			contentPane.add(panel);
			setTitle("销售情况");
		}

		public void actionPerformed(ActionEvent evt) {
			Object source = evt.getSource();
			setVisible(false);
		}
	}

	class DrawPanel extends JPanel// 画饼图
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
