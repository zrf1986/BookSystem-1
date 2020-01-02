package entity;

public class Book {
	private Integer id;//书号
	private String name;//书名
	private String publishing;//出版社
	private Double price;//单价
	private Integer stock;//库存
	
	
	
	public Book(Integer id, String name, String publishing, Double price, Integer stock) {
		super();
		this.id = id;
		this.name = name;
		this.publishing = publishing;
		this.price = price;
		this.stock = stock;
	}
	public Book() {
		
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPublishing() {
		return publishing;
	}
	public void setPublishing(String publishing) {
		this.publishing = publishing;
	}
	public Double getPrice() {
		return price;
	}
	public void setPrice(Double price) {
		this.price = price;
	}
	public Integer getStock() {
		return stock;
	}
	public void setStock(Integer stock) {
		this.stock = stock;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", name=" + name + ", publishing=" + publishing + ", price=" + price + ", stock="
				+ stock + "]";
	}
	
	
}
