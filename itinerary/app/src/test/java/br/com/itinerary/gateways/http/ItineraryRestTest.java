package br.com.itinerary.gateways.http;

import com.github.tomakehurst.wiremock.client.WireMock;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.wiremock.AutoConfigureWireMock;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.reactive.server.WebTestClient;

import java.time.Duration;

@AutoConfigureWireMock(port = 8081)
@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class ItineraryRestTest {

  WebTestClient webTestClient;

  @Test
  public void given_two_cities_calculate_short_connections_with_success() {

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Salvador"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Rio de Janeiro\" , \"destiny\":\"Salvador\", \"departureTime\":\"12:00\", \"arrivalTime\":\"16:00\"}, "
                            + "{\"from\":\"Sao Paulo\" , \"destiny\":\"Salvador\", \"departureTime\":\"9:00\", \"arrivalTime\":\"19:00\"}]")));

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Rio%20de%20Janeiro"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Macapa\" , \"destiny\":\"Rio de Janeiro\", \"departureTime\":\"9:00\", \"arrivalTime\":\"11:00\"}]")));

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Macapa"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Sao Paulo\" , \"destiny\":\"Macapa\", \"departureTime\":\"9:00\", \"arrivalTime\":\"11:00\"}]")));

    webTestClient
        .get()
        .uri("itineraries/calculateShortestConnections?fromCity=Sao Paulo&toCity=Salvador")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("@.departureCity")
        .isEqualTo("Sao Paulo")
        .jsonPath("@.arrivalCity")
        .isEqualTo("Salvador")
        .jsonPath("@.totalConnections")
        .isEqualTo("1");
  }

  @Test
  public void given_two_cities_calculate_shorttime_with_success() {

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Salvador"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Rio de Janeiro\" , \"destiny\":\"Salvador\", \"departureTime\":\"13:00\", \"arrivalTime\":\"15:00\"}, "
                            + "{\"from\":\"Sao Paulo\" , \"destiny\":\"Salvador\", \"departureTime\":\"9:00\", \"arrivalTime\":\"19:00\"}]")));

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Rio%20de%20Janeiro"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Macapa\" , \"destiny\":\"Rio de Janeiro\", \"departureTime\":\"11:00\", \"arrivalTime\":\"13:00\"}]")));

    WireMock.stubFor(
        WireMock.any(WireMock.urlMatching("/routes\\?cityName=Macapa"))
            .willReturn(
                WireMock.aResponse()
                    .withHeader(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON_UTF8_VALUE)
                    .withStatus(HttpStatus.OK.value())
                    .withBody(
                        "[{\"from\":\"Sao Paulo\" , \"destiny\":\"Macapa\", \"departureTime\":\"9:00\", \"arrivalTime\":\"11:00\"}]")));

    webTestClient
        .get()
        .uri("itineraries/calculateShortestTime?fromCity=Sao Paulo&toCity=Salvador")
        .exchange()
        .expectStatus()
        .isOk()
        .expectHeader()
        .contentType(MediaType.APPLICATION_JSON_UTF8)
        .expectBody()
        .jsonPath("@.departureCity")
        .isEqualTo("Sao Paulo")
        .jsonPath("@.arrivalCity")
        .isEqualTo("Salvador")
        .jsonPath("@.totalHours")
        .isEqualTo("6");
  }

  @Before
  public void setup() {
    this.webTestClient =
        WebTestClient.bindToServer()
            .responseTimeout(Duration.ofSeconds(10))
            .baseUrl("http://localhost:8082")
            .build();
  }
}
