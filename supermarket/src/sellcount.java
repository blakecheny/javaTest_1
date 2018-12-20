public class sellcount {

	Data[] sellData = new Data[100];// 存放当天总销售信息的数组

	public int ecount;// 总销售的商品类别数

	public int find(String acode) {// 查找当天是否卖出过指定编号的商品
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

	public void add(String acode, String aname, double aprice, int anum) {// 添加单个商品
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

	public double givecost() {// 返回得当天总销售额
		int i;
		double cost = 0;
		for (i = 0; i < ecount; i++) {
			cost = cost + sellData[i].getPrice() * sellData[i].getNum();
		}
		return cost;
	}

	public int allcount() {// 返回当天总销售类别数
		int allcount = 0;
		for (int i = 0; i < ecount; i++) {
			allcount += sellData[i].getNum();
		}
		return allcount;
	}

	public void save(DataManagement a) {// 直接将一组商品存入
		Data mydata = new Data();
		for (int i = 0; i < a.acount; i++) {
			mydata = a.productData[i];
			add(mydata.getCode(), mydata.getName(), mydata.getPrice(), mydata
					.getNum());
		}
	}

	public String sellinformantion(int location) {// 返回指定编号商品的销售信息
		String sellinformation = (" 编号:" + sellData[location].getCode() + "\n"
				+ " 商品名称： " + sellData[location].getName() + " 商品价格： "
				+ sellData[location].getPrice() + "元/单位  "
				+ sellData[location].getNum() + " 个" + "\n" + "今天共获得收益 "
				+ sellData[location].getPrice() * sellData[location].getNum() + "元");
		return sellinformation;
	}
}
