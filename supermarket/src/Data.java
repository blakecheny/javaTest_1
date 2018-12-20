public class Data { // 商品类
	private String code; // 商品编号

	private String name; // 商品名称

	private double price; // 商品单价

	private int num; // 商品个数

	public Data() { // 构造函数
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

	public String getCode() {// 获取编号
		return code;
	}

	public String getName() {// 获取名称
		return name;
	}

	public double getPrice() {// 获取价格
		return price;
	}

	public int getNum() {// 获取数量
		return num;
	}

	public void setCode(String bcode) {// 设置编号 未使用
		code = bcode;
	}

	public void setName(String bname) {// 设置名称 未使用
		name = bname;
	}

	public void setPrice(double bprice) {// 设置价格
		price = bprice;
	}

	public void setNum(int bnum) {// 设置数量
		num = bnum;
	}

	public void info() {
		System.out.println(code + "  " + name + "  " + price);// 输出编号、名称、价格
																// 未使用
	}
}
