package backend.project.services;

import backend.project.dtos.DTOCart;
import backend.project.entities.Cart;

import java.util.List;

public interface CartService {
    public Cart insertCart(DTOCart dtoCart);
    public Cart updateCart(DTOCart dtoCart);
    public Cart findById(Long id);
    public void deleteCart(Long id);
    public List<Cart> listAllCart();
}
