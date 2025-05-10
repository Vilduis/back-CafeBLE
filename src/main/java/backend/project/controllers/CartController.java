package backend.project.controllers;

import backend.project.dtos.DTOCart;
import backend.project.entities.Cart;
import backend.project.services.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CartController {
    @Autowired
    CartService cartService;

    @GetMapping("/carts")
    public ResponseEntity<List<Cart>> getAllCart() {
        return ResponseEntity.ok(cartService.listAllCart());
    }

    @GetMapping("/carts/{id}")
    public ResponseEntity<Cart> getCart(@PathVariable("id") Long id) {
        Cart cart = cartService.findById(id);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @PostMapping("/carts/register")
    public ResponseEntity<Cart> insertCart(@RequestBody DTOCart dtoCart) {
        Cart cart = cartService.insertCart(dtoCart);
        return new ResponseEntity<>(cart, HttpStatus.CREATED);
    }

    @PutMapping("/carts/{id}")
    public ResponseEntity<Cart> updateCart(@PathVariable("id") Long id, @RequestBody DTOCart dtoCart) {
        Cart cart = cartService.updateCart(dtoCart);
        return new ResponseEntity<>(cart, HttpStatus.OK);
    }

    @DeleteMapping("/carts/{id}")
    public ResponseEntity<Void> deleteCart(@PathVariable("id") Long id) {
        cartService.deleteCart(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
