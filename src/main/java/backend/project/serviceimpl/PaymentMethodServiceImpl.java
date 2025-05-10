package backend.project.serviceimpl;

import backend.project.dtos.DTOPaymentMethod;
import backend.project.entities.PaymentMethod;
import backend.project.exceptions.ResourceNotFoundException;
import backend.project.repositories.PaymentMethodsRepository;
import backend.project.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PaymentMethodServiceImpl implements PaymentMethodService {
    @Autowired
    PaymentMethodsRepository paymentMethodsRepository;

    public  boolean isPaymentMethodExists(String name) {
        return paymentMethodsRepository.findByName(name).isPresent();
    }

    @Override
    public PaymentMethod insertPaymentMethod(DTOPaymentMethod dtoPaymentMethod)
    {
        PaymentMethod paymentMethod = new PaymentMethod();
        if(isPaymentMethodExists(dtoPaymentMethod.getName()))
        {
            throw  new IllegalArgumentException("Payment method with name: "+ dtoPaymentMethod.getName()+ " already exists");
        }
        paymentMethod.setName(dtoPaymentMethod.getName());
        paymentMethod.setDescription(dtoPaymentMethod.getDescription());
        paymentMethod.setActive(dtoPaymentMethod.getActive());
        return paymentMethodsRepository.save(paymentMethod);
    }

    @Override
    public PaymentMethod updatePaymentMethod(DTOPaymentMethod dtoPaymentMethod)
    {
        PaymentMethod paymentMethodFound = paymentMethodsRepository.findById(dtoPaymentMethod.getId()).orElse(null);
        if(paymentMethodFound==null)
        {
           throw  new ResourceNotFoundException("Payment method with id:"+ dtoPaymentMethod.getId()+ " can not be found");
        }
        if (isPaymentMethodExists(dtoPaymentMethod.getName()))
        {
            throw  new IllegalArgumentException("Payment method with name: "+ dtoPaymentMethod.getName()+ " already exists");
        }
        paymentMethodFound.setName(dtoPaymentMethod.getName());
        paymentMethodFound.setDescription(dtoPaymentMethod.getDescription());
        paymentMethodFound.setActive(dtoPaymentMethod.getActive());
        return paymentMethodsRepository.save(paymentMethodFound);
    }

    @Override
    public PaymentMethod findById(Long id)
    {
        PaymentMethod paymentMethodFound = paymentMethodsRepository.findById(id).orElse(null);
        if(paymentMethodFound==null)
        {
            throw  new ResourceNotFoundException("Payment method with id:"+id+"can not be found");
        }
        return paymentMethodFound;
    }

    @Override
    public void deletePaymentMethod(Long id)
    {
        PaymentMethod paymentMethodFound = paymentMethodsRepository.findById(id).orElse(null);
        if(paymentMethodFound==null)
        {
            throw  new ResourceNotFoundException("Payment method with id:"+id+"can not be found");
        }
        paymentMethodsRepository.delete(paymentMethodFound);
    }

    @Override
    public List<PaymentMethod> listAllPaymentMethod()
    {return paymentMethodsRepository.findAll();}
}
