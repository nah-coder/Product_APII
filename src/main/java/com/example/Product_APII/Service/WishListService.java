package com.example.Product_APII.Service;

import com.example.Product_APII.DTO.Response.WishListResponse;
import com.example.Product_APII.Entity.Product;
import com.example.Product_APII.Entity.User;
import com.example.Product_APII.Entity.WishList;
import com.example.Product_APII.Exception.AppException;
import com.example.Product_APII.Exception.ErrorCode;
import com.example.Product_APII.Mapper.WishListMapper;
import com.example.Product_APII.Repository.ProductRepository;
import com.example.Product_APII.Repository.UsersRepository;
import com.example.Product_APII.Repository.WishListRepository;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class WishListService {

    private final WishListRepository wishListRepository;
    private final UsersRepository userRepository;
    private final ProductRepository productRepository;
    private final WishListMapper wishListMapper;

    public WishListService(WishListRepository wishListRepository,
                           UsersRepository userRepository,
                           ProductRepository productRepository,
                           WishListMapper wishListMapper) {
        this.wishListRepository = wishListRepository;
        this.userRepository = userRepository;
        this.productRepository = productRepository;
        this.wishListMapper = wishListMapper;
    }

    public String createOrUpdateWishlist(Integer userId, Integer productId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new AppException(ErrorCode.USER_NOT_EXISTED));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        Optional<WishList> optionalWishList = wishListRepository.findByUserId(userId);

        WishList wishList = optionalWishList.orElseGet(() -> {
            WishList newWishList = new WishList();
            newWishList.setUser(user);
            newWishList.setWishListName("Default Wishlist");
            newWishList.setQuantity(0);
            return newWishList;
        });

        Set<Product> products = wishList.getProducts();
        if (!products.contains(product)) {
            products.add(product);
        }
        wishList.setProducts(products);
        wishList.setQuantity(products.size());

        wishListRepository.save(wishList);
        return "Product added/updated in wishlist successfully.";
    }

    public WishListResponse updateWishListProduct(Integer wishListId, Set<Integer> productIds) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new AppException(ErrorCode.WISHLIST_NOT_FOUND));

        Set<Product> products = new HashSet<>(productRepository.findAllById(productIds));
        wishList.setProducts(products);
        wishList.setQuantity(products.size());

        return wishListMapper.toResponse(wishListRepository.save(wishList));
    }

    public String removeProductFromWishList(Integer wishListId, Integer productId) {
        WishList wishList = wishListRepository.findById(wishListId)
                .orElseThrow(() -> new AppException(ErrorCode.WISHLIST_NOT_FOUND));

        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new AppException(ErrorCode.PRODUCT_NOT_FOUND));

        if (!wishList.getProducts().contains(product)) {
            throw new AppException(ErrorCode.PRODUCT_NOT_FOUND);
        }

        wishList.getProducts().remove(product);
        wishList.setQuantity(wishList.getProducts().size());

        wishListRepository.save(wishList);
        return "Product removed from wishlist successfully.";
    }

    public List<WishListResponse> getWishlistByUserId(Integer userId) {
        List<WishList> wishLists = wishListRepository.findAllByUserId(userId);
        return wishLists.stream().map(wishListMapper::toResponse).collect(Collectors.toList());
    }
}