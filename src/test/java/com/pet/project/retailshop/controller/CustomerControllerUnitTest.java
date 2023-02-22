package com.pet.project.retailshop.controller;

import com.pet.project.retailshop.model.Customer;
import com.pet.project.retailshop.repository.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@WebMvcTest(controllers = CustomerController.class)
class CustomerControllerUnitTest {
    @MockBean
    private CustomerRepository customerRepositoryMock;

    @Autowired
    private MockMvc mockMvc;

    private final String GENERERIC_CUSTOMER_URL="http://localhost:8080/v1/customers";

    @Test
    void getCustomersById_SuccessTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(GENERERIC_CUSTOMER_URL + "/1").accept(MediaType.APPLICATION_JSON);
        Customer mockCustomer = new Customer("Raimondo Aire", "03949 Warrior Junction", "999-564-3927");
        when(customerRepositoryMock.findById(1)).thenReturn(Optional.of(mockCustomer));
        String expectedCustomer=
                """
                {
                  "name": "Raimondo Aire",
                  "address": "03949 Warrior Junction",
                  "phoneNumber": "999-564-3927"
                }
                """;

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(200,mvcResult.getResponse().getStatus());
        JSONAssert.assertEquals(expectedCustomer,mvcResult.getResponse().getContentAsString(),false);
    }

    @Test
    void getCustomersById_FailTest() throws Exception {
        MockHttpServletRequestBuilder requestBuilder =
                MockMvcRequestBuilders.get(GENERERIC_CUSTOMER_URL + "/500").accept(MediaType.APPLICATION_JSON);
        when(customerRepositoryMock.findById(1)).thenReturn(Optional.empty());

        MvcResult mvcResult = mockMvc.perform(requestBuilder).andReturn();

        assertEquals(404,mvcResult.getResponse().getStatus());
    }

}