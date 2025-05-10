package backend.project.services;

import backend.project.dtos.DTOCartItems;
import backend.project.entities.CartItems;

import java.util.List;

public interface CartItemsService {
    public CartItems insertCartItems(DTOCartItems dtoCartItems);
    public CartItems updateCartItems(DTOCartItems dtoCartItems);
    public CartItems findById(Long id);
    public void deleteCartItems(Long id);
    public List<CartItems> listAllCartItems();

}
