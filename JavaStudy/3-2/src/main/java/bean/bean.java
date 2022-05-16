package bean;
import java.io.Serializable;


public class bean implements Serializable {

	private String product_code;  //id格納用変数
	private String product_name;
	private String price;
	private String updatetime;
	private String registerdatetime;
	private String deletedate;
	private String quantity; // 数量
	private String salesdate;

	

	public bean() {

	}

	public String getCode() {
		return product_code;
	}

	public void setCode(String code) {
		this.product_code = code;
	}

	public String getName() {
		return product_name;

	}

	public void setName(String name) {
		this.product_name = name;
	}

	public String getPrice() {
		return price;

	}

	public void setPrice(String price) {
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
	
	
	
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getQuantity() {
		return quantity;
	}
	
	public String getSalesdate() {
		return salesdate;
	}

	public void setSalesdate(String salesdate) {
		this.salesdate = salesdate;
	}

}

