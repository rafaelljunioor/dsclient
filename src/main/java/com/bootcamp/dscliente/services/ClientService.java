package com.bootcamp.dscliente.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bootcamp.dscliente.dtos.ClientDTO;
import com.bootcamp.dscliente.entities.Client;
import com.bootcamp.dscliente.entities.repositories.ClientRepository;
import com.bootcamp.dscliente.services.exceptions.ResourceNotFoundException;


@Service
public class ClientService {
	
	@Autowired
	private ClientRepository repository;
	@Transactional(readOnly=true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest){	
		Page<Client>  list = repository.findAll(pageRequest);
		return list.map(x-> new ClientDTO(x));
	}
	
	@Transactional(readOnly=true)
	public ClientDTO findById(Long id){
		
		Optional<Client> obj  = repository.findById(id);
		Client entity = obj.orElseThrow(() -> new ResourceNotFoundException("Entity Not Found"));
		return new ClientDTO(entity);
	}
	
	public ClientDTO insert(ClientDTO dto) {
		Client entity = new Client();
		copyDtoToEntity(dto, entity);
		entity = repository.save(entity);
		return new ClientDTO(entity);
	}

	private void copyDtoToEntity(ClientDTO dto, Client entity) {
		entity.setName(dto.getName());
		entity.setIncome(dto.getIncome());
		entity.setCpf(dto.getCpf());
		entity.setChildren(dto.getChildren());
		entity.setBirthDate(dto.getBirthDate());
		
	}
}