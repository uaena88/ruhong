package com.yuyi.bean;

/**
 * Product商品实体类
 * 
 * @author 育奕
 *
 */
//`pid` VARCHAR(32) NOT NULL,
//`pname` VARCHAR(50) DEFAULT NULL,
//`market_price` DOUBLE DEFAULT NULL,
//`shop_price` DOUBLE DEFAULT NULL,
//`pimage` VARCHAR(200) DEFAULT NULL,
//`pdate` DATE DEFAULT NULL,
//`is_hot` INT(11) DEFAULT NULL,
//`pdesc` VARCHAR(255) DEFAULT NULL,
//`pflag` INT(11) DEFAULT NULL,
//`cid` VARCHAR(32) DEFAULT NULL,
public class Product {
	private String pid;
	private String pname;
	private double market_price;
	private double shop_price;
	private String pimage;
	private String pdate;
	private int is_hot;
	private String pdesc;
	private int pflag;
	private Category category;

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public String getPid() {
		return pid;
	}

	public void setPid(String pid) {
		this.pid = pid;
	}

	public String getPname() {
		return pname;
	}

	public void setPname(String pname) {
		this.pname = pname;
	}

	public double getMarket_price() {
		return market_price;
	}

	public void setMarket_price(double market_price) {
		this.market_price = market_price;
	}

	public double getShop_price() {
		return shop_price;
	}

	public void setShop_price(double shop_price) {
		this.shop_price = shop_price;
	}

	public String getPimage() {
		return pimage;
	}

	public void setPimage(String pimage) {
		this.pimage = pimage;
	}

	public String getPdate() {
		return pdate;
	}

	public void setPdate(String pdate) {
		this.pdate = pdate;
	}

	public int getIs_hot() {
		return is_hot;
	}

	public void setIs_hot(int is_hot) {
		this.is_hot = is_hot;
	}

	public String getPdesc() {
		return pdesc;
	}

	public void setPdesc(String pdesc) {
		this.pdesc = pdesc;
	}

	public int getPflag() {
		return pflag;
	}

	public void setPflag(int pflag) {
		this.pflag = pflag;
	}

	public Product(String pid, String pname, double market_price, double shop_price, String pdesc) {
		super();
		this.pid = pid;
		this.pname = pname;
		this.market_price = market_price;
		this.shop_price = shop_price;
		this.pdesc = pdesc;
	}

	public Product() {
		// TODO Auto-generated constructor stub
	}
}
