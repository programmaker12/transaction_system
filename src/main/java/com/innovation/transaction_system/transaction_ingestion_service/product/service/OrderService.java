//package com.innovation.transaction_system.transaction_ingestion_service.product.service;
//
//
//import com.innovation.transaction_system.transaction_ingestion_service.product.entity.Product;
//import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.EmailService;
//import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.PaymentService;
//import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.ProductRepository;
//import org.springframework.stereotype.Service;
//
//@Service
//public class OrderService {
//
//    private final ProductRepository productRepository;
//    private final PaymentService paymentService;
//    private final EmailService emailService;
//
//    public OrderService(ProductRepository productRepository,
//                        PaymentService paymentService,
//                        EmailService emailService) {
//
//        this.productRepository = productRepository;
//        this.paymentService = paymentService;
//        this.emailService = emailService;
//    }
//
//    public String placeOrder(Long productId,
//                             double amount,
//                             String email) {
//
//        Product product = productRepository
//                .findById(productId)
//                .orElseThrow(() ->
//                        new RuntimeException("Product Not Found"));
//
//        boolean paymentSuccess =
//                paymentService.makePayment(amount);
//
//        if (!paymentSuccess) {
//            return "Payment Failed";
//        }
//
//        emailService.sendEmail(email);
//
//        return "Order Placed Successfully";
//    }
//}
