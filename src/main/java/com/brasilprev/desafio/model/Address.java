package com.brasilprev.desafio.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "tbl_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Size(min = 8)
	private String zipCode;
	
	@NotBlank
	private String publicPlace;
	@NotBlank
	private String neighborhood;
	private String locality;
	@NotBlank
	private String uf;
	@NotBlank
	private String number;
	private String complement;
	private Boolean status;
	

	public Address(@Size(min = 8) String zipCode, @NotBlank String publicPlace, @NotBlank String neighborhood,
			String locality, @NotBlank String uf, @NotBlank String number, String complement, Boolean status) {
		super();
		this.zipCode = zipCode;
		this.publicPlace = publicPlace;
		this.neighborhood = neighborhood;
		this.locality = locality;
		this.uf = uf;
		this.number = number;
		this.complement = complement;
		this.status = status;
	}
	
	
	
}
