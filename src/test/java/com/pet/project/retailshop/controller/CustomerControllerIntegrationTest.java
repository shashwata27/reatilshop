package com.pet.project.retailshop.controller;

import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class CustomerControllerIntegrationTest {
    private static final String CUSTOMERS_API_GENERAL_URL="/v1/customers";

    @Autowired
    private TestRestTemplate template;

    @Test
    void getCustomersById_IntegrationTest() throws JSONException {
        String expectedCustomerById=
                """
                {
                  "customer_id": 1,
                  "name": "Raimondo Aire",
                  "address": "03949 Warrior Junction",
                  "phoneNumber": "999-564-3927"
                }
                """;

        ResponseEntity<String> actualCustomerById = template.getForEntity(CUSTOMERS_API_GENERAL_URL + "/1", String.class);
        HttpStatusCode actualCustomerByIdStatusCode = actualCustomerById.getStatusCode();
        String actualCustomerByIdContentType = actualCustomerById.getHeaders().get("Content-Type").get(0);
        String actualCustomerByIdBody = actualCustomerById.getBody();

        assertTrue(actualCustomerByIdStatusCode.is2xxSuccessful());
        assertEquals("application/json",actualCustomerByIdContentType);
        JSONAssert.assertEquals(expectedCustomerById, actualCustomerByIdBody,true);
    }

    @Test
    void getCustomersByName_IntegrationTest() throws JSONException {
        String expectedCustomerByName=
                """
                                {
                                   "customer_id": 2,
                                   "name": "Shanna Frodsam",
                                   "address": "90866 Hauk Avenue",
                                   "phoneNumber": "360-274-4713"
                                 }
                        """;

        ResponseEntity<String> actualCustomerByName = template.getForEntity(CUSTOMERS_API_GENERAL_URL + "/name/Shanna Frodsam", String.class);
        HttpStatusCode actualCustomerByNameStatusCode = actualCustomerByName.getStatusCode();
        String actualCustomerByNameContentType = actualCustomerByName.getHeaders().get("Content-Type").get(0);
        String actualCustomerByNameBody = actualCustomerByName.getBody();

        assertTrue(actualCustomerByNameStatusCode.is2xxSuccessful());
        assertEquals("application/json",actualCustomerByNameContentType);
        JSONAssert.assertEquals(expectedCustomerByName, actualCustomerByNameBody,true);
    }

    /*Has SIDE_EFFECTS*/
    @Test
    void getAllCustomersByPhoneNumber_IntegrationTest() throws JSONException {
        String expectedCustomerByPhoneNumber=
                """
                                [
                                    {
                                      "customer_id": 5,
                                      "name": "Anderson Rawcliffe",
                                      "address": "03 Dawn Lane",
                                      "phoneNumber": "141-665-3225"
                                    }
                                  ]
                        """;

        ResponseEntity<String> actualCustomerByPhoneNumber = template.getForEntity(CUSTOMERS_API_GENERAL_URL + "/phone-number/141-665-3225", String.class);
        HttpStatusCode actualCustomerByPhoneNumberStatusCode = actualCustomerByPhoneNumber.getStatusCode();
        String actualCustomerByPhoneNumberContentType = actualCustomerByPhoneNumber.getHeaders().get("Content-Type").get(0);
        String actualCustomerByPhoneNumberBody = actualCustomerByPhoneNumber.getBody();

        assertTrue(actualCustomerByPhoneNumberStatusCode.is2xxSuccessful());
        assertEquals("application/json",actualCustomerByPhoneNumberContentType);
        JSONAssert.assertEquals(expectedCustomerByPhoneNumber, actualCustomerByPhoneNumberBody,true);
    }


    @Test
    void createCustomer_IntegrationTest() throws JSONException {
        String requestBody=
                """
                [{\040\040
                    "name":"subi sam",
                    "address":"pent house 312",
                    "phoneNumber": "9090-080-091"
                 
                },{\040\040
                    "name":"lashyi saha",
                    "address":"pent house 312",
                    "phoneNumber": "9090-080-192"
                 
                }]
                """;
        HttpHeaders requestHeader=new HttpHeaders();
        requestHeader.add("Content-Type","application/json");
        HttpEntity<String> request=new HttpEntity<String>(requestBody,requestHeader);
        String expectedResponseBody= """
                [
                    {
                        "body": {
                            "name": "subi sam",
                            "address": "pent house 312",
                            "phoneNumber": "9090-080-091"
                        },
                        "statusCodeValue": 201,
                        "statusCode": "CREATED"
                    },
                    {
                        "body": {
                            "name": "lashyi saha",
                            "address": "pent house 312",
                            "phoneNumber": "9090-080-192"
                        },
                        "statusCodeValue": 201,
                        "statusCode": "CREATED"
                    }
                ]
                """;

        ResponseEntity<String> response =
                template.exchange(CUSTOMERS_API_GENERAL_URL, HttpMethod.POST, request, String.class);
        HttpStatusCode statusCode = response.getStatusCode();
        String responseBody = response.getBody();

        assertTrue(statusCode.is2xxSuccessful());
        JSONAssert.assertEquals(expectedResponseBody, responseBody,false);
    }

}