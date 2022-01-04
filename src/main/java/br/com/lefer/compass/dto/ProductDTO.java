package br.com.lefer.compass.dto;

import java.math.BigDecimal;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder(toBuilder = true)
public class ProductDTO {
	
	@NotBlank(message = "O nome é obrigatório")
	private String name;
	
	@NotBlank(message = "A descrição é obrigatória")
	private String  description;
	
	@NotNull(message = "O preço é obrigatório")
	@DecimalMin(value = "0.0" , message = "O valor deve ser positivo")
	private BigDecimal price;

}
