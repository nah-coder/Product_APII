package com.example.Product_APII.Service;


import com.example.Product_APII.DTO.Request.PaymentRequest;
import com.example.Product_APII.DTO.Response.PaymentResponse;
import com.example.Product_APII.Entity.Payment;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.PaymentMapper;
import com.example.Product_APII.Repository.PaymentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;

    public PaymentService(PaymentRepository paymentRepository, PaymentMapper paymentMapper) {
        this.paymentRepository = paymentRepository;
        this.paymentMapper = paymentMapper;
    }

    public PaymentResponse createPayment(PaymentRequest request) {
        Payment payment = paymentMapper.toEntity(request);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    public List<PaymentResponse> getAllPayments() {
        return paymentRepository.findAll().stream()
                .map(paymentMapper::toResponse)
                .collect(Collectors.toList());
    }

    public PaymentResponse getPaymentById(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        return paymentMapper.toResponse(payment);
    }

    public PaymentResponse updatePayment(Integer id, PaymentRequest request) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        paymentMapper.updateEntityFromDto(request, payment);
        return paymentMapper.toResponse(paymentRepository.save(payment));
    }

    public String deletePayment(Integer id) {
        Payment payment = paymentRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.PAYMENT_NOT_FOUND));
        paymentRepository.delete(payment);
        return "Deleted payment successfully.";
    }
}
