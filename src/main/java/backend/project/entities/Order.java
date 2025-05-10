package backend.project.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

@Entity
@AllArgsConstructor
@Data
@NoArgsConstructor
@Table(name="orders")
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String status;
    private BigDecimal total;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "client_id")
    private Client client;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "paymentmethod_id")
    private PaymentMethod paymentMethod;

    @JsonIgnore
    @OneToMany(mappedBy = "order")
    private List<OrderDetail> orderDetails;
}
