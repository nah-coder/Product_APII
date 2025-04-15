package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Response.OrderDetailResponse;
import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.OrderDetailMapper;
import com.example.Product_APII.Repository.OrderDetailRepository;
import com.example.Product_APII.Repository.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderDetailService {
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;
    private final OrderDetailMapper orderDetailMapper;

    @Autowired
    public OrderDetailService(OrderDetailRepository orderDetailRepository, @Lazy OrderRepository orderRepository,OrderDetailMapper orderDetailMapper) {
        this.orderDetailRepository = orderDetailRepository;
        this.orderRepository = orderRepository;
        this.orderDetailMapper = orderDetailMapper;

    }

    public List<OrderDetailResponse> getOrderDetailsFromOrder(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);
        if (order == null) {
            throw new AppException(ErrorCode.ORDER_NOT_FOUND);
        }

        return order.getOrderDetailList()
                .stream()
                .map(orderDetailMapper::toDTO)
                .collect(Collectors.toList());
    }

}