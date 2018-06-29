package bsmanagement.controllers.rest;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import bsmanagement.dto.rest.ProductRestDTO;
import bsmanagement.model.Product;
import bsmanagement.model.Product.productType;
import bsmanagement.model.ProductService;


@RestController
public class ProductRestController {
	
	@Autowired
	ProductService productService;

	public ProductRestController(ProductService productService) {
		this.productService = productService;
	}
	
	/**
	 * Rest Controller to get a list of all existing products  
	 * 
	 * @return ResponseEntity OK with a List of ProductRestDTO
	 */
	@RequestMapping("/products")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<ProductRestDTO>> listAllProducts()
	{
		List<ProductRestDTO> productsRestDTO = new ArrayList<>();
		for (Product p : productService.getAllProducts())
		{
			ProductRestDTO product = p.toRestDTO();
			productsRestDTO.add(product);
		}
		return new ResponseEntity<>(productsRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to add new product
	 * 
	 * @return ResponseEntity OK if all fields inputed are valid, otherwise return BAD_REQUEST
	 */
	@PostMapping("/products")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<ProductRestDTO> addProduct(@RequestBody ProductRestDTO productInDTO)
	{
		List<String> availableTypes = Arrays.asList("HAIRCUT", "SHAVE", "EXTRA");
		if (productInDTO.getType() == null || !availableTypes.contains(productInDTO.getType().toUpperCase()) ||
				productInDTO.getName() == null || productInDTO.getPrice() <= 0.0 )
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		
		Product p = productService.createProduct(productInDTO.getName(),
				productType.valueOf(productInDTO.getType().toUpperCase()), productInDTO.getPrice());
		
		productService.addProduct(p);
		return new ResponseEntity<>(HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a list of all active products for selling
	 * 
	 * @return ResponseEntity OK with a List of ProductRestDTO
	 */
	@RequestMapping("/products/active")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<ProductRestDTO>> listActiveProducts()
	{
		List<ProductRestDTO> productsRestDTO = new ArrayList<>();
		for (Product p : productService.getActiveProducts())
		{
			ProductRestDTO product = p.toRestDTO();
			productsRestDTO.add(product);
		}
		
		return new ResponseEntity<>(productsRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a product by id
	 * 
	 * @return ResponseEntity OK with a ProductRestDTO if id exists, otherwise return NOT_FOUND
	 */
	@RequestMapping("/products/{productId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<ProductRestDTO> getProduct(@PathVariable(value = "productId") int productId)
	{
		Product p = productService.findProductById(productId);
		if (p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		ProductRestDTO productRestDTO = p.toRestDTO();
		
		return new ResponseEntity<>(productRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to get a list of products of specific type
	 * 
	 * @return ResponseEntity OK with a List of ProductRestDTO if type inputed is valid, otherwise returned BAD_REQUEST
	 */
	@RequestMapping(value = "products", params = "type")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<List<ProductRestDTO>> listProductsByType(@RequestParam(value = "type", required = true) String type)
	{
		List<String> availableTypes = Arrays.asList("HAIRCUT", "SHAVE", "EXTRA");
		if (!availableTypes.contains(type.toUpperCase()))
		{
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		List<ProductRestDTO> productsRestDTO = new ArrayList<>();
		for (Product p : productService.getProductsByType(productType.valueOf(type.toUpperCase())))
		{
			ProductRestDTO product = p.toRestDTO();
			productsRestDTO.add(product);
		}
		return new ResponseEntity<>(productsRestDTO,HttpStatus.OK);
	}
	
	/**
	 * Rest Controller to disable product by id
	 * 
	 * @return ResponseEntity OK with a ProductRestDTO if id exists, otherwise return NOT_FOUND
	 */
	@DeleteMapping("/products/{productId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<ProductRestDTO> removeProduct(@PathVariable(value = "productId") int productId)
	{
		Product p = productService.findProductById(productId);
		if (p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		productService.disableProduct(p);
		
		ProductRestDTO productRestDTO = productService.findProductById(productId).toRestDTO();
		
		return new ResponseEntity<>(productRestDTO,HttpStatus.OK);
	}
	
	
	/**
	 * Rest Controller to edit product information by id
	 * 
	 * @return ResponseEntity OK with a ProductRestDTO if id exists, if id doesnt exists return NOT_FOUND, if TYPE inputed is invalid
	 * return BAD_REQUEST
	 */
	@PutMapping("/products/{productId}")
	@PreAuthorize("hasRole('USER') || hasRole('STOREMANAGER') || hasRole('ADMIN')")
	public ResponseEntity<ProductRestDTO> editProduct(@PathVariable(value = "productId") int productId, @RequestBody ProductRestDTO productRestDTO)
	{
		Product p = productService.findProductById(productId);
		List<String> availableTypes = Arrays.asList("HAIRCUT", "SHAVE", "EXTRA");
		if (p==null)
			return new ResponseEntity<>(HttpStatus.NOT_FOUND);
		if (productRestDTO.getName()!=null)
			p.setName(productRestDTO.getName());
		if (productRestDTO.getPrice()>0.0)
			p.setPrice(productRestDTO.getPrice());
		if (productRestDTO.getType()!=null)
		{
			if (!availableTypes.contains(productRestDTO.getType().toUpperCase()))
				return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
			p.setType(productType.valueOf(productRestDTO.getType().toUpperCase()));
		}
		
		productService.updateProduct(p);
		
		
		return new ResponseEntity<>(p.toRestDTO(),HttpStatus.ACCEPTED);
	}
	

}
