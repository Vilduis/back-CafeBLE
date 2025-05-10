package backend.project.repositories;

import backend.project.entities.PaymentMethod;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface PaymentMethodsRepository extends JpaRepository<PaymentMethod, Long> {
    Optional<PaymentMethod> findByName(String name);
}
