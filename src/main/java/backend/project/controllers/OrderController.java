package backend.project.controllers;

import backend.project.dtos.DTOOrder;
import backend.project.entities.Order;
import backend.project.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderController {
    @Autowired
    OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<List<Order>> getAllOrder() {
        return ResponseEntity.ok(orderService.listAllOrder());
    }

    @GetMapping("/orders/{id}")
    public ResponseEntity<Order> getOrder(@PathVariable("id") Long id) {
        Order order = orderService.findById(id);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @PostMapping("/orders/register")
    public ResponseEntity<Order> register(@RequestBody DTOOrder dtoOrder) {
        Order order = orderService.insertOrder(dtoOrder);
        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> updateOrder(@PathVariable("id") Long id, @RequestBody DTOOrder dtoOrder) {
        Order order = orderService.updateOrder(dtoOrder);
        return new ResponseEntity<>(order, HttpStatus.OK);
    }

    @DeleteMapping("/orders/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable("id") Long id) {
        orderService.deleteOrder(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
