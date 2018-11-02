package br.com.itinerary.gateways.http.handler;

import br.com.itinerary.exceptions.FailCommunicationException;
import br.com.itinerary.exceptions.RoutesNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class CustomExceptionHandler {

  @ResponseBody
  @ExceptionHandler({FailCommunicationException.class})
  public HttpEntity<Message> handlerFailCommunicationException(
      final FailCommunicationException ex) {
    final Message message = new Message(ex.getMessage());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    log.error(ex.getMessage(), ex);
    return new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
  }

  @ResponseBody
  @ExceptionHandler({RoutesNotFoundException.class})
  public HttpEntity<Message> handlerRoutesNotFoundExceptionn(
      final FailCommunicationException ex) {
    final Message message = new Message(ex.getMessage());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    log.error(ex.getMessage(), ex);
    return new ResponseEntity(message, responseHeaders, HttpStatus.NOT_FOUND);
  }
}
