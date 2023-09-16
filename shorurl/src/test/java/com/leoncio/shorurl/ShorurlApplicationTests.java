package com.leoncio.shorurl;

import static org.junit.jupiter.api.Assertions.assertEquals;


import org.apache.logging.log4j.Logger;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.leoncio.shorturl.ShorturlApplication;
import com.leoncio.shorturl.dto.UrlDto;
import com.leoncio.shorturl.service.ShortUrlService;


@SpringBootTest
@AutoConfigureMockMvc
@ContextConfiguration(classes = {ShorturlApplication.class})
class ShorurlApplicationTests {

	@Autowired
	private MockMvc mvc;

	@Autowired
	private Logger logger;

	@MockBean
	private ShortUrlService shortUrlService;

	@Test
	void verificaSeOStatusDaRequisicaoRetornaStatus404()throws Exception{
		String uri = "COdigo";
		MvcResult result = mvc.perform(MockMvcRequestBuilders.get("/{codeUrl}", uri)).andReturn();

		int status = result.getResponse().getStatus();

		// ShortUrlNotFoundException exp = assertThrows(ShortUrlNotFoundException.class, ()-> result.getResolvedException());

		logger.info("> [RESPONSE] - " + result.getResolvedException());
		
		// assertEquals("Url não encontrada", exp.getMessage());
		assertEquals(404, status);
	}

	@Test
	void verificaSeARequisicaoESalva()throws Exception{
		String uri = "/";

		UrlDto dto = new UrlDto();

		dto.setUrl("google.com.br");

		String mapper = new ObjectMapper().writeValueAsString(dto);

		MvcResult result = mvc.perform(MockMvcRequestBuilders.post(uri).contentType(MediaType.APPLICATION_JSON).content(mapper)).andReturn();

		String content = result.getResponse().getContentAsString();
		int status = result.getResponse().getStatus();

		logger.info("> [RESPONSE] - "+ content.toString());

		assertEquals(201, status);
	}

}
