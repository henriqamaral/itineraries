package br.com.route.gateways.http.handler;

import br.com.route.exceptions.RouteValidationException;
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
  @ExceptionHandler({RouteValidationException.class})
  public HttpEntity<Message> handlerRouteValidationException(final RouteValidationException ex) {
    final Message message = new Message(ex.getMessage());
    HttpHeaders responseHeaders = new HttpHeaders();
    responseHeaders.add("Content-Type", "application/json; charset=utf-8");
    log.error(ex.getMessage(), ex);
    return new ResponseEntity(message, responseHeaders, HttpStatus.INTERNAL_SERVER_ERROR);
  }
}
