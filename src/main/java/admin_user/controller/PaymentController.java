package admin_user.controller;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import admin_user.model.Payment;
import admin_user.model.Scholarship;
import admin_user.service.PaymentService;
import admin_user.service.ScholarshipService;
import org.springframework.ui.Model;

@Controller
public class PaymentController {

    @Autowired
    private ScholarshipService scholarshipService;

    @Autowired
    private PaymentService paymentService;

    // Show payment gateway page with scholarship details
    @GetMapping("/payment-gateway/{id}")
    public String showPaymentPage(@PathVariable Long id, Model model) {
        Scholarship scholarship = scholarshipService.getScholarshipById(id);
        model.addAttribute("scholarship", scholarship);
        return "payment-gateway"; // This should map to the payment gateway HTML page
    }

    // Process payment and save transaction details
    @PostMapping("/process-payment")
    public String processPayment(@RequestParam String cardNumber, 
                                 @RequestParam String cardHolder,
                                 @RequestParam String expiryDate,
                                 @RequestParam String cvv,
                                 @RequestParam Long scholarshipId, // Assuming this is passed from the form
                                 Model model) {
        // Generate a unique transaction code (simulating a real payment gateway)
        String transactionCode = generateTransactionCode();

        // Create the Payment object to save to the database
        Payment payment = new Payment(cardNumber, cardHolder, expiryDate, cvv, transactionCode, LocalDateTime.now());

        // Save payment to the database
        paymentService.savePayment(payment);

        // Optionally, add the scholarship to the model if you want to display it in the success page
        Scholarship scholarship = scholarshipService.getScholarshipById(scholarshipId);
        model.addAttribute("scholarship", scholarship);
        model.addAttribute("transactionCode", transactionCode);

        // Return to the success page after payment processing
        return "payment-success"; // Page that shows success and transaction code
    }

    // Generate a unique transaction code
    private String generateTransactionCode() {
        return UUID.randomUUID().toString().replace("-", "").substring(0, 8);
    }
}
