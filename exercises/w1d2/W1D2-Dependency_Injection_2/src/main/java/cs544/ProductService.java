package cs544;

import java.util.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService implements IProductService {
	private IInventoryService inventoryService;
	private Collection<Product> productList = new ArrayList<Product>();

	public ProductService() {
		productList.add(new Product(234, "LCD TV", 895.50));
		productList.add(new Product(239, "DVD player", 315.00));
		productList.add(new Product(423, "Plasma TV", 992.55));
	}

	public Product getProduct(int productNumber) {
		for (Product product : productList) {
			if (product.getProductNumber() == productNumber)
				return product;
		}
		return null;
	}

	@Override
	public int getNumberInStock(int productNumber) {
		return inventoryService.getNumberInStock(productNumber);
	}

	// Setter
	@Autowired
	public void setInventoryService(IInventoryService inventoryService) {
		this.inventoryService = inventoryService;
	}
}
