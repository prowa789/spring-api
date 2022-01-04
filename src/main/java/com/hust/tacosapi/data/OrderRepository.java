package com.hust.tacosapi.data;

import com.hust.tacosapi.entity.Order;
import com.hust.tacosapi.entity.User;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OrderRepository extends CrudRepository<Order,Long> {
    List<Order> findByDeliveryZip(String deliveryZip);
    List<Order> findByUserOrderByPlacedAtDesc(User user, Pageable pageable);
}
