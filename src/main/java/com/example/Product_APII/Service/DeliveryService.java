package com.example.Product_APII.Service;


import com.example.Product_APII.DTO.Request.DeliveryRequest;
import com.example.Product_APII.DTO.Response.DeliveryResponse;
import com.example.Product_APII.Entity.Delivery;
import com.example.Product_APII.Entity.Order;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.DeliveryMapper;
import com.example.Product_APII.Repository.DeliveryRepository;
import com.example.Product_APII.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DeliveryService {

    private final DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;
    private final OrderRepository orderRepository;

    public DeliveryService(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper, OrderRepository orderRepository) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
        this.orderRepository = orderRepository;
    }

    // Tạo mới phương thức giao hàng
    public DeliveryResponse create(DeliveryRequest request) {
        Delivery delivery = deliveryMapper.toEntity(request);
        return deliveryMapper.toResponse(deliveryRepository.save(delivery));
    }

    // Lấy danh sách tất cả phương thức giao hàng
    public List<DeliveryResponse> getAll() {
        return deliveryRepository.findAll()
                .stream().map(deliveryMapper::toResponse)
                .collect(Collectors.toList());
    }

    // Lấy chi tiết giao hàng theo ID
    public DeliveryResponse getById(Integer id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND));
        return deliveryMapper.toResponse(delivery);
    }

    // Cập nhật thông tin giao hàng
    public DeliveryResponse update(Integer id, DeliveryRequest request) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND));

        deliveryMapper.updateEntityFromDto(request, delivery);
        return deliveryMapper.toResponse(deliveryRepository.save(delivery));
    }

    // Xóa phương thức giao hàng
    public String delete(Integer id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new AppException(ErrorCode.DELIVERY_NOT_FOUND));

        deliveryRepository.delete(delivery);
        return "Delivery method deleted successfully.";
    }
    // ✅ Tìm kiếm delivery theo tên (gần đúng)
    public List<DeliveryResponse> findByDeliveryName(String name) {
        List<Delivery> deliveries = deliveryRepository.findByDeliveryNameContainingIgnoreCase(name);
        return deliveries.stream()
                .map(deliveryMapper::toResponse)
                .collect(Collectors.toList());
    }

    public ResponseEntity<?> findDeliveryByOrderId(Long orderId) {
        Order order = orderRepository.findOrderById(orderId);

        if(order == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new AppException(ErrorCode.ORDER_NOT_FOUND));
        }

        DeliveryResponse deliveryDto = new DeliveryResponse();
        deliveryDto.setDeliveryName(order.getDelivery().getDeliveryName());
        deliveryDto.setShippingFee(order.getDelivery().getShippingFee());
        deliveryDto.setId(order.getDelivery().getId());

        return ResponseEntity.ok().body(deliveryDto);
    }
}
