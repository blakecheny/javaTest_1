public class Data { // ��Ʒ��
	private String code; // ��Ʒ���

	private String name; // ��Ʒ����

	private double price; // ��Ʒ����

	private int num; // ��Ʒ����

	public Data() { // ���캯��
	}

	public Data(String acode, String aname, double aprice, int anum) {
		code = acode;
		name = aname;
		price = aprice;
		num = anum;
	}

	public Data(String acode, String aname, double aprice) {
		code = acode;
		name = aname;
		price = aprice;
	}

	public String getCode() {// ��ȡ���
		return code;
	}

	public String getName() {// ��ȡ����
		return name;
	}

	public double getPrice() {// ��ȡ�۸�
		return price;
	}

	public int getNum() {// ��ȡ����
		return num;
	}

	public void setCode(String bcode) {// ���ñ�� δʹ��
		code = bcode;
	}

	public void setName(String bname) {// �������� δʹ��
		name = bname;
	}

	public void setPrice(double bprice) {// ���ü۸�
		price = bprice;
	}

	public void setNum(int bnum) {// ��������
		num = bnum;
	}

	public void info() {
		System.out.println(code + "  " + name + "  " + price);// �����š����ơ��۸�
																// δʹ��
	}
}
