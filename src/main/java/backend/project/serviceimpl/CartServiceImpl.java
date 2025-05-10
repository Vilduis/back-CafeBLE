package backend.project.serviceimpl;

import backend.project.dtos.DTOCart;
import backend.project.entities.Cart;
import backend.project.entities.Client;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.CartRepository;
import backend.project.services.CartService;
import backend.project.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements CartService {
    @Autowired
    CartRepository cartRepository;
    @Autowired
    ClientService clientService;

    @Override
    public Cart insertCart(DTOCart dtoCart)
    {
        Client client =(dtoCart.getClientId() != null) ? clientService.findById(dtoCart.getClientId()) : null;

        Cart cart = new Cart();
        cart.setClient(client);
        cart.setStatus(dtoCart.getStatus());
        cart.setShoppingAddress(dtoCart.getShoppingAddress());
        return cartRepository.save(cart);
    }
    @Override
    public Cart updateCart(DTOCart dtoCart)
    {
        Cart cartFound = cartRepository.findById(dtoCart.getId()).orElse(null);
        if(cartFound==null)
        {
            throw new ResourceNotFoundException("Cart with id:"+dtoCart.getId()+"can not be found");
        }
        cartFound.setClient((dtoCart.getClientId() != null) ? clientService.findById(dtoCart.getClientId()) : null);
        cartFound.setStatus(dtoCart.getStatus());
        cartFound.setShoppingAddress(dtoCart.getShoppingAddress());
        return cartRepository.save(cartFound);
    }
    @Override
    public Cart findById(Long id)
    {
        Cart cartFound = cartRepository.findById(id).orElse(null);
        if(cartFound==null)
        {
            throw new ResourceNotFoundException("Cart with id:"+id+"can not be found");
        }
        return cartFound;
    }
    @Override
    public void deleteCart(Long id)
    {
        Cart cartFound = cartRepository.findById(id).orElse(null);
        if(cartFound==null)
        {
            throw new ResourceNotFoundException("Cart with id:"+id+"can not be found");
        }
        cartRepository.delete(cartFound);
    }
    @Override
    public List<Cart> listAllCart()
    {
        return cartRepository.findAll();
    }
}
