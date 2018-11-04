package br.com.route.gateways.http.handler;

import lombok.Getter;

@Getter
public class Message {

  private String description;

  public Message(final String description) {
    this.description = description;
  }

}
