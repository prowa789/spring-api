package com.hust.tacosapi.api;

import com.hust.tacosapi.data.OrderRepository;
import com.hust.tacosapi.data.UserRepository;
import com.hust.tacosapi.entity.Order;
import com.hust.tacosapi.entity.User;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping(path = "/orders",produces = "application/json")
public class OrderApiController {

    private OrderRepository repo;

    private UserRepository userRepo;

    public OrderApiController(OrderRepository repo, UserRepository userRepo) {
        this.repo = repo;
        this.userRepo = userRepo;
    }

    @GetMapping(produces="application/json")
    public Iterable<Order> allOrders() {
        return repo.findAll();
    }
    
    @GetMapping(path = "/{id}")
    public ResponseEntity<Order> findOneOrder(@PathVariable Long id){
        Optional<Order> optOrder = repo.findById(id);
        if(optOrder.isPresent()){
            return new ResponseEntity<>(optOrder.get(),HttpStatus.OK);
        }
        return new ResponseEntity<>(null,HttpStatus.NOT_FOUND);
    }

    @PostMapping(consumes = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        Optional<User> user = userRepo.findById((long) 1);
        order.setUser(user.get());
        return repo.save(order);
    }

    @PatchMapping(path="/{orderId}", consumes="application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {
        Order order = repo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code= HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {}
    }
}
