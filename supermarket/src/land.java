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
		frame.setTitle("��ӪԱ��½");
		frame.setLocation(300,300);
		frame.setSize(270, 170);
		frame.setVisible(true);

	}
}

class landframe extends JDialog implements ActionListener {
	public landframe() {
		label1 = new JLabel("JAVA������Ӫϵͳ ���½");
		label2 = new JLabel("�û�����");
		label3 = new JLabel("���룺");
		hbox1 = Box.createHorizontalBox();
		hbox2 = Box.createHorizontalBox();
		vbox = Box.createVerticalBox();
		jf1 = new JTextField();
		JPF= new JPasswordField();
		JPF.addActionListener(this);
		land = new JButton("��½");
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
		if (source == land || source == JPF) {// ��½��Ŧ���ڶ����ı������ENTER����ʵ��
			String a = "", b = null, read;
			String str = "";
			boolean denglusuccees = false;
			try {
				FileReader fr = new FileReader("denglu.txt");
				BufferedReader br = new BufferedReader(fr);
				while ((read = br.readLine()) != null) {
					str = str +read;
					System.out.println("���������û������ǣ�"+str);
				}
				StringTokenizer sto = new StringTokenizer(str);
				a = sto.nextToken();
				b = sto.nextToken();
				System.out.println(a+" "+b);
				if (jf1.getText().equals(a) && JPF.getText().equals(b)) {
					denglusuccees = true;
				}// ��֤��½Ա���뼰����
			} catch (IOException ie) {
				System.err.println(ie.getMessage());
			}
			if (denglusuccees == true) {
				setVisible(false);
				mainview mw = new mainview();
				mw.setTitle(a + "Ϊ������");
				mw.setSize(850, 500);
				mw.setLocation(200, 180);
				mw.setVisible(true);
			} else {
				jf1.setText("");
				JPF.setText("");
				JOptionPane.showMessageDialog(null, "�Ƿ��û�");
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
