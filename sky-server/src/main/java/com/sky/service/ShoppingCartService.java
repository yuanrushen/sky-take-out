package com.sky.service;

import com.sky.dto.ShoppingCartDTO;
import com.sky.entity.ShoppingCart;

import java.util.List;

public interface ShoppingCartService {
    void addshopping(ShoppingCartDTO shoppingCartDTO);

    List<ShoppingCart> showshoppingCart();

    void delete();

    void subshoppingclean(ShoppingCartDTO shoppingCartDTO);
}
