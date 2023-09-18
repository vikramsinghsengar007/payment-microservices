package com.microservices.payment.repository.mongoDB;

import com.microservices.payment.model.Payment;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface MongoDbRepo extends MongoRepository<Payment, String> {

    @Query("{paymentId:'?0'}")
    Payment findItemByPaymentId(String paymentId);

    @Query("{name: '?0'}")
    List<Payment> findPaymentByName(String name);
}
