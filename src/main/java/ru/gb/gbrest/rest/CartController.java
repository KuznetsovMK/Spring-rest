package ru.gb.gbrest.rest;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.gb.gbrest.entity.Cart;
import ru.gb.gbrest.entity.Product;
import ru.gb.gbrest.service.CartService;
import ru.gb.gbrest.service.ProductService;

import java.net.URI;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/cart")
public class CartController {
    private final CartService cartService;
    private final ProductService productService;

    @GetMapping
    public List<Cart> getProductList() {
        return cartService.findAll();
    }

    @PostMapping("/{productId}")
    public ResponseEntity<?> handlePost(@PathVariable("productId") Long id, @Validated @RequestBody Cart cart) {
        Product product = productService.findById(id);
        Cart saveCart = cartService.save(product);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setLocation(URI.create("/api/v1/cart/" + saveCart.getId()));
        return new ResponseEntity<>(httpHeaders, HttpStatus.CREATED);
    }

    @DeleteMapping("/{cartId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteById(@PathVariable("cartId") Long id) {
        cartService.deleteById(id);
    }
}