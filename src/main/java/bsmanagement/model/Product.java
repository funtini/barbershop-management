package bsmanagement.model;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * <h1> Product </h1>
 * <p>
 * Product is the abstract class for all Products registered in a barber shop
 * context. This class contains information like:
 * <ul>
 * <li>ID - Unique Identifier
 * <li>Name - Name of Product
 * <li>Product Type - Type of Product, in this barber shop context has to be one of this three: HAIRCUT,SHAVE and EXTRA.
 * <li>Price - Value of actual price of Product
 * </ul>
 * <p>
 * To create an instance of Product you have to define a Name, Type and Price.
 * 
 * @author JOAO GOMES
 */
public class Product implements Comparable<Product>{

	
	
	/**
	 * Type of Products:
	 * 
	 * <li>{@link #HAIRCUT}</li>
	 * <li>{@link #SHAVE}</li>
	 * <li>{@link #EXTRA}</li>
	 * 
	 * @author JOAO GOMES
	 *
	 */
	public enum productType {
		
		/**
		 *  HAIRCUT represents all different types of HAIRCUTS that barbershop sells
		 */
		HAIRCUT,
		
		/**
		 *  SHAVE represents all different types of SHAVES that barbershop sells
		 */
		SHAVE,
		
		/**
		 *  EXTRA represents the rest of the products that barbershop sells like shampoo's, gel, barber cream, after shaves, etc 
		 */
		EXTRA
	}
	
	private static AtomicInteger idGenerator=new AtomicInteger();
	
	private int id;
	private String name;
	private productType type;
	private double price;
	

	/**
	 * Constructor of new Product with name, type and price
	 * 
	 * @param name - Name of Product
	 * @param type - Type of produce (HAIRCUT,SHAVE or EXTRA)
	 * @param price - Price value in Euros
	 */
	public Product(String name, productType type, double price) {
		id=idGenerator.incrementAndGet();
		this.name = name;
		this.type = type;
		this.price = price;
	}

	/**
	 * Static method to set new start id generator
	 * 
	 * @param num
	 */
	public static void setStartIdGenerator(int num)
	{
		idGenerator.set(num-1);
	}
	
	
	/**
	 * @return the product's ID
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return the product's name
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * @return the type of product
	 */
	public productType getType() {
		return type;
	}
	
	/**
	 * @return the price of product in euros
	 */
	public double getPrice() {
		return price;
	}
	
	/**
	 * Set name to product
	 * 
	 * @param name 
	 */
	
	
	
	public void setName(String name) {
		this.name = name;
	}
	
	/**
	 * Set Product's Id
	 * 
	 * @param id 
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * Set type of product
	 * 
	 * @param type 
	 */
	public void setType(productType type) {
		this.type = type;
	}
	
	
	/**
	 * Set price of product in euros
	 * 
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Product other = (Product) obj;
		if (id != other.id)
			return false;
		return true;
	}

	
	@Override
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", type=" + type + ", price=" + price + "]";
	}
	
	@Override
	public int compareTo(Product p)
	{
		if (this.getPrice()>p.getPrice())
			return -1;
		else if(this.getPrice()<p.getPrice())
			return 1;
		return 0;
	}
	
	
}
