package br.com.itinerary.exceptions;

public class FailCommunicationException extends RuntimeException {

  public FailCommunicationException(String message) {
    super(message);
  }
}
