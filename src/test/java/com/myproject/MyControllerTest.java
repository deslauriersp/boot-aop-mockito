package com.myproject;

import static io.restassured.RestAssured.get;
import static org.hamcrest.Matchers.equalTo;
import static org.mockito.Mockito.when;

import io.restassured.RestAssured;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { MyApplication.class })
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class MyControllerTest {

    @MockBean
    MyService myService;

    @LocalServerPort
    private Integer port;

    @Before
    public void setBaseUri() {
        RestAssured.port = port;
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testObject() throws MyException {
        when(myService.getAnObject()).thenReturn("some string");
        get("/object").then().statusCode(200).assertThat().body("response", equalTo("some string"));
    }

}
