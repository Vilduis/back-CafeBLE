package backend.project.serviceimpl;

import backend.project.dtos.DTOCartItems;
import backend.project.entities.Cart;
import backend.project.entities.CartItems;
import backend.project.entities.Product;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CartItemsRepository;
import backend.project.services.CartItemsService;
import backend.project.services.CartService;
import backend.project.services.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class CartItemsServiceImpl implements CartItemsService {
    @Autowired
    CartItemsRepository cartItemsRepository;
    @Autowired
    CartService cartService;
    @Autowired
    ProductService productService;

    @Override
    public CartItems insertCartItems(DTOCartItems dtoCartItems)
    {
        Cart cart=(dtoCartItems.getCartId() != null) ? cartService.findById(dtoCartItems.getCartId()) : null;
        Product product=(dtoCartItems.getProductId() != null) ? productService.findById(dtoCartItems.getProductId()) : null;
        if (dtoCartItems.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }

        CartItems cartItems = new CartItems();
        cartItems.setCart(cart);
        cartItems.setProduct(product);
        cartItems.setQuantity(dtoCartItems.getQuantity());
        cartItems.setPrice(dtoCartItems.getPrice());
        return cartItemsRepository.save(cartItems);
    }
    @Override
    public CartItems updateCartItems(DTOCartItems dtoCartItems)
    {
        CartItems cartItemsFound =cartItemsRepository.findById(dtoCartItems.getId()).orElse(null);
        if (cartItemsFound == null) {
            throw new ResourceNotFoundException("CartItems with id:" + dtoCartItems.getId() + "can not be found");
        }
        Cart cart = (dtoCartItems.getCartId() != null) ? cartService.findById(dtoCartItems.getCartId()) : null;
        Product product = (dtoCartItems.getProductId() != null) ? productService.findById(dtoCartItems.getProductId()) : null;
        if (dtoCartItems.getPrice().compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        cartItemsFound.setCart(cart);
        cartItemsFound.setProduct(product);
        cartItemsFound.setQuantity(dtoCartItems.getQuantity());
        cartItemsFound.setPrice(dtoCartItems.getPrice());
        return cartItemsRepository.save(cartItemsFound);
    }
    @Override
    public CartItems findById(Long id)
    {
        CartItems cartItemsFound = cartItemsRepository.findById(id).orElse(null);
        if (cartItemsFound == null) {
            throw new ResourceNotFoundException("CartItems with id:" + id + "can not be found");
        }
        return cartItemsFound;
    }
    @Override
    public void deleteCartItems(Long id)
    {
        CartItems cartItemsFound = cartItemsRepository.findById(id).orElse(null);
        if (cartItemsFound == null) {
            throw new ResourceNotFoundException("CartItems with id:" + id + "can not be found");
        }
        cartItemsRepository.delete(cartItemsFound);
    }
    @Override
    public List<CartItems> listAllCartItems()
    {
        return cartItemsRepository.findAll();
    }
}
