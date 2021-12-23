package ru.gb.gbrest.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.gb.gbrest.entity.Cart;

public interface CartDao extends JpaRepository<Cart, Long> {
}
