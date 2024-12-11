package admin_user.repositories;

import admin_user.model.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment, Long> {
    // You can add custom query methods here if needed
}
