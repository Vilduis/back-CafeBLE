package backend.project.controllers;

import backend.project.dtos.DTOPaymentMethod;
import backend.project.entities.Client;
import backend.project.entities.PaymentMethod;
import backend.project.services.PaymentMethodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@CrossOrigin("*")
public class PaymentMethodController {
    @Autowired
    PaymentMethodService paymentMethodService;

    @GetMapping("/payment-methods")
    public ResponseEntity<List<PaymentMethod>> getAllPaymentMethod()
    {
        return ResponseEntity.ok(paymentMethodService.listAllPaymentMethod());
    }

    @GetMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethod> getPaymentMethod(@PathVariable("id") Long id)
    {
        PaymentMethod paymentMethod = paymentMethodService.findById(id);
        return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
    }

    @PostMapping("/payment-methods/register")
    public ResponseEntity<PaymentMethod> register(@RequestBody DTOPaymentMethod dtoPaymentMethod)
    {
        PaymentMethod paymentMethod = paymentMethodService.insertPaymentMethod(dtoPaymentMethod);
        return new ResponseEntity<>(paymentMethod, HttpStatus.CREATED);
    }

    @PutMapping("/payment-methods/{id}")
    public ResponseEntity<PaymentMethod> updatePaymentMethod(@PathVariable("id") Long id, @RequestBody DTOPaymentMethod dtoPaymentMethod)
    {
        PaymentMethod paymentMethod = paymentMethodService.updatePaymentMethod(dtoPaymentMethod);
        return new ResponseEntity<>(paymentMethod, HttpStatus.OK);
    }

    @DeleteMapping("/payment-methods/{id}")
    public ResponseEntity<Void> deletePaymentMethod(@PathVariable("id") Long id)
    {
        paymentMethodService.deletePaymentMethod(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
