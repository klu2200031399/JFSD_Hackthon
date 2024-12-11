package admin_user.service;

import admin_user.model.Payment;
import admin_user.repositories.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PaymentService {

    @Autowired
    private PaymentRepository paymentRepository;

    public void savePayment(Payment payment) {
        paymentRepository.save(payment);
    }
}
