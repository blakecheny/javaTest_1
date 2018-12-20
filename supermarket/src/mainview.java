import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;

class mainview extends JFrame implements ActionListener

{
	DataManagement mydata = new DataManagement();

	sellcount mysell = new sellcount();

	judge myjudge = new judge();

	int dcount = 1;

	public mainview() {
		changeButton1 = new JButton("修改价格");
		changeButton1.addActionListener(this);
		changeButton1.setEnabled(false);

		changeButton2 = new JButton("修改数量");
		changeButton2.addActionListener(this);
		changeButton2.setEnabled(false);

		deleteButton = new JButton("删除商品");
		deleteButton.addActionListener(this);
		deleteButton.setEnabled(false);

		finishButton = new JButton("完成结算");
		finishButton.addActionListener(this);
		finishButton.setEnabled(false);

		cheapButton = new JButton("会员结算");
		cheapButton.addActionListener(this);
		cheapButton.setEnabled(false);

		recordButton = new JButton("记录清单");
		recordButton.addActionListener(this);
		recordButton.setEnabled(false);

		moneyButton = new JButton("找零计算");
		moneyButton.addActionListener(this);
		moneyButton.setEnabled(false);

		clearButton = new JButton("清除清单");
		clearButton.addActionListener(this);

		textAll = new JTextField(0);
		textAll.setEditable(false);
		textAll.setBorder(BorderFactory.createEtchedBorder());

		textChange = new JTextArea(7, 0);
		textChange.setEditable(false);
		textChange.setBorder(BorderFactory.createEtchedBorder());

		hbox2 = Box.createHorizontalBox();
		vbox2 = Box.createVerticalBox();
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(changeButton1);
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(changeButton2);
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(deleteButton);
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(finishButton);
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(cheapButton);
		vbox2.add(Box.createVerticalStrut(9));
		vbox2.add(textAll);
		vbox2.add(moneyButton);
		vbox2.add(textChange);
		vbox2.setBorder(BorderFactory.createEtchedBorder());

		textArea = new JTextArea(24, 37);
		textArea.setMaximumSize(textArea.getPreferredSize());
		textArea.setEditable(false);
		scrollPane = new JScrollPane(textArea);

		textAdd = new JTextField(20);
		textAdd.addActionListener(this);

		vbox1 = Box.createVerticalBox();
		hbox1 = Box.createHorizontalBox();
		label1 = new JLabel("此处输入商品编号    ");
		label2 = new JLabel("商品信息显示：");
		drawButton = new JButton("单天商品销售情况");
		drawButton.addActionListener(this);
		vbox1.add(label1);
		vbox1.add(textAdd);
		hbox1.add(label2);
		hbox1.add(drawButton);
		vbox1.add(hbox1);
		vbox1.add(scrollPane);
		vbox1.setBorder(BorderFactory.createLoweredBevelBorder());

		vbox3 = Box.createVerticalBox();
		hbox3 = Box.createHorizontalBox();
		label3 = new JLabel("最终清单");
		label4 = new JLabel("版权所属");
		label4.setBorder(BorderFactory.createEtchedBorder());
		textFinish = new JTextArea(24, 30);
		textFinish.setEditable(false);
		textFinish.setBorder(BorderFactory.createEtchedBorder(Color.WHITE,
				Color.BLACK));
		hbox3.add(recordButton);
		hbox3.add(Box.createHorizontalStrut(10));
		hbox3.add(clearButton);
		hbox3.add(Box.createHorizontalStrut(10));
		hbox3.add(label4);
		vbox3.add(new clock());
		vbox3.add(label3);
		hbox3.setBorder(BorderFactory.createEtchedBorder());
		vbox3.add(textFinish);
		vbox3.add(hbox3);
		vbox3.setBorder(BorderFactory.createLoweredBevelBorder());

		hbox2.add(vbox1);
		hbox2.add(vbox2);
		hbox2.add(vbox3);

		getContentPane().add(hbox2);

		addWindowListener(new WindowAdapter() {
			public void windowClosing(WindowEvent e) {
				System.exit(0);
			}
		});
	}

	public void actionPerformed(ActionEvent evt) {
		Object source = evt.getSource();

		if (source == textAdd) {// 商品添加
			String log = textAdd.getText();
			StringTokenizer sto = new StringTokenizer(log);
			String dcode = sto.nextToken();
			boolean inputwrite = true;
			int dnum = 1;
			if (sto.hasMoreTokens())
				try {
					int tempnum=Integer.parseInt(sto.nextToken());
					if(tempnum>0){dnum = Integer.parseInt(sto.nextToken());}
					else inputwrite=false;
				} catch (NumberFormatException e) {
					JOptionPane.showMessageDialog(null, "请输入数字");
					inputwrite = false;
				}
			if (inputwrite == true) {
				if (myjudge.find(dcode) == -1) {
					String wrong = "Can't find " + dcode;
					JOptionPane.showMessageDialog(null, wrong);
					textAdd.setText("");
				} else {
					int location = myjudge.find(dcode);
					String dname = myjudge.judgeData[location].getName();
					double dprice = myjudge.judgeData[location].getPrice();
					mydata.add(dcode, dname, dprice, dnum);
					textArea.setText("");
					for (int i = 0; i < mydata.acount; i++) {
						textArea.insert("商品" + (i + 1) + " 编号:"
								+ mydata.productData[i].getCode() + " 商品名称："
								+ mydata.productData[i].getName() + " 商品价格： "
								+ mydata.productData[i].getPrice() + "元/单位  "
								+ mydata.productData[i].getNum() + " 个" + "\n",
								0);
					}
					textArea.insert(" 共" + mydata.givecost() + " 元\n", 0);

					textAdd.setText("");
				}
				changeButton1.setEnabled(true);
				changeButton2.setEnabled(true);
				deleteButton.setEnabled(true);
				cheapButton.setEnabled(true);
				finishButton.setEnabled(true);
			} else {
				textAdd.setText("");
			}
		}

		else if (source == drawButton) {
			String ecode = JOptionPane.showInputDialog(null, "请输入欲查询的商品编号");
			if (ecode != null) {
				if (mysell.find(ecode) == -1)
					JOptionPane.showMessageDialog(null, "今天还未出售" + ecode);
				else
					new draw(mysell.sellData[mysell.find(ecode)].getNum()
							* mysell.sellData[mysell.find(ecode)].getPrice(),
							mysell.givecost(), mysell.sellinformantion(mysell
									.find(ecode)));
			}
		}

		else if (source == changeButton1) {
			double tempprice;
			String changepricecode = JOptionPane
					.showInputDialog("输入要修改价格的商品编号");
			if (changepricecode != null) {
				int getpchange = mydata.find(changepricecode);
				if (getpchange == -1)
					JOptionPane.showMessageDialog(null, "用户未购买货物"
							+ changepricecode);
				else {
					double price = mydata.productData[getpchange].getPrice();
					try {
						tempprice = Double.parseDouble(JOptionPane
								.showInputDialog("输入新单价"));
						while (tempprice <= 0) {
							tempprice = Double.parseDouble(JOptionPane
									.showInputDialog("重新输入新单价（系统默认大于零元）"));
						}
						price=tempprice;
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "请输入数字");
					}
					mydata.productData[getpchange].setPrice(price);
					textArea.setText("");
					for (int i = 0; i < mydata.acount; i++) {
						textArea.insert("商品" + (i + 1) + " 编号:"
								+ mydata.productData[i].getCode() + " 商品名称："
								+ mydata.productData[i].getName() + " 商品价格： "
								+ mydata.productData[i].getPrice() + "元/单位  "
								+ mydata.productData[i].getNum() + " 个" + "\n",
								0);
					}
					textArea.insert(" 共" + mydata.givecost() + " 元\n", 0);
				}

			}
		}

		else if (source == changeButton2) {
			String changenum = JOptionPane.showInputDialog("输入要修改数量的商品编号");
			if (changenum != null) {
				int getnchange = mydata.find(changenum);
				if (getnchange == -1)
					JOptionPane.showMessageDialog(null, "用户未购买货物" + changenum);
				else {
					int num = mydata.productData[getnchange].getNum();
					try {
						num = Integer.parseInt(JOptionPane
								.showInputDialog("输入新数量"));
						while (num < 0) {
							num = Integer.parseInt(JOptionPane
									.showInputDialog("重新输入新数量（系统默认大于等于零元）"));
						}
					} catch (NumberFormatException e) {
						JOptionPane.showMessageDialog(null, "请输入数字");
					}
					if (num == 0)
						mydata.delete(changenum);
					else if(num>=0)
						mydata.productData[getnchange].setNum(num);
					textArea.setText("");
					for (int i = 0; i < mydata.acount; i++) {
						textArea.insert("商品" + (i + 1) + " 编号:"
								+ mydata.productData[i].getCode() + " 商品名称："
								+ mydata.productData[i].getName() + " 商品价格： "
								+ mydata.productData[i].getPrice() + "元/单位  "
								+ mydata.productData[i].getNum() + " 个" + "\n",
								0);
					}
					textArea.insert(" 共" + mydata.givecost() + " 元\n", 0);
				}
			}
		}

		else if (source == deleteButton) {
			String deletecode = JOptionPane.showInputDialog("输入所要删除货物的编号");
			if (deletecode != null) {
				boolean getdelete = mydata.delete(deletecode);
				if (getdelete == false)
					JOptionPane.showMessageDialog(null, "用户未购买货物" + deletecode);
				else {
					textArea.setText("");
					for (int i = 0; i < mydata.acount; i++) {
						textArea.insert("商品" + (i + 1) + " 编号:"
								+ mydata.productData[i].getCode() + " 商品名称："
								+ mydata.productData[i].getName() + " 商品价格： "
								+ mydata.productData[i].getPrice() + "元/单位  "
								+ mydata.productData[i].getNum() + " 个" + "\n",
								0);
					}
					textArea.insert(" 共" + mydata.givecost() + " 元\n", 0);
				}
			}
		}

		else if (source == finishButton) {
			int a = JOptionPane.showConfirmDialog(null, "确定后将不可再修改清单");
			if (a == JOptionPane.YES_OPTION) {
				textArea.setText("");
				textFinish.insert(" 谢谢您的惠顾", 0);
				textFinish.insert(" 共" + mydata.givecost() + " 元\n", 0);
				textFinish.insert(
						"------------------------------------------------"
								+ "\n", 0);
				for (int i = 0; i < mydata.acount; i++) {
					textFinish.insert("商品" + (i + 1) + " 编号:"
							+ mydata.productData[i].getCode() + " 商品名称："
							+ mydata.productData[i].getName() + " 商品价格： "
							+ mydata.productData[i].getPrice() + "元/单位  "
							+ mydata.productData[i].getNum() + " 个" + "\n", 0);
				}
				textFinish.insert(
						"------------------------------------------------"
								+ "\n", 0);
				textFinish.insert("软院小超市" + "\n", 0);
				textAll.setText("总价：" + mydata.givecost());
				mysell.save(mydata);
				changeButton1.setEnabled(false);
				changeButton2.setEnabled(false);
				deleteButton.setEnabled(false);
				recordButton.setEnabled(true);
				moneyButton.setEnabled(true);
				cheapButton.setEnabled(false);
				finishButton.setEnabled(false);
				textAdd.setEnabled(false);
				recordButton.doClick();
			}
		}

		else if (source == cheapButton) {
			String vipnumber = "";
			vipnumber = JOptionPane.showInputDialog(null, "请输入VIP用户会员号码");
			if (vipnumber != null) {
				boolean vipsuccees = false;
				String read;
				try {
					FileReader fr = new FileReader("vip.txt");
					BufferedReader br = new BufferedReader(fr);
					while ((read = br.readLine()) != null) {
						if (vipnumber.equals(read)) {
							vipsuccees = true;
							break;
						}
					}
				} catch (IOException ie) {
					System.err.println(ie.getMessage());
				}
				if (vipsuccees == true) {
					int a = JOptionPane.showConfirmDialog(null, "确定后将不可再修改清单");
					if (a == JOptionPane.YES_OPTION) {
						textArea.setText("");
						textFinish.insert(" VIP会员" + vipnumber + "优惠50%，只需 "
								+ mydata.givecost2() + "元", 0);
						textFinish
								.insert(" 原价" + mydata.givecost() + " 元\n", 0);
						textFinish.insert(
								"------------------------------------------------"
										+ "\n", 0);
						for (int i = 0; i < mydata.acount; i++) {
							textFinish.insert("商品" + (i + 1) + " 编号:"
									+ mydata.productData[i].getCode()
									+ " 商品名称："
									+ mydata.productData[i].getName()
									+ " 商品价格： "
									+ mydata.productData[i].getPrice()
									+ "元/单位  " + mydata.productData[i].getNum()
									+ " 个" + "\n", 0);
						}
						textFinish.insert(
								"------------------------------------------------"
										+ "\n", 0);
						textFinish.insert("软院小超市" + "\n", 0);
						textAll.setText("总价：" + mydata.givecost2());
						mysell.save(mydata);
						changeButton1.setEnabled(false);
						changeButton2.setEnabled(false);
						deleteButton.setEnabled(false);
						recordButton.setEnabled(true);
						moneyButton.setEnabled(true);
						cheapButton.setEnabled(false);
						finishButton.setEnabled(false);
						textAdd.setEnabled(false);
						recordButton.doClick();
					}
				} else
					JOptionPane.showMessageDialog(null, "非VIP会员号码");
			}
		}

		else if (source == recordButton) {
			mydata.record();
			JOptionPane.showMessageDialog(null, "交易信息记录成功");
			recordButton.setEnabled(false);
		}

		else if (source == moneyButton) {
			String clientmoney = JOptionPane.showInputDialog("用户给予金额");
			if (clientmoney != null) {
				double inmoney = Double.parseDouble(clientmoney);
				String outmoney = mydata.moneycount(inmoney);
				textChange.setText(outmoney);
				moneyButton.setEnabled(false);
			}
		}

		else if (source == clearButton) {
			textAdd.setText("");
			textArea.setText("");
			textAll.setText("");
			textChange.setText("");
			textFinish.setText("");
			dcount = 0;
			mydata.clear();
			moneyButton.setEnabled(false);
			recordButton.setEnabled(false);
			changeButton1.setEnabled(false);
			changeButton2.setEnabled(false);
			deleteButton.setEnabled(false);
			cheapButton.setEnabled(false);
			finishButton.setEnabled(false);
			textAdd.setEnabled(true);
		}
	}

	private JButton changeButton1, changeButton2, deleteButton, finishButton,
			clearButton;

	private JButton recordButton, moneyButton, cheapButton, drawButton;

	private JLabel label1, label2, label3, label4;

	private JTextArea textArea, textChange, textFinish;

	private JScrollPane scrollPane;

	private JTextField textAdd, textAll;

	private Box vbox1, vbox2, vbox3, hbox1, hbox2, hbox3;

}
