package com.tienda.model;

import java.math.BigDecimal;

import java.sql.Date;
import java.util.Set;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import lombok.AllArgsConstructor;

import lombok.Data;

import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PRODUCTO")
public class Product {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "product_seq")
	@SequenceGenerator(name = "product_seq", sequenceName = "SEC_ID_PRODUCTO", allocationSize = 1)
	@Column(name = "ID_PRODUCTO")
	private Long id;

	@Column(name = "SKU", unique = true, nullable = false)
	private String sku;
	
	@Column(name = "NOMBRE", nullable = false)
	private String name;
	
	@Column(name = "DESCRIPCION")
	private String description;
	
	@Column(name = "PRECIO", nullable = false)
	private BigDecimal price;
	
	@Column(name = "PESO_KG")
	private BigDecimal weightKg;
	
	@Column(name = "DISPONIBLE", nullable = false)
	private Character available;
	
	@Column(name = "FECHA_CREACION", updatable = false)
	private Date creationDate;
	
	@Column(name = "FECHA_ACTUALIZACION")
	private Date updateDate;
	
	//@Column(name = "URL_IMAGEN")
	private String imageUrl;
	
	// Relacion con inventario uno a uno
	@OneToOne(mappedBy = "product", cascade = CascadeType.ALL, orphanRemoval = true)
	private Inventory inventory;
	
	// Relacion con categorias muchos a muchos
	@ManyToMany(fetch = FetchType.EAGER)
	@JoinTable(name = "PRODUCTO_CATEGORIAS", joinColumns = @JoinColumn(name ="PRODUCTO_ID"))
	private Set<Category> categories;
}
