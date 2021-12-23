package ru.gb.gbrest.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import ru.gb.gbrest.dao.CartDao;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CartService {

    private final CartDao cartDao;

    public void deleteById(Long id) {
        try {
            cartDao.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            log.error("No such id in DB: " + e.getMessage());
        }
    }

    public List<Cart> findAll() {
        return cartDao.findAll();
    }

    public Cart save(Product product) {
        Cart cart = Cart.builder()
                .product_id(product.getId())
                .title(product.getTitle())
                .cost(product.getCost())
                .build();
        return cartDao.save(cart);
    }
}
