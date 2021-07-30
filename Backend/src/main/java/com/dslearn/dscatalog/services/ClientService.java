package com.dslearn.dscatalog.services;

import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dslearn.dscatalog.dto.ClientDTO;
import com.dslearn.dscatalog.models.Client;
import com.dslearn.dscatalog.repositories.ClientRepository;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;

@Service
public class ClientService {

	@Autowired
	private ClientRepository repository;

	@Transactional(readOnly = true)
	public Page<ClientDTO> findAllPaged(PageRequest pageRequest) {
		Page<Client> list = repository.findAll(pageRequest);
		return list.map(x -> new ClientDTO(x));
	}

	@Transactional(readOnly = true)
	public ClientDTO findById(Long id) {
		Optional<Client> optional = repository.findById(id);
		Client client = optional.orElseThrow(() -> new ServiceNotfoundException("Cliente não encontrado"));
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO insert(ClientDTO clientDTO) {
		Client client = new Client();
		BeanUtils.copyProperties(clientDTO, client);
		client = repository.save(client);
		return new ClientDTO(client);
	}

	@Transactional
	public ClientDTO update(Long id, ClientDTO clientDTO) {
		try {
			Client client = repository.getOne(id);
			//BeanUtils.copyProperties(clientDTO, client);
			copyDtoToEntity(clientDTO, client);
			client = repository.save(client);
			return new ClientDTO(client);
		} catch (EntityNotFoundException e) {
			throw new ServiceNotfoundException("código não Encontrado: " + id);
		}
	}

	@Transactional
	public void delete(Long id) {
		try {
			repository.deleteById(id);
		} catch (EmptyResultDataAccessException e) {
			throw new ServiceNotfoundException("Código não Encontrado: " + id);
		}
	}

	private void copyDtoToEntity(ClientDTO dto, Client client) {
		client.setName(dto.getName());
		client.setCpf(dto.getCpf());
		client.setIncome(dto.getIncome());
		client.setBirthDate(dto.getBirthDate());
		client.setChildren(dto.getChildren());
	}
}
