package backend.project.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
@Data
@NoArgsConstructor
public class DTOProduct {
    private Long id;
    private String image;
    private String name;
    private String description;
    private BigDecimal price;
    private int stock;
    private String origin;
    private String intensity;
    private String weightgram;
    private Long categoryId;

    // private byte[] image;
}
