package br.com.lefer.compass.entity;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
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
@Table(name = "products")  
@Entity
public class Product {
	
	@Id
    @GeneratedValue
	private Integer id;
	
	@Column(name="name", nullable = false)
	@NotBlank(message = "O nome é obrigatório")
	private String name;
	
	@Column(name="description", nullable = false)
	@NotBlank(message = "A descrição é obrigatória")
	private String  description;
	
	@Column(name="price")
	@NotNull(message = "O preço é obrigatório")
	@DecimalMin(value = "0.0" , message = "O valor deve ser positivo")
	private BigDecimal price;
	

}
