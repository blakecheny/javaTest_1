import java.util.*;
import java.io.*;
import java.text.*;
import java.math.*;

public class DataManagement {// 用户指定商品的管理
	public int acount = 0;// 用来记录商品个数

	private static double DISCOUNT = 0.5;// VIP用户打折率

	boolean vipyn = false;// 是否为VIP用户

	Data[] productData = new Data[100];// 放商品的数组

	public void add(String acode, String aname, double aprice, int anum) {// 添加一个商品
		boolean find = false;
		for (int i = 0; i < acount; i++) {
			if (productData[i].getCode().equals(acode)) {
				productData[i].setNum(productData[i].getNum() + anum);
				find = true;
				break;
			}
		}// 确认此商品编号是否已出现在商品数组内，如果是则直接累加新的个数
		if (find == false) {
			productData[acount] = new Data(acode, aname, aprice, anum);
			acount++;
		}// 如果未找到则添加进数组中
	}

	public int find(String acode) {// 确认某商品编号是否存在
		int i;
		for (i = 0; i < acount; i++) {
			if (productData[i].getCode().equals(acode))
				break;
		}
		if (i == acount)
			return -1;// 不存在，返回-1
		else
			return i;// 存在，返回此商品在商品数组中的位置
	}

	public boolean delete(String acode) {// 删除指定商品
		int i = find(acode);
		if (i == -1)
			return false;// 商品数组中不存在输入编号
		else {
			for (; i < acount; i++)
				productData[i] = productData[i + 1];
			acount--;
			return true;// 存在则删除此商品，相应调整，调动后面位置的商品补其空隙
		}

	}

	public void record() {// 记录进“record.txt”
		try {
			Writer fr = new FileWriter("record.txt", true);// 使得记录时不会覆盖
			int i;
			SimpleDateFormat sdf = new SimpleDateFormat("",
					Locale.SIMPLIFIED_CHINESE);
			sdf.applyPattern("yyyy年MM月dd日 HH时mm分ss秒");
			String timeStr = sdf.format(new Date());// 格式化时间记录方式
			fr.write(timeStr + "\r\n");
			for (i = 0; i < acount ; i++) {
				fr.write("商品" + (i + 1) + " 编号:" + productData[i].getCode()
						+ " 商品名称： " + productData[i].getName() + " 商品价格： "
						+ productData[i].getPrice() + "元/单位  "
						+ productData[i].getNum() + " 个" + "\r\n");
			}
//			fr.write("商品" + (i + 1) + " 编号:" + productData[i].getCode()
//					+ " 商品名称： " + productData[i].getName() + " 商品价格： "
//					+ productData[i].getPrice() + "元/单位  "
//					+ productData[i].getNum() + " 个" + "\r\n" + "\r\n");// 依次记录

			fr.flush();
			fr.close();

		} catch (IOException ie) {
			System.err.println("error:" + ie.getMessage());
		}
	}

	public String moneycount(double pay) {// 实现找零处理
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
			vipyn = false;// 处理完后，重新将vipyn设定成false
			return "用户所给金额不足";
		} else if (mypay == price) {
			vipyn = false;
			return "用户付款 " + mypay + "正好";
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
			return "用户所付金额 " + pay + "\n" + "应找零 "
			        +finalchange/10+"\n"
			        +"建议找零： " + "\n" + "  50元 "+
					+ count50 + "\n" + "  20元 " + count20 + "\n" + "  10元 "
					+ count10 + "\n" + "  5元 " + count5 + "\n" + "  1元 "
					+ count1 + "\n" + "  5角 " + count05 + "\n" + "  1角 "
					+ count01 + "\n";
		}

	}

	public double givecost() {// 普通客户统计商品总价
		int i;
		double cost = 0;
		for (i = 0; i < acount; i++) {
			cost = cost + productData[i].getPrice() * productData[i].getNum();
		}
		DecimalFormat df = new DecimalFormat("########.00");
		cost = Double.parseDouble(df.format(cost));// 格式化小数显示
		return cost;
	}

	public double givecost2() {// VIP客户统计商品总价
		int i;
		double cost = 0;
		for (i = 0; i < acount; i++) {
			cost = cost + productData[i].getPrice() * productData[i].getNum();
		}
		vipyn = true;
		DecimalFormat df = new DecimalFormat("########.00");
		cost = Double.parseDouble(df.format(cost));// 格式化小数显示
		cost = cost * DISCOUNT;// 打折处理
		return cost;
	}

	public void clear() {// 将商品数组清空
		acount = 0;
	}

}
