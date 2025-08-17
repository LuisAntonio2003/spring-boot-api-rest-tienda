package com.tienda.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
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
@Table(name = "INVENTARIO")
public class Inventory {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "inventory_seq")
    @SequenceGenerator(name = "inventory_seq", sequenceName = "SEC_ID_INVENTARIO", allocationSize = 1)
    @Column(name = "ID_INVENTARIO")
    private Long id;
	
	@Column(name = "CANTIDAD_STOCK")
	private Integer stock;
	
	@Column(name = "CANTIDAD_RESERVADA")
	private Integer reserved;
	
	@OneToOne
	@JoinColumn(name = "PRODUCTO_ID", nullable = false)
	private Product product;
}
