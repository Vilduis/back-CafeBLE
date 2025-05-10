package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOCliente {
    private Long id;
    private String firstName;
    private String lastName;
    private String phone;
    private String address;

    private Long userId;
}
