package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOOrder {
    private Long id;
    private String status;
    private BigDecimal total;

    private Long clientId;
    private Long paymentMethodsId;
}
