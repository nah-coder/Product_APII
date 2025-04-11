package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Request.OrderRequest;
import com.example.Product_APII.DTO.Response.OrderResponse;
import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Mapper.OrderMapper;
import com.example.Product_APII.Repository.OrderRepository;
import com.example.Product_APII.Service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderService{

    private final OrderRepository orderRepository;
    private final OrderMapper orderMapper;

    public OrderService(OrderRepository orderRepository, OrderMapper orderMapper) {
        this.orderRepository = orderRepository;
        this.orderMapper = orderMapper;
    }

    public String create(OrderRequest request) {
        Order order = orderMapper.toEntity(request);
        orderRepository.save(order);
        return "Tạo đơn hàng thành công";
    }

    public String update(Long id, OrderRequest request) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        orderMapper.updateEntityFromDto(request, order);
        orderRepository.save(order);
        return "Cập nhật đơn hàng thành công";
    }

    public String delete(Long id) {
        orderRepository.deleteById(id);
        return "Xoá đơn hàng thành công";
    }

    public OrderResponse getById(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy đơn hàng"));
        return orderMapper.toResponse(order);
    }

    public Map<String, Object> getAll(int page, int size) {
        Page<Order> orderPage = orderRepository.findAll(PageRequest.of(page, size));
        List<OrderResponse> list = orderPage.getContent().stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());

        Map<String, Object> result = new HashMap<>();
        result.put("orders", list);
        result.put("currentPage", orderPage.getNumber());
        result.put("totalItems", orderPage.getTotalElements());
        result.put("totalPages", orderPage.getTotalPages());
        return result;
    }

    public List<OrderResponse> getByUserId(Integer userId) {
        return orderRepository.findByUserId(userId).stream()
                .map(orderMapper::toResponse)
                .collect(Collectors.toList());
    }
}
