package br.com.lefer.compass.converter;

import br.com.lefer.compass.dto.ProductDTO;
import br.com.lefer.compass.entity.Product;

public class ProductDTOtoProductConverter {
	
	public Product converter(ProductDTO productDTO) {
		return Product.builder()
				.name(productDTO.getName())
				.description(productDTO.getDescription())
				.price(productDTO.getPrice())
				.build();
	}

}
