package no.kristiania.pgr301.exam.controller;

import io.restassured.RestAssured;
import no.kristiania.pgr301.exam.enums.DeviceType;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.*;
import static org.hamcrest.MatcherAssert.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class GeigerControllerTest {

  @LocalServerPort private int port;

  @Before
  public void setUp() throws Exception {
    RestAssured.baseURI = "http://localhost";
    RestAssured.port = port;
    RestAssured.basePath = "/devices";
  }

  @Test
  public void testCreatingGeigerCounter_withInvalidDeviceType_shouldReturn400() {
    String invalidDeviceType = "MONKEY";

    Arrays.stream(DeviceType.values())
        .forEach(deviceType -> assertThat(deviceType.name(), is(not(equalTo(invalidDeviceType)))));

    given().queryParam("deviceType", invalidDeviceType).post("/").then().statusCode(400);
  }
}
