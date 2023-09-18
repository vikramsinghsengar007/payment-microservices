package com.microservices.payment.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.math.BigInteger;

@Data
@Document
public class Payment implements Serializable {

    @Id
    public BigInteger paymentId;

    private String name;
}
