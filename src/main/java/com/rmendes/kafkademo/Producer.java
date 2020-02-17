package com.rmendes.kafkademo;

import java.math.BigDecimal;
import java.util.Random;
import java.util.concurrent.TimeUnit;

import javax.enterprise.context.ApplicationScoped;

import org.eclipse.microprofile.reactive.messaging.Outgoing;

import com.rmendes.kafkademo.model.AccountEvent;

import io.reactivex.Flowable;
import io.smallrye.reactive.messaging.kafka.KafkaMessage;
import io.vertx.core.json.Json;

@ApplicationScoped
public class Producer {
	
	
	@Outgoing("account-events")
	public Flowable<KafkaMessage<String, String>> generateEvent(){
		return Flowable.interval(5, TimeUnit.SECONDS)
				.onBackpressureDrop()
				.map(tick -> {
					return KafkaMessage.of(null, Json.encode(generateRandonAccountEvent()));
				});
	}
	
	
	private AccountEvent generateRandonAccountEvent(){
		AccountEvent a = new AccountEvent();
		Random r = new Random();
		a.setType((r.nextInt()%2 == 0 ? "C":"D"));
		a.setValue(new BigDecimal(r.nextDouble()));		
		a.setAccountNumber(r.nextInt()%2 == 0 ? 1l : 2l);
		return a;
	}
}
