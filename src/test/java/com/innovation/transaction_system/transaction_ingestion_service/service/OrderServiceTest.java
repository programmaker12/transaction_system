package com.innovation.transaction_system.transaction_ingestion_service.service;


import com.innovation.transaction_system.transaction_ingestion_service.product.entity.Product;
import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.EmailService;
import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.PaymentService;
import com.innovation.transaction_system.transaction_ingestion_service.product.interfaces.ProductRepository;
import com.innovation.transaction_system.transaction_ingestion_service.product.service.OrderService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTest {

    @Mock
    private ProductRepository productRepository;

    @Mock
    private PaymentService paymentService;

    @Mock
    private EmailService emailService;

    @InjectMocks
    private OrderService orderService;

    @Test
    void productExists()
    {
        Product product = new Product();
        product.setId(1l);

        when(productRepository.findById(1l))
                .thenReturn(Optional.of(product));

        when(paymentService.makePayment(1000))
                .thenReturn(true );

        String result = orderService.placeOrder(
                1L
        , 1000, "product@order.com");

        assertEquals("Order Placed Successfully", result);

        verify(productRepository)
                .findById(1L);

        verify(paymentService)
                .makePayment(1000);

        verify(emailService)
                .sendEmail("product@order.com");
    }

    @Test
    void productNotFound()
    {
        when(productRepository.findById(1l))
                .thenReturn(Optional.empty());

        RuntimeException exception = assertThrows(
                RuntimeException.class,
                () -> orderService
                                    .placeOrder(1l, 1000, "product@order.com"));

        assertEquals("Product Not Found", exception.getMessage());

        verify(productRepository)
                .findById(1l);

        verify(paymentService, never())
                .makePayment(anyDouble());

        verify(emailService, never())
                .sendEmail(anyString());

    }

    @Test
    void paymentFailed(){

        Product product = new Product();
        product.setId(1l);

        when(productRepository.findById(1l))
                .thenReturn(Optional.of(product));

        when(paymentService.makePayment(1000))
                .thenReturn(false );

        String result = orderService.placeOrder(
                1L
                , 1000, "product@order.com");

        assertEquals("Payment Failed", result);

        verify(productRepository)
                .findById(1l);

        verify(paymentService)
                .makePayment(1000);

        verify(emailService, never())
                .sendEmail(anyString());

    }
}
