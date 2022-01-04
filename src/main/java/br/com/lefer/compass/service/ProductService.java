package br.com.lefer.compass.service;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import java.math.BigDecimal;
import java.util.Collections;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.lefer.compass.converter.ProductDTOtoProductConverter;
import br.com.lefer.compass.dto.ProductDTO;
import br.com.lefer.compass.entity.Product;
import br.com.lefer.compass.repository.ProductRepository;

@Service
public class ProductService {

	@Autowired
	private ModelMapper modelMapper;

	@Autowired
	private ProductRepository productRepository;

	public Product create(ProductDTO productDTO) {
		return ofNullable(modelMapper.map(productDTO, Product.class)).map(productRepository::save)
				.orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "N\\u00E3o foi poss\\u00EDvel inserir o produto"));
	}

	public List<Product> findAll() {
		List<Product> products = productRepository.findAll();
		return ofNullable(products).orElseGet(Collections::emptyList);
	}

	public Product findById(Integer id) {
		return productRepository.findById(id) 
				.orElseThrow(() -> new HttpClientErrorException(HttpStatus.NOT_FOUND,
						"N\u00E3o foi poss\u00EDvel encontrar o produto informado"));
	}

	public void deleteById(Integer id) {
		Product product = findById(id);
		productRepository.delete(product);
	}

	public Product updateProduct(Integer id, ProductDTO newProduct) {
		return productRepository.findById(id).map(product -> {
			product.setName(newProduct.getName());
			product.setDescription(newProduct.getDescription());
			product.setPrice(newProduct.getPrice());
			return productRepository.save(product);
		}).orElseGet(() -> {
			Product product = ProductDTOtoProductConverter.converter(newProduct);
			product.setId(id);
			return productRepository.save(product);
		});
	}

	public List<Product> searchProducts(String q, BigDecimal minPrice, BigDecimal maxPrice) {
		return ofNullable(productRepository.search(q, minPrice, maxPrice))
				.orElse(Collections.emptyList()); 
	}

}
