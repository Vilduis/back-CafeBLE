package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOCart {
    private Long id;
    private String status;
    private String shoppingAddress;
    private Long clientId;
}
