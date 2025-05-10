package backend.project.services;

import backend.project.dtos.DTOOrder;
import backend.project.entities.Order;

import java.util.List;

public interface OrderService {
    public Order insertOrder(DTOOrder dtoOrder);
    public Order updateOrder(DTOOrder dtoOrder);
    public Order findById(Long id);
    public void deleteOrder(Long id);
    public List<Order> listAllOrder();
}
