package br.com.lefer.compass.controller;

import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import br.com.lefer.compass.dto.ProductDTO;
import br.com.lefer.compass.entity.Product;
import br.com.lefer.compass.service.ProductService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping("products")
public class ProductController {
	
	@Autowired
	private ProductService productService;
	
	
	@PostMapping
	 @ApiResponses(value = { 
		        @ApiResponse(code = HttpURLConnection.HTTP_CREATED, message = "Produto adicionado com sucesso"),
		        @ApiResponse(code = HttpURLConnection.HTTP_BAD_REQUEST, message = "Não foi possivel criar o produto"),
		    })
    @ApiOperation("Criação de um produto")
    public ResponseEntity<Product>  create(@Valid @RequestBody ProductDTO product){  
        log.info("Criando um novo produto");
        return new ResponseEntity<>(productService.create(product), HttpStatus.CREATED); 
	}
	
	@ResponseStatus(HttpStatus.BAD_REQUEST)
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public Map<String, String> handleValidationExceptions(
	  MethodArgumentNotValidException ex) {
	    Map<String, String> errors = new HashMap<>();
	    ex.getBindingResult().getAllErrors().forEach((error) -> {
	        String fieldName = ((FieldError) error).getField();
	        String errorMessage = error.getDefaultMessage();
	        errors.put(fieldName, errorMessage);
	    });
	    return errors;
	}


}
