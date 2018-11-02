package br.com.itinerary.exceptions;

public class RoutesNotFoundException extends RuntimeException {

  public RoutesNotFoundException(String message) {
    super(message);
  }
}
