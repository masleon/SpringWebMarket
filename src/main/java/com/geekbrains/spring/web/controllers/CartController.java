package com.geekbrains.spring.web.controllers;

import com.geekbrains.spring.web.dto.Cart;
import com.geekbrains.spring.web.services.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartService cartService;

    @GetMapping("/add/{id}")
    public void addToCart(@PathVariable Long id){
        cartService.add(id);
    }

    @GetMapping("/delete/{id}")
    public void deleteFromCart(@PathVariable Long id){
        cartService.delete(id);
    }
    @GetMapping("/clean")
    public void cleanCart(){
        cartService.clean();
    }


    @GetMapping
    public Cart getCurrentCart(){
        return cartService.getCurrentCart();
    }

}
