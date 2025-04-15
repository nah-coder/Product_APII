package com.example.Product_APII.Service;


import com.example.Product_APII.DTO.Request.CartItemRequest;
import com.example.Product_APII.DTO.Response.CartItemResponse;
import com.example.Product_APII.Entity.CartItem;
import com.example.Product_APII.Entity.Product;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Mapper.CartItemMapper;
import com.example.Product_APII.Repository.CartItemRepository;
import com.example.Product_APII.Repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CartItemService {

    private final CartItemRepository cartItemRepository;
    private final ProductRepository productRepository;

    // Giả sử bạn đã có user từ bảo mật hoặc truyền từ controller
    public List<CartItemResponse> getCartItems(User user) {
        return cartItemRepository.findByUser(user).stream()
                .map(CartItemMapper::toDTO)
                .collect(Collectors.toList());
    }

    public CartItemResponse addToCart(User user, CartItemRequest dto) {
        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(() -> new RuntimeException("Product not found"));

        CartItem existing = cartItemRepository.findByUserAndProductId(user, dto.getProductId());

        if (existing != null) {
            existing.setQuantity(existing.getQuantity() + dto.getQuantity());
            cartItemRepository.save(existing);
            return CartItemMapper.toDTO(existing);
        }

        CartItem cartItem = new CartItem();
        cartItem.setProduct(product);
        cartItem.setUser(user);
        cartItem.setQuantity(dto.getQuantity());
        cartItem.setCreateAt(Instant.now());

        return CartItemMapper.toDTO(cartItemRepository.save(cartItem));
    }

    public CartItemResponse updateCartItemQuantity(User user, int cartItemId, int newQty) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItem.setQuantity(newQty);
        return CartItemMapper.toDTO(cartItemRepository.save(cartItem));
    }

    public void removeCartItem(User user, int cartItemId) {
        CartItem cartItem = cartItemRepository.findById(cartItemId)
                .orElseThrow(() -> new RuntimeException("Cart item not found"));

        cartItemRepository.delete(cartItem);
    }

    public void clearCart(User user) {
        cartItemRepository.deleteByUser(user);
    }
}
