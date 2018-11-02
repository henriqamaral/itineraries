package br.com.itinerary.gateways.http.handler;

import br.com.itinerary.exceptions.FailCommunicationException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ResponseBody
  @ExceptionHandler({FailCommunicationException.class})
  @ResponseStatus(HttpStatus.SERVICE_UNAVAILABLE)
  public HttpEntity<Message> handlerFailCommunicationException(
      final FailCommunicationException ex) {
    final Message message = new Message(ex.getMessage());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    log.error(ex.getMessage(), ex);
    return new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
