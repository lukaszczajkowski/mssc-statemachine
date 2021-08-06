package guru.springframework.ssm.msscssm.services;

import guru.springframework.ssm.msscssm.domain.Payment;
import guru.springframework.ssm.msscssm.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.transaction.Transactional;
import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class PaymentServiceImplTest {

    @Autowired
    PaymentService paymentService;

    @Autowired
    PaymentRepository paymentRepository;

    Payment payment;

    @BeforeEach
    void setUp() {
        payment = Payment.builder()
                .amount(new BigDecimal("12.99"))
                .build();
    }

    @Transactional
    @Test
    void preAuth() {
        Payment savedPayment = paymentService.newPayment(payment);

        System.out.println("Should be NEW");
        System.out.println(savedPayment.getState());

        paymentService.preAuth(savedPayment.getId());

        Payment paymentAfterPreAuth = paymentRepository.getById(savedPayment.getId());

        System.out.println("Should be PRE_AUTH");
        System.out.println(paymentAfterPreAuth);
    }
}