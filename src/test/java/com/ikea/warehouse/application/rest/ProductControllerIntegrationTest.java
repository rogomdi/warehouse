package com.ikea.warehouse.application.rest;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ikea.warehouse.application.model.dto.ProductDto;
import com.ikea.warehouse.application.model.rest.RestToDtoMapper;
import com.ikea.warehouse.application.service.ApplicationProductService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.nio.file.Files;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class ProductControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ApplicationProductService productService;
    @Autowired
    private RestToDtoMapper restToDtoMapper;

    @Test
    void getAll() throws Exception {
        //Store articles
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article")
                .content(Files.readString(new ClassPathResource("inventory.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        //Store products
        mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .content(Files.readString(new ClassPathResource("products.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        MvcResult getProducts = mockMvc.perform(MockMvcRequestBuilders.get("/api/product")
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<ProductDto> products = new ObjectMapper().readValue(getProducts.getResponse().getContentAsString(), new TypeReference<List<ProductDto>>() {
        });
        assertNotNull(products, "result should not be null");
        assertEquals(2, products.size(), "Should return the two products stored");
        products.forEach(productDto -> {
            assertEquals(3, productDto.getComposition().size(), "Product is made of 3 articles");
        });

    }

    @Test
    void sell() throws Exception {
        //Store articles
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article")
                .content(Files.readString(new ClassPathResource("inventory.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful());
        //Store products
        MvcResult productRequest = mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .content(Files.readString(new ClassPathResource("products.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<UUID> products = new ObjectMapper().readValue(productRequest.getResponse().getContentAsString(), new TypeReference<List<UUID>>() {
        });
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/product")
                .content(products.get(0).toString())
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.ALL))
                .andExpect(status().is2xxSuccessful());
    }

    @Test
    void sellInvalidUuid() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.patch("/api/product")
                .content("invalidUuid")
                .contentType(MediaType.TEXT_PLAIN_VALUE)
                .accept(MediaType.ALL))
                .andExpect(status().is4xxClientError());
    }

    @Test
    void store() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article")
                .content(Files.readString(new ClassPathResource("inventory.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
                .content(Files.readString(new ClassPathResource("products.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<UUID> res = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<UUID>>() {
        });
        assertEquals(2, res.size(), "2 products should be stored");
    }

    @Test
    void storeWithFile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.post("/api/article")
                .content(Files.readString(new ClassPathResource("inventory.json").getFile().toPath()))
                .contentType(MediaType.APPLICATION_JSON)
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        MvcResult result = mockMvc.perform(MockMvcRequestBuilders.multipart("/api/product/bulk")
                .file(new MockMultipartFile("file", new ClassPathResource("products.json").getInputStream())))
                .andExpect(status().is2xxSuccessful())
                .andReturn();
        List<UUID> res = new ObjectMapper().readValue(result.getResponse().getContentAsString(), new TypeReference<List<UUID>>() {
        });
        assertEquals(2, res.size(), "2 products should be stored");
    }

    @Test
    void storeIncorrectFile() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.multipart("/api/product/bulk")
                .file(new MockMultipartFile("file", new ClassPathResource("test_file.txt").getInputStream())))
                .andExpect(status().isBadRequest());
    }
}