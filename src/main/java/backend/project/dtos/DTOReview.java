package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOReview {
    private Long id;
    private Integer rating;
    private String comment;
    private Long clientId;
    private Long productId;
}
