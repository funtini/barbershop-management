package dbcmanagement.model;

import java.util.ArrayList;
import java.util.List;

import dbcmanagement.model.Product.productType;

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
	
private List<Product> listOfProducts;
	
	/**
	 * Constructor of list of products
	 */
	public ProductList() {
		listOfProducts = new ArrayList<Product>();
	}

	/**
	 * @return the listOfProducts
	 */
	public List<Product> getListOfProducts() {
		return listOfProducts;
	}

	/**
	 * Set a List of Products
	 * 
	 * @param listOfProducts
	 */
	public void setListOfProducts(List<Product> listOfProducts) {
		this.listOfProducts = listOfProducts;
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
		if (this.listOfProducts.contains(product))
			return false;
		return this.listOfProducts.add(product);
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
		for (Product p: this.listOfProducts)
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
		for (Product p: this.listOfProducts)
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
	public List<Product> getProductListByType(productType type)
	{
		List<Product> productList = new ArrayList<Product>();
		for (Product p: this.listOfProducts)
		{
			if (p.getType().equals(type)) 
				productList.add(p);
		}
		return productList;	
	}
	
	/**
	 * Method to get a List of Products ordered By Price
	 * 
	 * @param Name
	 * 
	 * @return List of Products
	 */
	public List<Product> getProductListOrderByPrice()
	{
		return null;	
	}
	
	
	/**
	 * Method to get a List of Products ordered By Price
	 * 
	 * @param Name
	 * 
	 * @return List of Products
	 */
	public List<Product> getProductListByMinPrice(productType type)
	{
		return null;	
	}

	

}
