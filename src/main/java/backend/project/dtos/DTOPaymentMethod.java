package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOPaymentMethod {
    private Long id;
    private String name;
    private String description;
    private Boolean active;
}
