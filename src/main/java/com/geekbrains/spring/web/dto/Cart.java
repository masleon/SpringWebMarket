package com.geekbrains.spring.web.dto;

import com.geekbrains.spring.web.entities.Product;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Data
public class Cart {
    private List<CartItem> cartItems;
    private int totalPrice;

    public Cart(){
        cartItems = new ArrayList<>();
    }

    public List<CartItem> getCartItems(){
        return Collections.unmodifiableList(cartItems);
    }

    public void add(Product product){
        //TODO: сделать чтобы при добавлении такого же продукта новая айтем не создавалась, а добавлялось количество и пересчитывалась общая цена
        cartItems.add(new CartItem(product.getId(), product.getTitle(), 1,product.getPrice(),product.getPrice()));
        recalculateTotalPrice();
    }

    private void recalculateTotalPrice(){
        totalPrice = 0;
        for (CartItem ca:cartItems){
            totalPrice+=ca.getPrice();
        }
    }

    public CartItem findCartItemByProductId(Long productId){
        for(CartItem ca:cartItems){
            if (ca.getProductId().equals(productId)) return ca;
        }
        return null;
    }
    public void addQuantityAndPrice(CartItem ca, int quantity){
        ca.setQuantity(ca.getQuantity()+quantity);
        ca.setPrice(ca.getQuantity()*ca.getPricePerProduct());
    }

    public void delete(CartItem caForRemove) {
        int indexForDelete = -1;
        for (CartItem ca:cartItems){
            if(ca.equals(caForRemove)){
                indexForDelete = cartItems.indexOf(ca);
            }
        }
        if (indexForDelete!=-1){
            cartItems.remove(indexForDelete);
        }
        recalculateTotalPrice();
    }

    public void clean() {
        cartItems.clear();
        recalculateTotalPrice();
    }
}
