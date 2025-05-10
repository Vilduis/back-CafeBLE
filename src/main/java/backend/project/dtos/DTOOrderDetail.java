package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOOrderDetail {
    private Long id;
    private int quantity;
    private BigDecimal unitPrice;

    private Long productId;
    private Long orderId;
}
