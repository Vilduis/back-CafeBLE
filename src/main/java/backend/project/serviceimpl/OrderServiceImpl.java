package backend.project.serviceimpl;

import backend.project.dtos.DTOOrder;
import backend.project.entities.Client;
import backend.project.entities.Order;
import backend.project.entities.PaymentMethod;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.OrderRepository;
import backend.project.services.ClientService;
import backend.project.services.OrderService;
import backend.project.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ClientService clientService;

    @Autowired
    PaymentMethodService paymentMethodService;

    @Override
    public Order insertOrder(DTOOrder dtoOrder)
    {
        Client client = (dtoOrder.getClientId() != null) ? clientService.findById(dtoOrder.getClientId()) : null;
        PaymentMethod paymentMethod = (dtoOrder.getPaymentMethodsId() != null) ? paymentMethodService.findById(dtoOrder.getPaymentMethodsId()) : null;

        Order order = new Order();
        order.setClient(client);
        order.setStatus(dtoOrder.getStatus());
        order.setTotal(dtoOrder.getTotal());
        order.setPaymentMethod(paymentMethod);
        return orderRepository.save(order);
    }

    @Override
    public Order updateOrder(DTOOrder dtoOrder)
    {
        Order orderFound = orderRepository.findById(dtoOrder.getId()).orElse(null);
        if(orderFound==null)
        {
            throw new ResourceNotFoundException("Order with id:"+dtoOrder.getId()+"can not be found");
        }

        orderFound.setClient((dtoOrder.getClientId() != null) ? clientService.findById(dtoOrder.getClientId()) : null);
        orderFound.setStatus(dtoOrder.getStatus());
        orderFound.setTotal(dtoOrder.getTotal());
        orderFound.setPaymentMethod((dtoOrder.getPaymentMethodsId() != null) ? paymentMethodService.findById(dtoOrder.getPaymentMethodsId()) : null);
        return orderRepository.save(orderFound);
    }

    @Override
    public Order findById(Long id)
    {
        Order orderFound = orderRepository.findById(id).orElse(null);
        if(orderFound==null)
        {
            throw new ResourceNotFoundException("Order with id:"+id+"can not be found");
        }
        return orderFound;
    }
    @Override
    public void deleteOrder(Long id)
    {
        Order orderFound = orderRepository.findById(id).orElse(null);
        if(orderFound==null)
        {
            throw new ResourceNotFoundException("Order with id:"+id+"can not be found");
        }
        orderRepository.delete(orderFound);
    }

    @Override
    public List<Order> listAllOrder()
    {
        return orderRepository.findAll();
    }
}
