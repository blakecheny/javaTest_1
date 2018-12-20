import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

import javax.swing.*;

import jdk.management.cmm.SystemResourcePressureMXBean;

public class land {
	public static void main(String[] args) {
		landframe frame = new landframe();
		frame.setTitle("收营员登陆");
		frame.setLocation(300,300);
		frame.setSize(270, 170);
		frame.setVisible(true);

	}
}

class landframe extends JDialog implements ActionListener {
	public landframe() {
		label1 = new JLabel("JAVA超市收营系统 请登陆");
		label2 = new JLabel("用户名：");
		label3 = new JLabel("密码：");
		hbox1 = Box.createHorizontalBox();
		hbox2 = Box.createHorizontalBox();
		vbox = Box.createVerticalBox();
		jf1 = new JTextField();
		JPF= new JPasswordField();
		JPF.addActionListener(this);
		land = new JButton("登陆");
		land.addActionListener(this);
		panel1 = new JPanel();
		panel2 = new JPanel();
		hbox1.add(label2);
		hbox1.add(Box.createHorizontalStrut(5));
		hbox1.add(jf1);
		hbox2.add(label3);
		hbox2.add(Box.createHorizontalStrut(18));
		panel2.add(land, "Center");
		hbox2.add(JPF);
		vbox.add(label1);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox1);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(hbox2);
		vbox.add(Box.createVerticalStrut(10));
		vbox.add(panel2);
		panel1.add(vbox);
		getContentPane().add(panel1, "Center");
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();
		if (source == land || source == JPF) {// 登陆按纽及第二个文本输入框ENTER功能实现
			String a = "", b = null, read;
			String str = "";
			boolean denglusuccees = false;
			try {
				FileReader fr = new FileReader("denglu.txt");
				BufferedReader br = new BufferedReader(fr);
				while ((read = br.readLine()) != null) {
					str = str +read;
					System.out.println("读出来的用户内容是："+str);
				}
				StringTokenizer sto = new StringTokenizer(str);
				a = sto.nextToken();
				b = sto.nextToken();
				System.out.println(a+" "+b);
				if (jf1.getText().equals(a) && JPF.getText().equals(b)) {
					denglusuccees = true;
				}// 验证登陆员号码及密码
			} catch (IOException ie) {
				System.err.println(ie.getMessage());
			}
			if (denglusuccees == true) {
				setVisible(false);
				mainview mw = new mainview();
				mw.setTitle(a + "为您服务");
				mw.setSize(850, 500);
				mw.setLocation(200, 180);
				mw.setVisible(true);
			} else {
				jf1.setText("");
				JPF.setText("");
				JOptionPane.showMessageDialog(null, "非法用户");
			}
		}

	}

	private JLabel label1, label2, label3;

	private Box hbox1, hbox2, vbox;

	private JTextField jf1;
	
	private JPasswordField JPF;

	private JButton land;

	private JPanel panel1, panel2;
}
