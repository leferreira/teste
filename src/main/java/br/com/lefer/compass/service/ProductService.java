package br.com.lefer.compass.service;

import static java.util.Optional.ofNullable;
import static org.springframework.http.HttpStatus.BAD_REQUEST;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

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
		return ofNullable(modelMapper.map(productDTO, Product.class))
				.map(productRepository::save)
				.orElseThrow(() -> new HttpClientErrorException(BAD_REQUEST, "Não foi possível inserir o produto")); 
	}

}
