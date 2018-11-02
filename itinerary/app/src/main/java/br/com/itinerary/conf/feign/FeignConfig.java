package br.com.itinerary.conf.feign;

import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableFeignClients(basePackages = {"br.com.itinerary.gateways.http"})
public class FeignConfig {}
