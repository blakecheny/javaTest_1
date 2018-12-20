import java.io.*;
import java.util.*;

public class judge {
	Data[] judgeData = new Data[100];//���������Ʒ��Ϣ������

	String a, b, c;

	int bcount;//��Ʒ����

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
				c = sto.nextToken();//��ȡ"supermarket.txt"����д�õ���Ʒ��Ϣ�����
				dou3 = Double.parseDouble(c);
				judgeData[bcount] = new Data(a, b, dou3);
				bcount++;
			}
		} catch (IOException ie) {
			System.err.println(ie.getMessage());
		}
	}

	public int find(String ccode) {//�����г�����Ʒ�в��ҿͻ�ָ���ı��
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
