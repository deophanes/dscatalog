package com.dslearn.dscatalog.controllers;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.test.web.servlet.MockMvc;

import com.dslearn.dscatalog.dto.ProdutoDTO;
import com.dslearn.dscatalog.services.ProdutoService;
import com.dslearn.dscatalog.tests.Factory;

@WebMvcTest(ProdutoController.class)
public class ProductControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private ProdutoService service;
	
	private ProdutoDTO produtoDTO;
	private PageImpl<ProdutoDTO> page;
	
	@BeforeEach
	void setUp() throws Exception {
	
		produtoDTO = Factory.createProductDTO();
		page = new PageImpl<>(List.of(produtoDTO));
		when(service.findAllPaged(any())).thenReturn(page);
	}
	
	@Test
	private void findAllshouldReturnPage() throws Exception {
		mockMvc.perform(get("/produtos")).andExpect(status().isOk());
	}
	
	
}
