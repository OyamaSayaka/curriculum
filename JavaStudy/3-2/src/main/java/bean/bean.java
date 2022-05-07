package bean;

public class bean {

	private int product_code;  //id格納用変数
	private String product_name;
	private int price;
	private String updatetime;
	private String registerdatetime;
	private String deletedate;
	private int quantity; // 数量

	public bean() {

	}

	public int getCode() {
		return product_code;
	}

	public void setCode(int code) {
		this.product_code = code;
	}

	public String getName() {
		return product_name;

	}

	public void setName(String name) {
		this.product_name = name;
	}

	public int getPrice() {
		return price;

	}

	public void setPrice(int price) {
		this.price = price;
	}

	public void setUpdateTime(String updatetime) {
		this.updatetime = updatetime;
	}

	public String getUpdateTime() {
		return updatetime;
	}

	public void setRegisterdDate(String register) {
		this.registerdatetime = register;
	}

	public String getRegisterdDate() {
		return registerdatetime;
	}

	public void setDeleteDate(String deletedate) {
		this.deletedate = deletedate;
	}

	public String getDeleteDate() {
		return deletedate;
	}
	
	
	
	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getQuantity() {
		return quantity;
	}

}

