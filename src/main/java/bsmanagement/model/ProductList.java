package bsmanagement.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import bsmanagement.model.Product.productType;

/**
 * <h1> ProductList </h1>
 * <p>
 * ProductList is the abstract class to manage a list of Products in a business
 * context. This class contains information like:
 * <ul>
 * <li>ProductList - List of products
 * </ul>
 * <p>
 * To create an instance of ProductList you just need to invoke the empty constructor
 * 
 * @author JOAO GOMES
 *
 */
public class ProductList {
	
private List<Product> products;
	
	/**
	 * Constructor of list of products
	 */
	public ProductList() {
		products = new ArrayList<Product>();
	}

	/**
	 * @return the listOfProducts
	 */
	public List<Product> getProducts() {
		return products;
	}

	/**
	 * Set a List of Products
	 * 
	 * @param products
	 */
	public void setProducts(List<Product> products) {
		this.products = products;
	}
	
	/**
	 * Method to create an instance of Product
	 * 
	 * @param name - name of product
	 * @param type - productType
	 * @param price - value of produt's price
	 * 
	 * @return Product
	 */
	public Product createProduct(String name, productType type, double price)
	{
		Product p = new Product(name,type,price);
		
		return p;
	}
	
	/**
	 * Method to add Product to Inventory
	 * 
	 * @param product
	 * 
	 * @return true if product is successfully added, false otherwise
	 */
	public boolean addProduct(Product product)
	{
		if (this.products.contains(product))
			return false;
		return this.products.add(product);
	}
	
	
	/**
	 * Method to find Product in Inventory By ID
	 * 
	 * @param id
	 * 
	 * @return Product if id was found, Null if id doesnt exist
	 */
	public Product findProductById(int id)
	{
		for (Product p: this.products)
		{
			if (p.getId()==id) 
				return p;
		}
		return null;	
	}
	
	
	/**
	 * Method to find a List of Product By Name
	 * 
	 * @param Name
	 * 
	 * @return List of Products
	 */
	public List<Product> searchProductByName(String name)
	{
		List<Product> productList = new ArrayList<Product>();
		for (Product p: this.products)
		{
			if (p.getName().equals(name)) 
				productList.add(p);
		}
		return productList;		
	}
	
	
	/**
	 * Method to find a List of Product By Type
	 * 
	 * @param type
	 * 
	 * @return List of Products
	 */
	public List<Product> getProductsByType(productType type)
	{
		List<Product> productList = new ArrayList<Product>();
		for (Product p: this.products)
		{
			if (p.getType().equals(type)) 
				productList.add(p);
		}
		return productList;	
	}
	
	/**
	 * Method to get a List of Products ordered By Price
	 * 
	 * @return List of Products
	 */
	public List<Product> getProductsOrderByPrice()
	{
		List<Product> productList = new ArrayList<Product>();
		for (Product p: this.products)
		{ 
				productList.add(p);
		}
		Collections.sort(productList);
		return productList;		
	}
	
	
	/**
	 * Method to get a List of Products ordered By Price of Specific type
	 * 
	 * @param Type
	 * 
	 * @return List of Products
	 */
	public List<Product> getProductsOrderByPriceOfType(productType type)
	{
		List<Product> productList = new ArrayList<Product>();
		for (Product p: this.products)
		{
			if (p.getType().equals(type)) 
				productList.add(p);
		}
		Collections.sort(productList);
		return productList;		
	}

	

}
