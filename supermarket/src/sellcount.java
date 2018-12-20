public class sellcount {

	Data[] sellData = new Data[100];// ��ŵ�����������Ϣ������

	public int ecount;// �����۵���Ʒ�����

	public int find(String acode) {// ���ҵ����Ƿ�������ָ����ŵ���Ʒ
		int i;
		for (i = 0; i < ecount; i++) {
			if (sellData[i].getCode().equals(acode))
				break;
		}
		if (i == ecount)
			return -1;
		else
			return i;
	}

	public void add(String acode, String aname, double aprice, int anum) {// ��ӵ�����Ʒ
		boolean find = false;
		for (int i = 0; i < ecount; i++) {
			if (sellData[i].getCode().equals(acode)) {
				sellData[i].setNum(sellData[i].getNum() + anum);
				find = true;
				break;
			}
		}
		if (find == false) {
			sellData[ecount] = new Data(acode, aname, aprice, anum);
			ecount++;
		}
	}

	public double givecost() {// ���صõ��������۶�
		int i;
		double cost = 0;
		for (i = 0; i < ecount; i++) {
			cost = cost + sellData[i].getPrice() * sellData[i].getNum();
		}
		return cost;
	}

	public int allcount() {// ���ص��������������
		int allcount = 0;
		for (int i = 0; i < ecount; i++) {
			allcount += sellData[i].getNum();
		}
		return allcount;
	}

	public void save(DataManagement a) {// ֱ�ӽ�һ����Ʒ����
		Data mydata = new Data();
		for (int i = 0; i < a.acount; i++) {
			mydata = a.productData[i];
			add(mydata.getCode(), mydata.getName(), mydata.getPrice(), mydata
					.getNum());
		}
	}

	public String sellinformantion(int location) {// ����ָ�������Ʒ��������Ϣ
		String sellinformation = (" ���:" + sellData[location].getCode() + "\n"
				+ " ��Ʒ���ƣ� " + sellData[location].getName() + " ��Ʒ�۸� "
				+ sellData[location].getPrice() + "Ԫ/��λ  "
				+ sellData[location].getNum() + " ��" + "\n" + "���칲������� "
				+ sellData[location].getPrice() * sellData[location].getNum() + "Ԫ");
		return sellinformation;
	}
}
