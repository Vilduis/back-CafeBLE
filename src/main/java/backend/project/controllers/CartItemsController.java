package backend.project.controllers;

import backend.project.dtos.DTOCartItems;
import backend.project.entities.CartItems;
import backend.project.services.CartItemsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class CartItemsController {
    @Autowired
    CartItemsService cartItemsService;

    @GetMapping("/cartItems")
    public ResponseEntity<List<CartItems>> getAllCartItems()
    {
        return ResponseEntity.ok(cartItemsService.listAllCartItems());
    }
    @GetMapping("/cartItems/{id}")
    public ResponseEntity<CartItems> getCartItemsById(@PathVariable("id") Long id)
    {
        CartItems cartItems = cartItemsService.findById(id);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @PostMapping("/cartItems/register")
    public ResponseEntity<CartItems> register(@RequestBody DTOCartItems dtoCartItems)
    {
        CartItems cartItems = cartItemsService.insertCartItems(dtoCartItems);
        return new ResponseEntity<>(cartItems, HttpStatus.CREATED);
    }

    @PutMapping("/cartItems/{id}")
    public ResponseEntity<CartItems> updateCartItems(@PathVariable("id") Long id, @RequestBody DTOCartItems dtoCartItems)
    {
        CartItems cartItems = cartItemsService.updateCartItems(dtoCartItems);
        return new ResponseEntity<>(cartItems, HttpStatus.OK);
    }

    @DeleteMapping("/cartItems/{id}")
    public ResponseEntity<Void> deleteCartItems(@PathVariable("id") Long id)
    {
        cartItemsService.deleteCartItems(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
