import java.io.*;
import java.util.*;

public class judge {
	Data[] judgeData = new Data[100];//存放所有商品信息的数组

	String a, b, c;

	int bcount;//商品总数

	double dou3;

	public judge() {
		try {
			FileReader fr = new FileReader("supermarket.txt");
			BufferedReader br = new BufferedReader(fr);
			String read;
			while ((read = br.readLine()) != null) {
				StringTokenizer sto = new StringTokenizer(read);
				a = sto.nextToken();
				b = sto.nextToken();
				c = sto.nextToken();//获取"supermarket.txt"中已写好的商品信息并存放
				dou3 = Double.parseDouble(c);
				judgeData[bcount] = new Data(a, b, dou3);
				bcount++;
			}
		} catch (IOException ie) {
			System.err.println(ie.getMessage());
		}
	}

	public int find(String ccode) {//在所有超市商品中查找客户指定的编号
		boolean codefind = false;
		int i;
		for (i = 0; i < bcount; i++) {
			if (judgeData[i].getCode().equals(ccode)) {
				codefind = true;
				break;
			}
		}
		if (codefind == true)
			return i;
		else
			return -1;
	}
}
