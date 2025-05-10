package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOCartItems {
    private Long id;
    private Integer quantity;
    private BigDecimal price;

    private Long cartId;
    private Long productId;
}
