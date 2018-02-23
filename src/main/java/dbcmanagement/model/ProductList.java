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
		listOfProducts = new ArrayList<>();
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
		return null;
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
		return true;
	}
	
	
	
	public Product findProductById(int id)
	{
		return null;	
	}
	
	public List<Product> searchProductByName(String name)
	{
		return null;	
	}
	
	public List<Product> getProductListByType(productType type)
	{
		return null;	
	}
	
	public List<Product> getProductListOrderByPrice()
	{
		return null;	
	}
	
	public List<Product> getProductListByMinPrice(productType type)
	{
		return null;	
	}

	

}
