package com.brasilprev.desafio.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.br.CPF;

import com.sun.istack.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Entity
@Table(name = "tbl_clients")
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	@Size(min = 3, max = 100, message = "The name must contain more than 3 characters")
	private String name;
	
	@NotNull
	@CPF(message="CPF invalid")
	private String cpf;
	
	@OneToMany(cascade = CascadeType.ALL)
	@JoinColumn(name = "fk_client", referencedColumnName = "id")
	List<Address> address = new ArrayList<>();

	
	public Client(@Size(min = 3, max = 100, message = "The name must contain more than 3 characters") String name,
			@CPF(message = "CPF invalid") String cpf) {
		super();
		this.name = name;
		this.cpf = cpf;
	}

}
