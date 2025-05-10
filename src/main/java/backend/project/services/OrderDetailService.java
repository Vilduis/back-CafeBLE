package backend.project.services;

import backend.project.dtos.DTOOrderDetail;
import backend.project.entities.OrderDetail;

import java.util.List;

public interface OrderDetailService {
    public OrderDetail insertOrderDetail(DTOOrderDetail dtoOrderDetail);
    public OrderDetail updateOrderDetail(DTOOrderDetail dtoOrderDetail);
    public OrderDetail findById(Long id);
    public void deleteOrderDetail(Long id);
    public List<OrderDetail> listAllOrderDetail();
}
