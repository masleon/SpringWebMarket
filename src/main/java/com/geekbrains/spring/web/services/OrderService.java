package com.geekbrains.spring.web.services;

import com.geekbrains.spring.web.dto.CartItem;
import com.geekbrains.spring.web.entities.Order;
import com.geekbrains.spring.web.entities.OrderItem;
import com.geekbrains.spring.web.entities.Product;
import com.geekbrains.spring.web.entities.User;
import com.geekbrains.spring.web.repositories.OrdersRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrdersRepository ordersRepository;
    private CartService cartService;
    private ProductsService productsService;
    public void createOrder(User user){
        Order order = new Order();
        cartService.getCurrentCart().getCartItems().stream().map(cartItem -> new OrderItem(productsService.findById(cartItem.getProductId()).get(),
                    order,
                    cartItem.getQuantity(),
                    cartItem.getPricePerProduct(),
                    cartItem.getPrice()
                    )).collect(Collectors.toList());

        ordersRepository.save(order);
    }
}
