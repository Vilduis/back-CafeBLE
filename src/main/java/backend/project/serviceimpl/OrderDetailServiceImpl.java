package backend.project.serviceimpl;

import backend.project.dtos.DTOOrderDetail;
import backend.project.entities.Order;
import backend.project.entities.OrderDetail;
import backend.project.entities.Product;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.OrderDetailRepository;
import backend.project.services.OrderDetailService;
import backend.project.services.OrderService;
import backend.project.services.ProductService;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderDetailServiceImpl implements OrderDetailService {
    @Autowired
    OrderDetailRepository orderDetailRepository;
    @Autowired
    OrderService orderService;
    @Autowired
    ProductService productService;

    @Override
    public OrderDetail insertOrderDetail(DTOOrderDetail dtoOrderDetail)
    {
        Order order = (dtoOrderDetail.getOrderId() != null) ? orderService.findById(dtoOrderDetail.getOrderId()) : null;
        Product product = (dtoOrderDetail.getProductId() != null) ? productService.findById(dtoOrderDetail.getProductId()) : null;
        if(dtoOrderDetail.getUnitPrice().compareTo(BigDecimal.ZERO)  <=  0)
        {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        OrderDetail orderDetail = new OrderDetail();
        orderDetail.setOrder(order);
        orderDetail.setProduct(product);
        orderDetail.setQuantity(dtoOrderDetail.getQuantity());
        orderDetail.setUnitPrice(dtoOrderDetail.getUnitPrice());
        return orderDetailRepository.save(orderDetail);
    }
    @Override
    public OrderDetail updateOrderDetail(DTOOrderDetail dtoOrderDetail)
    {
        OrderDetail orderDetailFound = orderDetailRepository.findById(dtoOrderDetail.getId()).orElse(null);
        if (orderDetailFound == null) {
            throw new ResourceNotFoundException("OrderDetail with id:" + dtoOrderDetail.getId() + "can not be found");
        }
        Order order = (dtoOrderDetail.getOrderId() != null) ? orderService.findById(dtoOrderDetail.getOrderId()) : null;
        Product product = (dtoOrderDetail.getProductId() != null) ? productService.findById(dtoOrderDetail.getProductId()) : null;
        if(dtoOrderDetail.getUnitPrice().compareTo(BigDecimal.ZERO)  <=  0)
        {
            throw new IllegalArgumentException("Price must be greater than 0");
        }
        orderDetailFound.setOrder(order);
        orderDetailFound.setProduct(product);
        orderDetailFound.setQuantity(dtoOrderDetail.getQuantity());
        orderDetailFound.setUnitPrice(dtoOrderDetail.getUnitPrice());
        return orderDetailRepository.save(orderDetailFound);
    }
    @Override
    public OrderDetail findById(Long id)
    {
        OrderDetail orderDetailFound = orderDetailRepository.findById(id).orElse(null);
        if (orderDetailFound == null) {
            throw new ResourceNotFoundException("OrderDetail with id:" + id + "can not be found");
        }
        return orderDetailFound;
    }
    @Override
    public void deleteOrderDetail(Long id)
    {
        OrderDetail orderDetailFound = orderDetailRepository.findById(id).orElse(null);
        if (orderDetailFound == null) {
            throw new ResourceNotFoundException("OrderDetail with id:" + id + "can not be found");
        }
        orderDetailRepository.delete(orderDetailFound);
    }
    @Override
    public List<OrderDetail> listAllOrderDetail()
    {
        return orderDetailRepository.findAll();
    }


}
