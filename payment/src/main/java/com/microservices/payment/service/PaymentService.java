package com.microservices.payment.service;

import com.microservices.payment.model.Payment;
import com.microservices.payment.repository.mongoDB.MongoDbRepo;
import lombok.AllArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.math.BigInteger;
import java.util.List;

@Service
@AllArgsConstructor
public class PaymentService {


    private final MongoDbRepo mongoDbRepo;

    public Payment updatePayment(Payment dbPayment, Payment updatedPayments) {
            BeanUtils.copyProperties(updatedPayments, dbPayment);
        return mongoDbRepo.save(dbPayment);
    }

    public Payment savePayment(Payment payment){
        payment.setPaymentId(generateId());
        return  mongoDbRepo.save(payment);
    }

    public ResponseEntity isDataPresent(Payment payment){
        List<Payment> paymentByName = mongoDbRepo.findPaymentByName(payment.getName());
        if(!ObjectUtils.isEmpty(paymentByName)){
            return new ResponseEntity("duplicate record present in the DB. Total records available in the db: "+paymentByName.size(), HttpStatus.CONFLICT);
        }
        return new ResponseEntity(savePayment(payment), HttpStatus.OK);
    }

   private BigInteger generateId(){
       long count = mongoDbRepo.count()+1;

       return new BigInteger(String.valueOf(count));
    }
}
