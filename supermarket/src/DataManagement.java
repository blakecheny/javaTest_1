import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;

public class DataManagement {// �û�ָ����Ʒ�Ĺ���
	public int acount = 0;// ������¼��Ʒ����

	private static double DISCOUNT = 0.5;// VIP�û�������

	boolean vipyn = false;// �Ƿ�ΪVIP�û�

	Data[] productData = new Data[100];// ����Ʒ������

	public void add(String acode, String aname, double aprice, int anum) {// ���һ����Ʒ
		boolean find = false;
		for (int i = 0; i < acount; i++) {
			if (productData[i].getCode().equals(acode)) {
				productData[i].setNum(productData[i].getNum() + anum);
				find = true;
				break;
			}
		}// ȷ�ϴ���Ʒ����Ƿ��ѳ�������Ʒ�����ڣ��������ֱ���ۼ��µĸ���
		if (find == false) {
			productData[acount] = new Data(acode, aname, aprice, anum);
			acount++;
		}// ���δ�ҵ�����ӽ�������
	}

	public int find(String acode) {// ȷ��ĳ��Ʒ����Ƿ����
		int i;
		for (i = 0; i < acount; i++) {
			if (productData[i].getCode().equals(acode))
				break;
		}
		if (i == acount)
			return -1;// �����ڣ�����-1
		else
			return i;// ���ڣ����ش���Ʒ����Ʒ�����е�λ��
	}

	public boolean delete(String acode) {// ɾ��ָ����Ʒ
		int i = find(acode);
		if (i == -1)
			return false;// ��Ʒ�����в�����������
		else {
			for (; i < acount; i++)
				productData[i] = productData[i + 1];
			acount--;
			return true;// ������ɾ������Ʒ����Ӧ��������������λ�õ���Ʒ�����϶
		}

	}

	public void record() {// ��¼����record.txt��
		try {
			Writer fr = new FileWriter("record.txt", true);// ʹ�ü�¼ʱ���Ḳ��
			int i;
			SimpleDateFormat sdf = new SimpleDateFormat("",
					Locale.SIMPLIFIED_CHINESE);
			sdf.applyPattern("yyyy��MM��dd�� HHʱmm��ss��");
			String timeStr = sdf.format(new Date());// ��ʽ��ʱ���¼��ʽ
			fr.write(timeStr + "\r\n");
			for (i = 0; i < acount ; i++) {
				fr.write("��Ʒ" + (i + 1) + " ���:" + productData[i].getCode()
						+ " ��Ʒ���ƣ� " + productData[i].getName() + " ��Ʒ�۸� "
						+ productData[i].getPrice() + "Ԫ/��λ  "
						+ productData[i].getNum() + " ��" + "\r\n");
			}
//			fr.write("��Ʒ" + (i + 1) + " ���:" + productData[i].getCode()
//					+ " ��Ʒ���ƣ� " + productData[i].getName() + " ��Ʒ�۸� "
//					+ productData[i].getPrice() + "Ԫ/��λ  "
//					+ productData[i].getNum() + " ��" + "\r\n" + "\r\n");// ���μ�¼

			fr.flush();
			fr.close();

		} catch (IOException ie) {
			System.err.println("error:" + ie.getMessage());
		}
	}

	public String moneycount(double pay) {// ʵ�����㴦��
		int count50 = 0, count20 = 0, count10 = 0, count5 = 0, count1 = 0, count05 = 0, count01 = 0;
		long for01;
		double mypay = pay;
		double price;
		double finalchange;
		if (vipyn == false)
			price = givecost();
		else
			price = givecost2();
		if (mypay < price) {
			vipyn = false;// ����������½�vipyn�趨��false
			return "�û���������";
		} else if (mypay == price) {
			vipyn = false;
			return "�û����� " + mypay + "����";
		} else {
			mypay = mypay-price;
			while (mypay >= 50) {
				mypay -= 50;
				count50++;
			}
			while (mypay >= 20) {
				mypay -= 20;
				count20++;
			}
			while (mypay >= 10) {
				mypay -= 10;
				count10++;
			}
			while (mypay >= 5) {
				mypay -= 5;
				count5++;
			}
			while (mypay >= 1) {
				mypay -= 1;
				count1++;
			}
			while (mypay >= 0.5) {
				mypay -= 0.5;
				count05++;
			}
			while (mypay >0) {	
				for01=Math.round(mypay*10);
				for01-=1;
				mypay=for01;
				mypay=mypay/10;
				count01++;
			}
			vipyn = false;
            finalchange=Math.round(10*(pay-price));
			return "�û�������� " + pay + "\n" + "Ӧ���� "
			        +finalchange/10+"\n"
			        +"�������㣺 " + "\n" + "  50Ԫ "+
					+ count50 + "\n" + "  20Ԫ " + count20 + "\n" + "  10Ԫ "
					+ count10 + "\n" + "  5Ԫ " + count5 + "\n" + "  1Ԫ "
					+ count1 + "\n" + "  5�� " + count05 + "\n" + "  1�� "
					+ count01 + "\n";
		}

	}

	public double givecost() {// ��ͨ�ͻ�ͳ����Ʒ�ܼ�
		int i;
		double cost = 0;
		for (i = 0; i < acount; i++) {
			cost = cost + productData[i].getPrice() * productData[i].getNum();
		}
		DecimalFormat df = new DecimalFormat("########.00");
		cost = Double.parseDouble(df.format(cost));// ��ʽ��С����ʾ
		return cost;
	}

	public double givecost2() {// VIP�ͻ�ͳ����Ʒ�ܼ�
		int i;
		double cost = 0;
		for (i = 0; i < acount; i++) {
			cost = cost + productData[i].getPrice() * productData[i].getNum();
		}
		vipyn = true;
		DecimalFormat df = new DecimalFormat("########.00");
		cost = Double.parseDouble(df.format(cost));// ��ʽ��С����ʾ
		cost = cost * DISCOUNT;// ���۴���
		return cost;
	}

	public void clear() {// ����Ʒ�������
		acount = 0;
	}

}
