package com.rmendes.kafkademo.api;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.rmendes.kafkademo.model.AccountEvent;

import io.smallrye.reactive.messaging.annotations.Channel;
import io.smallrye.reactive.messaging.annotations.Emitter;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.vertx.core.json.Json;

@Path("/account")
public class AccountEventService {
	
	@Inject @Channel("reactive-account-events") Emitter<KafkaMessage<String, String>> eventEmitter;


	@POST
	@Path("/event")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.TEXT_PLAIN)
	public String getAccountEventMessage(AccountEvent event) {
		try {
			eventEmitter.send(KafkaMessage.of(null, Json.encode(event)));
			return "Message Received";
		}catch (Exception e) {
			e.printStackTrace();
			return "Error: "+e.getMessage();
		}
	}
}
