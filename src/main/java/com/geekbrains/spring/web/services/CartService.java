package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.dto.CartItem;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.exceptions.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;

@Service
@RequiredArgsConstructor
public class CartService {
    private Cart tempCart;
    private final ProductsService productsService;

    @PostConstruct
    public void init(){
        tempCart = new Cart();
    }

    public Cart getCurrentCart(){
        return tempCart;
    }

    public void add(Long productId){
        Product product = productsService.findById(productId).orElseThrow(() -> new ResourceNotFoundException("Невозможно найти продукт с id " + productId + " для добавления в корзину."));
        CartItem ca = tempCart.findCartItemByProductId(productId);
        if (ca!=null){
            tempCart.addQuantityAndPrice(ca,1);
        }
        else {
            tempCart.add(product);
        }
    }

    public void delete(Long productId) {
        CartItem ca = tempCart.findCartItemByProductId(productId);
        if (ca!=null){
            tempCart.delete(ca);
        }
    }

    public void clean() {
        tempCart.clean();
    }
}
