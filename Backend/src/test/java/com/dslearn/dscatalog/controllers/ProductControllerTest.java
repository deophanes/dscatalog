package com.dslearn.dscatalog.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import com.dslearn.dscatalog.dto.ProdutoDTO;
import com.dslearn.dscatalog.services.ProdutoService;
import com.dslearn.dscatalog.services.exceptions.DatabaseException;
import com.dslearn.dscatalog.services.exceptions.ServiceNotfoundException;
import com.dslearn.dscatalog.tests.Factory;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(ProdutoController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private ProdutoService service;

	private Long existingId;
	private Long nonExistingId;
	private Long dependentId;

	private ProdutoDTO produtoDTO;
	private PageImpl<ProdutoDTO> page;

	@BeforeEach
	void setUp() throws Exception {

		existingId = 1L;
		nonExistingId = 300L;
		dependentId = 3L;

		produtoDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(produtoDTO));

		when(service.findAllPaged(any())).thenReturn(page);

		when(service.findById(existingId)).thenReturn(produtoDTO);
		when(service.findById(nonExistingId)).thenThrow(ServiceNotfoundException.class);

		when(service.update(existingId, eq(any()))).thenReturn(produtoDTO);
		when(service.update(nonExistingId, eq(any()))).thenThrow(ServiceNotfoundException.class);

		doNothing().when(service).delete(existingId);
		doThrow(ServiceNotfoundException.class).when(service).delete(nonExistingId);
		doThrow(DatabaseException.class).when(service).delete(dependentId);

		when(service.insert(any())).thenReturn(produtoDTO);
	}

	@Test
	public void findAllShouldReturnPage() throws Exception {

		ResultActions result = mockMvc.perform(get("/produtos").accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
	}

	@Test
	public void findByIdShouldReturnProductWhenIdExists() throws Exception {

		ResultActions result = mockMvc.perform(get("/produtos/{id}", existingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.id").exists());
	}

	@Test
	public void findByIdShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result = mockMvc.perform(get("/produtos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void updateShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(produtoDTO);

		ResultActions result = mockMvc.perform(put("/produtos/{id}", nonExistingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void updateShouldReturnProductDTOWhenIdExists() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(produtoDTO);

		ResultActions result = mockMvc.perform(put("/produtos/{id}", existingId).content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isOk());
		result.andExpect(jsonPath("$.Id").exists());
	}

	@Test
	public void deleteShouldReturnNoContentWhenIdExists() throws Exception {

		ResultActions result = mockMvc.perform(delete("/produtos/{id}", existingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNoContent());
	}

	@Test
	public void deleteShouldReturnNotFoundWhenIdDoesNotExist() throws Exception {

		ResultActions result = mockMvc
				.perform(delete("/produtos/{id}", nonExistingId).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isNotFound());
	}

	@Test
	public void insertShouldReturnProductDTOCreated() throws Exception {

		String jsonBody = objectMapper.writeValueAsString(produtoDTO);

		ResultActions result = mockMvc.perform(post("/produtos").content(jsonBody)
				.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON));

		result.andExpect(status().isCreated());
		result.andExpect(jsonPath("$.id").exists());
	}

}
