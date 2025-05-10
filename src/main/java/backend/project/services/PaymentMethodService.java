package backend.project.services;

import backend.project.dtos.DTOPaymentMethod;
import backend.project.entities.PaymentMethod;

import java.util.List;

public interface PaymentMethodService {
    public PaymentMethod insertPaymentMethod(DTOPaymentMethod dtoPaymentMethod);
    public PaymentMethod updatePaymentMethod(DTOPaymentMethod dtoPaymentMethod);
    public PaymentMethod findById(Long id);
    public void deletePaymentMethod(Long id);
    public List<PaymentMethod> listAllPaymentMethod();
}
