package com.delcons.features.sale.cart.repository;

import com.delcons.features.sale.cart.model.Cart;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Repository;

import java.util.concurrent.TimeUnit;

@Repository
public class CartRedisRepository {
    private final RedisTemplate<String, Object> redisTemplate;
    private static final long CART_TTL = 86400; // un dia en segundos;

    public CartRedisRepository(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public void saveCart(String userId, Cart cart) {
        redisTemplate.opsForValue().set("cart" + userId, cart, CART_TTL, TimeUnit.SECONDS);
    }

    public Cart getCart(String userId) {
        return (Cart) redisTemplate.opsForValue().get("cart" + userId);
    }

    public void deleteCart(String userId) {
        redisTemplate.delete("cart:" + userId);
    }
}
