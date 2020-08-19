package com.brasilprev.desafio.resource;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.brasilprev.desafio.model.Address;
import com.brasilprev.desafio.model.Client;
import com.brasilprev.desafio.repository.AddressRepository;
import com.brasilprev.desafio.repository.ClientRepository;

import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/clients")
public class ClientResource {
	
	@Autowired
	ClientRepository clientRepository;
	
	@Autowired
	AddressRepository addressRepository;
	
	@ApiOperation(value = "Returns all clients")
	@GetMapping
	public List<Client> getClients(){
		return clientRepository.findAll();
	}
	
	@ApiOperation(value = "Returns the client by id")
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
	public ResponseEntity<?> getClientById(@PathVariable Long id){
		Client client = clientRepository.findById(id).orElse(null);
		if(client == null) ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		return ResponseEntity.ok(client);
	}
	
	@ApiOperation(value = "Register new client")
	@PostMapping(produces = "application/json")
	public  ResponseEntity<?> create(@Valid @RequestBody Client client) {
		if(clientRepository.existsByCpf(client.getCpf())) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		return ResponseEntity.status(HttpStatus.CREATED).body(clientRepository.save(client));
	}
	
	@ApiOperation(value = "Update client")
	@PutMapping
	public ResponseEntity<?> update(@Validated @RequestBody Client client) {
		
		if(clientRepository.findById(client.getId()).orElse(null) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(clientRepository.save(client));
	}
	
	@ApiOperation(value = "Update address the client")
	@PutMapping("/{idClient}/address")
	public ResponseEntity<?> updateAddress(@Validated @RequestBody Address address, @PathVariable Long idClient) {
		
		if(!clientRepository.existsById(idClient)) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(addressRepository.save(address));
	}
	
	@ApiOperation(value = "Delete client")
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public ResponseEntity<?> delete(@PathVariable Long id) {
		if(clientRepository.findById(id).orElse(null) == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
		}
		clientRepository.deleteById(id);
		return null;
	}
	
	
	

}
