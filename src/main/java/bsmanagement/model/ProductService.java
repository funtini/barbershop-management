package bsmanagement.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import bsmanagement.model.Product.productType;
import bsmanagement.model.jparepositories.ProductRepository;

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
@Service
public class ProductService {

	@Autowired
	private ProductRepository productRepo;
	
	/**
	 * Constructor of list of products
	 */
	public ProductService() {
	}

	
	/**
	 * @return the list Of all Products
	 */
	public List<Product> getAllProducts() {
		List<Product> allProducts = new ArrayList<>();
		for (Product p : productRepo.findAll())
			allProducts.add(p);		
		return allProducts;
	}
	
	/**
	 * @return the list Of Active Products
	 */
	public List<Product> getActiveProducts() {
		List<Product> activeProducts = new ArrayList<>();
		for (Product p : getAllProducts())
			if (p.isActive())
				activeProducts.add(p);
		return activeProducts;
	}
	
	/**
	 * @return the list Of Removed Products
	 */
	public List<Product> getDisabledProducts() {
		List<Product> inactiveProducts = new ArrayList<>();
		for (Product p : getAllProducts())
			if (!p.isActive())
				inactiveProducts.add(p);
		return inactiveProducts;
	}
	
	/**
	 * Method to update an existing product in DataBase
	 * 
	 * @param product
	 * 
	 * @return true if product exists and is successfully updated, false otherwise
	 */
	public boolean updateProduct(Product product)
	{
		if (productRepo.existsById(product.getId())) {
			productRepo.save(product);
            return true;
        }
        return false;
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
		if (productRepo.existsById(product.getId()))
			return false;
		productRepo.save(product);
		return true;
	}
	
	/**
	 * Method to remove Product to Inventory
	 * 
	 * @param product
	 * 
	 */
	public void disableProduct(Product product)
	{
		if (productRepo.existsById(product.getId()))
		{
			product.deactivate();
			productRepo.save(product);
		}
	}
	
	
	/**
	 * Method to remove Product to Inventory
	 * 
	 * @param product
	 * 
	 */
	public void activateProduct(Product product)
	{
		if (productRepo.existsById(product.getId()))
		{
			product.activate();
			productRepo.save(product);
		}
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
		for (Product p: getAllProducts())
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
		for (Product p: getAllProducts())
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
		for (Product p: getAllProducts())
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
		for (Product p: getAllProducts())
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
		for (Product p: getAllProducts())
		{
			if (p.getType().equals(type)) 
				productList.add(p);
		}
		Collections.sort(productList);
		return productList;		
	}


	public void setRepository(ProductRepository productRepository) {
		this.productRepo = productRepository;
	}

	

}
