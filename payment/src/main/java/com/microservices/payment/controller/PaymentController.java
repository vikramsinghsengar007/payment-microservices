package com.microservices.payment.controller;

import com.microservices.payment.model.Payment;
import com.microservices.payment.repository.mongoDB.MongoDbRepo;
import com.microservices.payment.service.PaymentService;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
@AllArgsConstructor
public class PaymentController {


    private MongoDbRepo mongoDbRepo;

    private PaymentService paymentService;

    @GetMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getPayment(@RequestParam(name = "paymentId") String paymentId){
        Payment p = mongoDbRepo.findItemByPaymentId(paymentId);
        return p;
    }

    @GetMapping(value = "/allPayments", produces = MediaType.APPLICATION_JSON_VALUE)
    public Object getAllPayment(){
        List<Payment> payments = mongoDbRepo.findAll();
        return payments;
    }

    @PostMapping(value = "/payments", produces = MediaType.APPLICATION_JSON_VALUE,
    consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object makePayment(@RequestBody Payment payment){
        ResponseEntity dataPresent = paymentService.isDataPresent(payment);

        return dataPresent;
    }

    @PostMapping(value = "/multiPayments", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object makeMultiPayment(@RequestBody List<Payment> payments){
        List<Payment> savedPayments = mongoDbRepo.saveAll(payments);
        return savedPayments;
    }

    @PostMapping(value = "/deletePayments", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object deletePayment(@RequestBody List<Payment> payments){
        List<Payment> savedPayments = mongoDbRepo.saveAll(payments);
        return savedPayments;
    }

    @PutMapping(value = "/updatePayments", produces = MediaType.APPLICATION_JSON_VALUE,
            consumes = MediaType.APPLICATION_JSON_VALUE)
    public Object updatePayment(@PathVariable String id, @RequestBody Payment payments){
        Payment savedPayments = mongoDbRepo.findItemByPaymentId(id);
        paymentService.updatePayment(savedPayments, payments);
        return savedPayments;
    }

}
