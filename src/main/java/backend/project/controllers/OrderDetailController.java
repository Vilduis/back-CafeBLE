package backend.project.controllers;

import backend.project.dtos.DTOOrderDetail;
import backend.project.entities.OrderDetail;
import backend.project.services.OrderDetailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class OrderDetailController {
    @Autowired
    OrderDetailService orderDetailService;

    @GetMapping("/order-details")
    public ResponseEntity<List<OrderDetail>> getAllOrderDetail(){
        return ResponseEntity.ok(orderDetailService.listAllOrderDetail());
    }

    @GetMapping("/order-details/{id}")
    public ResponseEntity<OrderDetail> getOrderDetail(@PathVariable("id") Long id){
        OrderDetail orderDetail = orderDetailService.findById(id);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @PostMapping("/order-details/register")
    public ResponseEntity<OrderDetail> register(@RequestBody DTOOrderDetail dtoOrderDetail){
        OrderDetail orderDetail = orderDetailService.insertOrderDetail(dtoOrderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.CREATED);
    }

    @PutMapping("/order-details/{id}")
    public ResponseEntity<OrderDetail> updateOrderDetail(@PathVariable("id") Long id, @RequestBody DTOOrderDetail dtoOrderDetail){
        OrderDetail orderDetail = orderDetailService.updateOrderDetail(dtoOrderDetail);
        return new ResponseEntity<>(orderDetail, HttpStatus.OK);
    }

    @DeleteMapping("/order-details/{id}")
    public ResponseEntity<Void> deleteOrderDetail(@PathVariable("id") Long id) {
        orderDetailService.deleteOrderDetail(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
